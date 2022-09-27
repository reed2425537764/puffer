package cn.syq.puffer.business.model.api;

import cn.syq.puffer.business.model.rule.api.Rule;
import cn.syq.puffer.dao.sql.entity.ModelRule;

import java.util.List;
import java.util.Map;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/26 15:33
 */
public interface RuleDomainService {

    List<ModelRule> listModelRule(long projectId);

    Map<Long, List<Rule>> listRuleGroupByCatalogId(long projectId);

    Rule modelRule2Rule(ModelRule modelRule);
}
