package cn.syq.puffer.business.model.rule.api.json;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/10/11 14:07
 */
@Data
public class RuleJson {

    /**
     * 存储数据对象的name
     */
    private List<String> imports = Lists.newArrayList();

    /**
     * 存放条件定义
     */
    private List<Condition> conditions = Lists.newArrayList();

    /**
     * 新增或更新数据对象结果
     */
    private List<SubAction> actions = Lists.newArrayList();
}
