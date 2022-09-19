package cn.syq.puffer.business.model.field.api;

import lombok.Data;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/19 14:26
 */
@Data
public class FieldMeta {

    private Long id;

    private Long projectId;

    private Long doId;

    private String name;

    private String label;

    private String type;

    private String description;

    private Boolean listFlag;

    private Long version;
}
