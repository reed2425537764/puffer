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
 * 模型-规则
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModelRule implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 显示标签
     */
    @TableField("LABEL")
    private String label;

    /**
     * 外键-规则集ID
     */
    @TableField("RS_ID")
    private Long rsId;

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
     * 规则类型
     */
    @TableField("RULE_TYPE")
    private String ruleType;

    /**
     * 规则语法
     */
    @TableField("DRL")
    private String drl;

    /**
     * 是否启用Y/N
     */
    @TableField("ENABLE")
    private String enable;

    /**
     * 外键-创建用户ID
     */
    @TableField("CREATE_UID")
    private Integer createUid;

    /**
     * 创建时间
     */
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
