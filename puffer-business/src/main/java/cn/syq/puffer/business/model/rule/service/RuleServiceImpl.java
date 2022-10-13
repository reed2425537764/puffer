package cn.syq.puffer.business.model.rule.service;

import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.*;
import cn.syq.puffer.business.model.rule.api.RuleContext;
import cn.syq.puffer.business.model.rule.api.RuleService;
import cn.syq.puffer.business.model.rule.api.json.RuleJson;
import cn.syq.puffer.dao.sql.entity.ModelDo;
import cn.syq.puffer.dao.sql.entity.ModelRule;
import cn.syq.puffer.dao.sql.entity.ModelRuleHis;
import com.alibaba.fastjson.JSON;
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

    @Autowired
    private DataObjectDomainService dataObjectDomainService;


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

    @Override
    public RuleContext getRuleContext(long projectId, long rsId, long ruleId) {
        projectDomainService.checkProjectExist(projectId);
        ruleSetDomainService.checkRuleSetExist(projectId, rsId);
        ModelRule modelRule = ruleDomainService.checkRuleExist(projectId, rsId, ruleId);

        RuleContext ruleContext = new RuleContext();
        ruleContext.setId(modelRule.getId());
        if (Objects.equals("drl", modelRule.getDrl())) {
            ruleContext.setContext("");
        } else {
            RuleJson ruleJson = JSON.parseObject(modelRule.getDrl(), RuleJson.class);
            ruleJson.getConditions().forEach(condition -> condition.getSubPatterns().forEach(subPattern -> {
                if (subPattern.getDoId() < 0L) {
                    subPattern.setText("系统参数");
                } else {
                    ModelDo modelDo = dataObjectDomainService.checkDoExist(Optional.of(projectId), subPattern.getDoId());
                    subPattern.setText(modelDo.getClassName());
//                    subPattern.setDoType(dataObjectDomainService.getOuterDoType(modelDo.getDoType()));
                }
            }));
            ruleJson.getActions().forEach(subAction -> {
                if (subAction.getDoId() < 0L) {
                    subAction.setText("系统参数");
                } else {
                    ModelDo modelDo = dataObjectDomainService.checkDoExist(Optional.of(projectId), subAction.getDoId());
                    subAction.setText(modelDo.getClassName());
//                    subAction.setDoType(dataObjectDomainService.getOuterDoType(modelDo.getDoType()));
                }
            });
            ruleContext.setContext(JSON.toJSONString(ruleJson));
        }
        return ruleContext;
    }
}
