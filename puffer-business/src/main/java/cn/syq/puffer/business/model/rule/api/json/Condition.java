package cn.syq.puffer.business.model.rule.api.json;

import lombok.Data;

import java.util.List;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/10/11 14:26
 */
@Data
public class Condition {

    /**
     * 多数据对象关系
     * 可选值exists/not/or
     */
    private String compositeType;

    /**
     * 数据对象约束
     */
    private List<SubPattern> subPatterns;
}
