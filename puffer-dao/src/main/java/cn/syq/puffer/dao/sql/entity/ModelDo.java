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
 * 模型-数据对象
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModelDo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 类名simpleName
     */
    @TableField("CLASS_NAME")
    private String className;

    /**
     * 显示标签
     */
    @TableField("LABEL")
    private String label;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 外键-项目ID
     */
    @TableField("PROJECT_ID")
    private Long projectId;

    /**
     * 外键-历史表ID
     */
    @TableField("HIS_ID")
    private Long hisId;

    /**
     * 外键-目录ID
     */
    @TableField("CATALOG_ID")
    private Long catalogId;

    /**
     * 数据对象类型
     */
    @TableField("DO_TYPE")
    private String doType;

    /**
     * 外键-创建用户ID
     */
    @TableField("CREATE_UID")
    private Integer createUid;

    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 外键-更新用户ID
     */
    @TableField("UPDATE_UID")
    private Integer updateUid;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


}
