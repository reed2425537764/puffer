package cn.syq.puffer.business.model.ruleset.api;

import lombok.Data;

import java.util.List;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/27 14:45
 */
@Data
public class RsCatalog {

    private Long id;

    private String name;

    private String label;

    private List<RuleSet> ruleSets;
}
