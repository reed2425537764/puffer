package cn.syq.puffer.manager.model.api.ruleset;

import lombok.Data;

import java.util.List;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/21 14:03
 */
@Data
public class RsCatalogMeta {

    private Long id;

    private Long projectId;

    private String name;

    private String label;

    private List<RuleSetMeta> ruleSetMetas;
}
