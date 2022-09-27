package cn.syq.puffer.manager.model.api.rule;

import lombok.Data;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/27 15:14
 */
@Data
public class RuleMeta {

    private Long id;

    private String name;

    private String label;

    private String type;

    private Boolean enable;

    private Long rsId;

    private String rsLabel;

    private Long version;

    private Long projectId;
}
