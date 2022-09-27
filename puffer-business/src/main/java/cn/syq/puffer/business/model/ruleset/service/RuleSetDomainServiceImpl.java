package cn.syq.puffer.business.model.ruleset.service;

import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.His;
import cn.syq.puffer.business.model.api.RuleSetDomainService;
import cn.syq.puffer.business.model.ruleset.api.RuleSet;
import cn.syq.puffer.business.utils.ModelUtils;
import cn.syq.puffer.business.utils.StringTools;
import cn.syq.puffer.dao.sql.entity.ModelRs;
import cn.syq.puffer.dao.sql.entity.ModelRsCatalog;
import cn.syq.puffer.dao.sql.entity.ModelRsHis;
import cn.syq.puffer.dao.sql.mapper.ModelRsCatalogMapper;
import cn.syq.puffer.dao.sql.mapper.ModelRsHisMapper;
import cn.syq.puffer.dao.sql.mapper.ModelRsMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/21 11:15
 */
public class RuleSetDomainServiceImpl implements RuleSetDomainService {

    @Autowired
    private ModelRsCatalogMapper modelRsCatalogMapper;

    @Autowired
    private ModelRsMapper modelRsMapper;

    @Autowired
    private ModelRsHisMapper modelRsHisMapper;


    @Override
    public Page<ModelRsCatalog> listRsCatalogPaging(long projectId, Optional<String> labelOpt, int pageNo, int pageSize) {
        LambdaQueryWrapper<ModelRsCatalog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelRsCatalog::getProjectId, projectId).eq(labelOpt.isPresent(), ModelRsCatalog::getLabel, labelOpt.get());
        return modelRsCatalogMapper.selectPage(Page.of(pageNo, pageSize), wrapper);
    }

    @Override
    public ModelRsCatalog queryRsCatalogByLabel(long projectId, String label) {
        LambdaQueryWrapper<ModelRsCatalog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelRsCatalog::getProjectId, projectId);
        wrapper.eq(ModelRsCatalog::getLabel, label);
        return modelRsCatalogMapper.selectOne(wrapper);
    }

    @Override
    public List<ModelRsCatalog> listRsCatalog(long projectId) {
        LambdaQueryWrapper<ModelRsCatalog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelRsCatalog::getProjectId, projectId);
        return modelRsCatalogMapper.selectList(wrapper);
    }

    @Override
    public ModelRsCatalog checkRsCatalogExist(long projectId, long rsCatalogId) {
        ModelRsCatalog modelRsCatalog = modelRsCatalogMapper.selectById(rsCatalogId);
        if (Objects.isNull(modelRsCatalog) || !Objects.equals(projectId, modelRsCatalog.getProjectId())) {
            throw new ManagerException(ManagerErrorCode.E20);
        }
        return modelRsCatalog;
    }

    @Override
    public ModelRsCatalog addRsCatalog(ModelRsCatalog modelRsCatalog) {
        modelRsCatalog.setName(ModelUtils.generateModelName());
        modelRsCatalogMapper.insert(modelRsCatalog);
        return modelRsCatalog;
    }

    @Override
    public int updateRsCatalog(ModelRsCatalog modelRsCatalog) {
        LambdaUpdateWrapper<ModelRsCatalog> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ModelRsCatalog::getId, modelRsCatalog.getId());
        wrapper.set(StringTools.isNotBlank(modelRsCatalog.getLabel()), ModelRsCatalog::getLabel, modelRsCatalog.getLabel());
        return modelRsCatalogMapper.update(null, wrapper);
    }

    @Override
    public ModelRs queryRsByLabel(long projectId, String label) {
        LambdaQueryWrapper<ModelRs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelRs::getProjectId, projectId).eq(ModelRs::getLabel, label);
        return modelRsMapper.selectOne(wrapper);
    }

    @Override
    public List<ModelRs> listRsByCatalogId(long projectId, Optional<Long> rsCatalogId) {
        LambdaQueryWrapper<ModelRs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelRs::getProjectId, projectId).eq(rsCatalogId.isPresent(), ModelRs::getCatalogId, rsCatalogId.get());
        return modelRsMapper.selectList(wrapper);
    }

    @Override
    public ModelRs addRuleSet(ModelRs modelRs) {
        modelRs.setName(ModelUtils.generateModelName());
        modelRsMapper.insert(modelRs);
        return modelRs;
    }

    @Override
    public int updateRuleSet(ModelRs modelRs) {
        LambdaUpdateWrapper<ModelRs> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ModelRs::getId, modelRs.getId());
        wrapper.set(StringTools.isNotBlank(modelRs.getLabel()), ModelRs::getLabel, modelRs.getLabel());
        wrapper.set(Objects.nonNull(modelRs.getCatalogId()), ModelRs::getCatalogId, modelRs.getCatalogId());
        wrapper.set(Objects.nonNull(modelRs.getHisId()), ModelRs::getHisId, modelRs.getHisId());
        return modelRsMapper.update(null, wrapper);
    }

    @Override
    public ModelRsHis addRuleSetHis(ModelRs modelRs, His his) {
        ModelRsHis modelRsHis = new ModelRsHis();
        modelRsHis.setType(his.getType().name());
        modelRsHis.setComment(his.getRemark());
        modelRsHis.setForeignId(modelRs.getId());
        modelRsHis.setName(modelRs.getName());
        modelRsHis.setLabel(modelRs.getLabel());
        modelRsHis.setCatalogId(modelRs.getCatalogId());
        modelRsHisMapper.insert(modelRsHis);
        return modelRsHis;
    }

    @Override
    public RuleSet modelRs2Rs(ModelRs modelRs) {
        RuleSet ruleSet = new RuleSet();
        ruleSet.setId(modelRs.getId());
        ruleSet.setName(modelRs.getName());
        ruleSet.setLabel(modelRs.getLabel());
        ruleSet.setHisId(modelRs.getHisId());
        ruleSet.setCatalogId(modelRs.getCatalogId());
        return ruleSet;
    }

    @Override
    public Map<Long, List<RuleSet>> listRuleSetGroupByRsCatalogId(long projectId) {
        return listRsByCatalogId(projectId, Optional.empty()).stream()
                .map(this::modelRs2Rs)
                .collect(Collectors.groupingBy(
                        RuleSet::getCatalogId,
                        () -> new TreeMap<>(Comparator.naturalOrder()),
                        Collectors.toList()
                ));
    }

}
