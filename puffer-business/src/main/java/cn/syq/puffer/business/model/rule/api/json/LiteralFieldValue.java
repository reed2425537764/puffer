package cn.syq.puffer.business.model.rule.api.json;

import lombok.Data;

/**
 * 值为文本的结果字段
 *
 * @author shiyuqin
 * @date 2022/10/11 15:01
 */
@Data
public class LiteralFieldValue extends Field{

    private String literalField;
}
