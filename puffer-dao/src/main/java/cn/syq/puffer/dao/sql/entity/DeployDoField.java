package cn.syq.puffer.dao.sql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 模型-数据对象与字段关系
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeployDoField implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 外键-部署ID
     */
    @TableField("DEPLOY_ID")
    private Long deployId;

    /**
     * 外键-数据对象历史ID
     */
    @TableField("DO_HIS_ID")
    private Long doHisId;

    /**
     * 外键-字段ID
     */
    @TableField("FIELD_ID")
    private Long fieldId;

    /**
     * 外键-字段历史ID
     */
    @TableField("FIELD_HIS_ID")
    private Long fieldHisId;

    /**
     * 字段名称
     */
    @TableField("FIELD_NAME")
    private String fieldName;

    /**
     * 字段数据类型
     */
    @TableField("CLASS_TYPE")
    private String classType;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;


}
