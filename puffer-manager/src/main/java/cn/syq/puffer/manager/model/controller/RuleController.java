package cn.syq.puffer.manager.model.controller;

import cn.syq.puffer.business.model.rule.api.RuleContext;
import cn.syq.puffer.business.model.rule.api.RuleService;
import cn.syq.puffer.dao.sql.entity.ModelRule;
import cn.syq.puffer.manager.model.api.ManagerResponse;
import cn.syq.puffer.manager.model.api.rule.RuleMeta;
import cn.syq.puffer.manager.model.api.rule.RuleNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/29 14:31
 */
@RestController
@RequestMapping("/model/rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;


    @PutMapping("{projectId:\\d+}/ruleset/{rsId:\\d+/rule}")
    @Valid
    public ManagerResponse<RuleMeta> addRule(@RequestBody RuleNew ruleNew) {
        ModelRule modelRule = new ModelRule();
        BeanCopier beanCopier = BeanCopier.create(RuleNew.class, ModelRule.class, false);
        beanCopier.copy(ruleNew, modelRule, null);

        ModelRule modelRuleNew = ruleService.addRule(modelRule);

        RuleMeta ruleMeta = new RuleMeta();
        ruleMeta.setId(modelRuleNew.getId());
        return ManagerResponse.buildSuccess(ruleMeta);
    }

    @GetMapping("{projectId:\\d+}/ruleset/{rsId:\\d+/rule}")
    @Valid
    public ManagerResponse<List<RuleMeta>> listRules(@PathVariable("projectId") Long projectId, @PathVariable("rsId") Long rsId) {
        return ManagerResponse.buildSuccess(ruleService.listRules(projectId, rsId).stream().map(modelRule -> {
            RuleMeta ruleMeta = new RuleMeta();
            ruleMeta.setId(modelRule.getId());
            ruleMeta.setName(modelRule.getName());
            ruleMeta.setLabel(modelRule.getLabel());
            return ruleMeta;
        }).collect(Collectors.toList()));
    }

    @GetMapping("{projectId:\\d+}/ruleset/{rsId:\\d+}/rule/{ruleId:\\d+}/meta")
    @Valid
    public ManagerResponse<RuleMeta> getRuleMeta(@PathVariable("projectId") Long projectId,
                                                 @PathVariable("rsId") Long rsId,
                                                 @PathVariable("ruleId") Long ruleId) {
        ModelRule modelRule = ruleService.getRuleMeta(projectId, rsId, ruleId);
        RuleMeta ruleMeta = new RuleMeta();
        ruleMeta.setId(modelRule.getId());
        ruleMeta.setName(modelRule.getName());
        ruleMeta.setLabel(modelRule.getLabel());
        return ManagerResponse.buildSuccess(ruleMeta);
    }

    @GetMapping("{projectId:\\d+}/ruleset/{rsId:\\d+}/rule/{ruleId:\\d+}/context")
    @Valid
    public ManagerResponse<RuleContext> getRuleContext(@PathVariable("projectId") Long projectId,
                                                       @PathVariable("rsId") Long rsId,
                                                       @PathVariable("ruleId") Long ruleId) {
        return null;
    }
}
