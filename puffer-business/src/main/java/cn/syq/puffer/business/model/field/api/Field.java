package cn.syq.puffer.business.model.field.api;

import lombok.Data;

/**
 *
 * @author shiyuqin
 * @date 2022/9/16 17:57
 */
@Data
public class Field {

    private long id;

    private String name;

    private String label;

    private String description;

    private String classType;

    private boolean listFlag;

    private long hisId;

    private long doId;
}
