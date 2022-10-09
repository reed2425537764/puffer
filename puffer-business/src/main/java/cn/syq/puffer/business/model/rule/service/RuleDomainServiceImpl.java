package cn.syq.puffer.business.model.rule.service;

import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.His;
import cn.syq.puffer.business.model.api.RuleDomainService;
import cn.syq.puffer.business.model.rule.api.Rule;
import cn.syq.puffer.business.utils.ModelUtils;
import cn.syq.puffer.business.utils.StringTools;
import cn.syq.puffer.dao.sql.entity.ModelRule;
import cn.syq.puffer.dao.sql.entity.ModelRuleHis;
import cn.syq.puffer.dao.sql.mapper.ModelRuleHisMapper;
import cn.syq.puffer.dao.sql.mapper.ModelRuleMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.*;
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
    public ModelRule queryModelRuleByLabel(long projectId, String label) {
        LambdaQueryWrapper<ModelRule> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelRule::getProjectId, projectId).eq(ModelRule::getLabel, label);
        return modelRuleMapper.selectOne(wrapper);
    }

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
    public ModelRule addModelRule(ModelRule modelRule, Optional<String> drlOpt) {
        modelRule.setDrl(drlOpt.orElse("drl"));
        modelRule.setName(ModelUtils.generateModelName());
        modelRule.setEnable(Boolean.TRUE.toString());
        modelRule.setHisId(0L);
        modelRule.setRuleType(getInnerRuleType(modelRule.getRuleType()));
        modelRuleMapper.insert(modelRule);
        return modelRule;
    }

    @Override
    public ModelRuleHis addModelRuleHis(ModelRule modelRule, His his) {
        ModelRuleHis modelRuleHis = new ModelRuleHis();
        BeanCopier beanCopier = BeanCopier.create(ModelRule.class, ModelRuleHis.class, false);
        beanCopier.copy(modelRule, modelRuleHis, null);
        modelRuleHis.setForeignId(modelRule.getId());
        modelRuleHis.setType(his.getType().name());
        modelRuleHis.setComment(his.getRemark());
        modelRuleHisMapper.insert(modelRuleHis);
        return modelRuleHis;
    }

    @Override
    public int updateModelRule(ModelRule modelRule) {
        LambdaUpdateWrapper<ModelRule> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ModelRule::getId, modelRule.getId());
        wrapper.set(StringTools.isNotBlank(modelRule.getLabel()), ModelRule::getLabel, modelRule.getLabel());
        wrapper.set(StringTools.isNotBlank(modelRule.getRuleType()), ModelRule::getRuleType, modelRule.getRuleType());
        wrapper.set(StringTools.isNotBlank(modelRule.getDrl()), ModelRule::getDrl, modelRule.getDrl());
        wrapper.set(StringTools.isNotBlank(modelRule.getEnable()), ModelRule::getEnable, modelRule.getEnable());
        wrapper.set(Objects.nonNull(modelRule.getRsId()), ModelRule::getRsId, modelRule.getRsId());
        wrapper.set(Objects.nonNull(modelRule.getHisId()), ModelRule::getHisId, modelRule.getHisId());
        return modelRuleMapper.update(null, wrapper);
    }

    @Override
    public Rule modelRule2Rule(ModelRule modelRule) {
        Rule rule = new Rule();
        BeanCopier beanCopier = BeanCopier.create(ModelRule.class, Rule.class, false);
        beanCopier.copy(modelRule, rule, null);
        return rule;
    }

    @Override
    public String getInnerRuleType(String outer) {
        return switch (outer) {
            case "0" -> RULE_TYPE_DEFAULT;
            case "1" -> RULE_TYPE_STRATEGY;
            case "2" -> RULE_TYPE_TEST;
            case "3" -> RULE_TYPE_ADD_LIST;
            case "4" -> RULE_TYPE_NULL_RUN;
            case "5" -> RULE_TYPE_VALUATION;
            case "6" -> RULE_TYPE_TAG;
            default -> throw new ManagerException(ManagerErrorCode.E20);
        };
    }
}
