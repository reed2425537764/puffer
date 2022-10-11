package cn.syq.puffer.business.model.rule.service;

import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.*;
import cn.syq.puffer.business.model.rule.api.RuleService;
import cn.syq.puffer.dao.sql.entity.ModelRule;
import cn.syq.puffer.dao.sql.entity.ModelRuleHis;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/29 14:39
 */
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleDomainService ruleDomainService;

    @Autowired
    private ProjectDomainService projectDomainService;

    @Autowired
    private RuleSetDomainService ruleSetDomainService;


    @Override
    public ModelRule addRule(ModelRule modelRule) {
        projectDomainService.checkProjectExist(modelRule.getProjectId());
        ruleSetDomainService.checkRuleSetExist(modelRule.getProjectId(), modelRule.getRsId());

        ModelRule modelRuleByLabel = ruleDomainService.queryModelRuleByLabel(modelRule.getProjectId(), modelRule.getLabel());
        if (Objects.nonNull(modelRuleByLabel)) {
            throw new ManagerException(ManagerErrorCode.E20);
        }

        ModelRule modelRuleNew = ruleDomainService.addModelRule(modelRule, Optional.empty());
        ModelRuleHis modelRuleHis = ruleDomainService.addModelRuleHis(modelRuleNew, His.of(HisType.A, ""));
        ModelRule modelRuleUpdate = new ModelRule();
        modelRuleUpdate.setId(modelRuleNew.getId());
        modelRuleUpdate.setHisId(modelRuleHis.getId());
        ruleDomainService.updateModelRule(modelRuleUpdate);

        return modelRuleNew;
    }

    @Override
    public List<ModelRule> listRules(long projectId, long rsId) {
        projectDomainService.checkProjectExist(projectId);
        ruleSetDomainService.checkRuleSetExist(projectId, rsId);
        return ruleDomainService.listModelRule(projectId, Optional.of(rsId));
    }

    @Override
    public ModelRule getRuleMeta(long projectId, long rsId, long ruleId) {
        projectDomainService.checkProjectExist(projectId);
        ruleSetDomainService.checkRuleSetExist(projectId, rsId);
        return ruleDomainService.checkRuleExist(projectId, rsId, ruleId);
    }
}
