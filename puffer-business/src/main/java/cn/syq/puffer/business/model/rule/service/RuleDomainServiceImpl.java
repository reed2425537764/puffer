package cn.syq.puffer.business.model.rule.service;

import cn.syq.puffer.business.model.api.RuleDomainService;
import cn.syq.puffer.business.model.rule.api.Rule;
import cn.syq.puffer.dao.sql.entity.ModelRule;
import cn.syq.puffer.dao.sql.mapper.ModelRuleHisMapper;
import cn.syq.puffer.dao.sql.mapper.ModelRuleMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/26 15:33
 */
public class RuleDomainServiceImpl implements RuleDomainService {

    @Autowired
    private ModelRuleMapper modelRuleMapper;

    @Autowired
    private ModelRuleHisMapper modelRuleHisMapper;


    @Override
    public List<ModelRule> listModelRule(long projectId) {
        LambdaQueryWrapper<ModelRule> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelRule::getProjectId, projectId);
        return modelRuleMapper.selectList(wrapper);
    }

    @Override
    public Map<Long, List<Rule>> listRuleGroupByCatalogId(long projectId) {
        return listModelRule(projectId).stream()
                .map(this::modelRule2Rule)
                .collect(Collectors.groupingBy(
                        Rule::getRsId,
                        TreeMap::new,
                        Collectors.toList()
                ));
    }

    @Override
    public Rule modelRule2Rule(ModelRule modelRule) {
        Rule rule = new Rule();
        BeanCopier beanCopier = BeanCopier.create(ModelRule.class, Rule.class, false);
        beanCopier.copy(modelRule, rule, null);
        return rule;
    }
}
