package cn.syq.puffer.business.model.rule.api;

import lombok.Data;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/26 15:34
 */
@Data
public class Rule {

    private long id;

    private String name;

    private String label;

    private String ruleType;

    private boolean enable;

    private String drl;

    private long rsId;

    private long hisId;

}
