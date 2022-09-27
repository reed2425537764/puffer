package cn.syq.puffer.business.model.ruleset.api;

import cn.syq.puffer.business.model.rule.api.Rule;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/26 15:13
 */
@Data
public class RuleSet {

    private long id;

    private String name;

    private String label;

    private long hisId;

    private long catalogId;

    private List<Rule> rules = Lists.newArrayList();
}

