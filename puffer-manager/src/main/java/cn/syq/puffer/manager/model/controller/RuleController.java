package cn.syq.puffer.manager.model.controller;

import cn.syq.puffer.business.model.rule.api.RuleService;
import cn.syq.puffer.dao.sql.entity.ModelRule;
import cn.syq.puffer.manager.model.api.ManagerResponse;
import cn.syq.puffer.manager.model.api.rule.RuleMeta;
import cn.syq.puffer.manager.model.api.rule.RuleNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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


    @PutMapping("{projectId:\\d+/ruleset/{rsId:\\d+/rule}}")
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
}
