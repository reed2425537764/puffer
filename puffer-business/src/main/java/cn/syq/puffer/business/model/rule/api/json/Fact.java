package cn.syq.puffer.business.model.rule.api.json;

import lombok.Data;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/10/11 14:28
 */
@Data
public class Fact {

    /**
     * 数据对象ID或TAGID
     */
    private long doId;

    /**
     * 数据对象label
     */
    private String text;

    /**
     * 数据对象类型或TAG名称
     */
    private String factType;

    /**
     * 数据对象dotype
     */
    private String doType;

    /**
     * 绑定变量
     */
    private String bindingVar;
}
