package cn.syq.puffer.business.model.api;

import cn.syq.puffer.business.model.rule.api.Rule;
import cn.syq.puffer.dao.sql.entity.ModelRule;
import cn.syq.puffer.dao.sql.entity.ModelRuleHis;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/26 15:33
 */
public interface RuleDomainService {

    /**
     * 其他
     */
    String RULE_TYPE_DEFAULT = "00";

    /**
     * 策略
     */
    String RULE_TYPE_STRATEGY = "01";

    /**
     * 测试规则
     */
    String RULE_TYPE_TEST = "02";

    /**
     * 加名单
     */
    String RULE_TYPE_ADD_LIST = "03";

    /**
     * 空跑
     */
    String RULE_TYPE_NULL_RUN = "04";

    /**
     * 打标签
     */
    String RULE_TYPE_VALUATION = "05";

    /**
     * 打标签
     */
    String RULE_TYPE_TAG = "06";

    ModelRule queryModelRuleByLabel(long projectId, String label);

    List<ModelRule> listModelRule(long projectId);

    Map<Long, List<Rule>> listRuleGroupByCatalogId(long projectId);

    ModelRule addModelRule(ModelRule modelRule, Optional<String> drlOpt);

    ModelRuleHis addModelRuleHis(ModelRule modelRule, His his);

    int updateModelRule(ModelRule modelRule);

    Rule modelRule2Rule(ModelRule modelRule);

    String getInnerRuleType(String outer);
}
