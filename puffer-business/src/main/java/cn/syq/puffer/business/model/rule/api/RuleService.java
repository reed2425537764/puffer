package cn.syq.puffer.business.model.rule.api;

import cn.syq.puffer.dao.sql.entity.ModelRule;

import java.util.List;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/29 14:38
 */
public interface RuleService {

    ModelRule addRule(ModelRule modelRule);

    List<ModelRule> listRules(long projectId, long rsId);

    ModelRule getRuleMeta(long projectId, long rsId, long ruleId);

    RuleContext getRuleContext(long projectId, long rsId, long ruleId);
}
