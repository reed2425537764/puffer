package cn.syq.puffer.business.model.rule.api.json;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/10/11 14:43
 */
public class SubPattern extends Fact{

    /**
     * 数据对象约束
     */
    private List<Constraint> constraints = Lists.newArrayList();
}
