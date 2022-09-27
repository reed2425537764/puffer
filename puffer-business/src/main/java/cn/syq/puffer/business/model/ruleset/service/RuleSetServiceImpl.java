package cn.syq.puffer.business.model.ruleset.service;

import cn.hutool.core.lang.Pair;
import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.*;
import cn.syq.puffer.business.model.rule.api.Rule;
import cn.syq.puffer.business.model.ruleset.api.RsCatalog;
import cn.syq.puffer.business.model.ruleset.api.RuleSet;
import cn.syq.puffer.business.utils.ModelUtils;
import cn.syq.puffer.dao.sql.entity.ModelRs;
import cn.syq.puffer.dao.sql.entity.ModelRsCatalog;
import cn.syq.puffer.dao.sql.entity.ModelRsHis;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/21 11:16
 */
public class RuleSetServiceImpl implements RuleSetService{

    @Autowired
    private RuleSetDomainService ruleSetDomainService;

    @Autowired
    private DataObjectDomainService dataobjectDomainService;

    @Autowired
    private ProjectDomainService projectDomainService;

    @Autowired
    private RuleDomainService ruleDomainService;


    @Override
    public Page<ModelRsCatalog> listRuleSetCatalogs(long projectId, Optional<String> labelOpt, int pageNo, int pageSize) {

        projectDomainService.checkProjectExist(projectId);

        return ruleSetDomainService.listRsCatalogPaging(projectId, labelOpt, pageNo, pageSize);
    }

    @Override
    public ModelRsCatalog addRsCatalog(ModelRsCatalog modelRsCatalog) {

        projectDomainService.checkProjectExist(modelRsCatalog.getProjectId());

        ModelRsCatalog modelRsCatalogByLabel = ruleSetDomainService.queryRsCatalogByLabel(modelRsCatalog.getProjectId(), modelRsCatalog.getLabel());
        if (Objects.isNull(modelRsCatalogByLabel) || Objects.equals(modelRsCatalog.getLabel(), modelRsCatalogByLabel.getLabel())) {
            throw new ManagerException(ManagerErrorCode.E20);
        }

        ruleSetDomainService.addRsCatalog(modelRsCatalog);
        return modelRsCatalog;
    }

    @Override
    public ModelRsCatalog getRsCatalogMeta(long projectId, long rsCatalogId) {

        projectDomainService.checkProjectExist(projectId);

        return ruleSetDomainService.checkRsCatalogExist(projectId, rsCatalogId);
    }

    @Override
    public ModelRsCatalog editRsCatalogMeta(ModelRsCatalog modelRsCatalog) {
        projectDomainService.checkProjectExist(modelRsCatalog.getProjectId());

        ModelRsCatalog modelRsCatalogOld = ruleSetDomainService.checkRsCatalogExist(modelRsCatalog.getProjectId(), modelRsCatalog.getId());
        if (!Objects.equals(modelRsCatalogOld.getLabel(), modelRsCatalog.getLabel())) {
            ModelRsCatalog modelRsCatalogByLabel = ruleSetDomainService.queryRsCatalogByLabel(modelRsCatalog.getId(), modelRsCatalog.getLabel());
            if (Objects.nonNull(modelRsCatalogByLabel)) {
                throw new ManagerException(ManagerErrorCode.E20);
            }
            ruleSetDomainService.updateRsCatalog(modelRsCatalog);
        }
        return modelRsCatalog;
    }

    @Override
    public ModelRs addRuleSet(ModelRs modelRs) {
        projectDomainService.checkProjectExist(modelRs.getProjectId());
        ruleSetDomainService.checkRsCatalogExist(modelRs.getProjectId(), modelRs.getCatalogId());

        ModelRs modelRsByLabel = ruleSetDomainService.queryRsByLabel(modelRs.getProjectId(), modelRs.getLabel());
        if (Objects.nonNull(modelRsByLabel)) {
            throw new ManagerException(ManagerErrorCode.E20);
        }

        ModelRs modelRsNew = ruleSetDomainService.addRuleSet(modelRs);
        ModelRsHis modelRsHis = ruleSetDomainService.addRuleSetHis(modelRsNew, His.of(HisType.A, "新增"));
        modelRsNew.setHisId(modelRsHis.getId());
        ruleSetDomainService.updateRuleSet(modelRsNew);

        return modelRsNew;
    }

    @Override
    public List<RsCatalog> listRuleSetMeta(long projectId, Optional<Long> rsCatalogIdOpt, boolean includeRule) {
        List<RsCatalog> list = Lists.newArrayList();

        projectDomainService.checkProjectExist(projectId);
        if (rsCatalogIdOpt.isPresent()) {
            ModelRsCatalog modelRsCatalog = ruleSetDomainService.checkRsCatalogExist(projectId, rsCatalogIdOpt.get());
            RsCatalog rsCatalog = new RsCatalog();
            rsCatalog.setId(modelRsCatalog.getId());
            rsCatalog.setName(modelRsCatalog.getName());
            rsCatalog.setLabel(modelRsCatalog.getName());
            rsCatalog.setRuleSets(ruleSetDomainService.listRsByCatalogId(projectId, rsCatalogIdOpt).stream()
                    .map(modelRs -> ruleSetDomainService.modelRs2Rs(modelRs)).collect(Collectors.toList()));
            return list;
        }


        Map<Long, ModelRsCatalog> rsCatalogMap = ruleSetDomainService.listRsCatalog(projectId).stream()
                .collect(Collectors.toMap(ModelRsCatalog::getId, Function.identity()));

        Map<Long, List<RuleSet>> ruleSetMap = ruleSetDomainService.listRuleSetGroupByRsCatalogId(projectId);

        Map<Long, List<Rule>> ruleMap = ruleDomainService.listRuleGroupByCatalogId(projectId);

        ruleSetMap.forEach((catalogId, ruleSet) -> {
            RsCatalog rsCatalog = new RsCatalog();
            ModelRsCatalog modelRsCatalog = rsCatalogMap.get(catalogId);
            if (Objects.nonNull(modelRsCatalog)) {
                rsCatalog.setId(modelRsCatalog.getId());
                rsCatalog.setName(modelRsCatalog.getName());
                rsCatalog.setLabel(modelRsCatalog.getLabel());
            }
            ruleSet.forEach(rs -> {
                if (includeRule && ruleMap.containsKey(rs.getId())) {
                    rs.setRules(ruleMap.get(rs.getId()));
                }
            });
            list.add(rsCatalog);
        });

        rsCatalogMap.values().stream()
                .filter(modelRsCatalog -> !ruleSetMap.containsKey(modelRsCatalog.getId()))
                .map(modelRsCatalog -> {
                    RsCatalog rsCatalog = new RsCatalog();
                    rsCatalog.setId(modelRsCatalog.getId());
                    rsCatalog.setName(modelRsCatalog.getName());
                    rsCatalog.setLabel(modelRsCatalog.getLabel());
                    return rsCatalog;
                })
                .forEach(list::add);

        return list;
    }
}
