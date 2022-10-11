package cn.syq.puffer.business.model.rule.api.json;

import lombok.Data;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/10/11 14:38
 */
@Data
public class Field {

    private long fieldId;

    private String fieldName;

    private String fieldType;

    private Boolean isList;
}
