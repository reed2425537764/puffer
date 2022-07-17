package cn.syq.puffer.dao.sql.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 模型-项目
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModelProject implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 项目组
     */
    @TableField("GROUP_ID")
    private String groupId;

    /**
     * 项目名称
     */
    @TableField("ARTIFACT_ID")
    private String artifactId;

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
     * 外键-当前历史表主键
     */
    @TableField("HIS_ID")
    private Long hisId;

    /**
     * 外键-当前部署ID
     */
    @TableField("DEPLOY_ID")
    private Long deployId;

    /**
     * 外键-创建用户ID
     */
    @TableField(value = "CREATE_UID", fill = FieldFill.INSERT)
    private Integer createUid;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 外键-更新用户ID
     */
    @TableField(value = "UPDATE_UID",fill = FieldFill.INSERT_UPDATE)
    private Integer updateUid;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
