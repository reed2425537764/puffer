package cn.syq.puffer.business.model.rule.api.json;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/10/11 14:38
 */
public class SubConstraint extends Field {

    /**
     * 操作符
     */
    private String operator;

    /**
     * 文本值
     */
    private String literalValue;

    private FactReference ref;
}
