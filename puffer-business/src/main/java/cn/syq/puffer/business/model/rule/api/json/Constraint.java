package cn.syq.puffer.business.model.rule.api.json;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/10/11 14:37
 */
@Data
public class Constraint {

    /**
     * 多数据对象关系
     * 可选值exists/not/or
     */
    private String compositeType;

    /**
     * 字段条件
     */
    private List<SubConstraint> subConstraints = Lists.newArrayList();

}
