package cn.syq.puffer.manager.model.api.ruleset;

import cn.syq.puffer.manager.model.api.rule.RuleMeta;
import lombok.Data;

import java.util.List;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/22 14:44
 */
@Data
public class RuleSetMeta {

    private Long id;

    private Long projectId;

    private Long catalogId;

    private String catalogLabel;

    private String name;

    private String label;

    private String type;

    private Long version;

    private List<RuleMeta> ruleMetas;

//    private List<BpmMeta>;
}
