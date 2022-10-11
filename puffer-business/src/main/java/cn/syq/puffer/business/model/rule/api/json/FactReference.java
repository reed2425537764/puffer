package cn.syq.puffer.business.model.rule.api.json;

import lombok.Data;

/**
 * 引用类型的值
 *
 * @author shiyuqin
 * @date 2022/10/11 14:41
 */
@Data
public class FactReference extends Fact{

    /**
     * 所引用的字段
     */
    private Field field;
}
