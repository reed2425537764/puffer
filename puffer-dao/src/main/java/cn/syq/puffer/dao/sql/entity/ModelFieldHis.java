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
 * 模型-字段历史
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModelFieldHis implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 类型
     */
    @TableField("TYPE")
    private String type;

    /**
     * 外键-关联表主键
     */
    @TableField("FOREIGN_ID")
    private Long foreignId;

    /**
     * 字段名称
     */
    @TableField("NAME")
    private String name;

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
     * 字段数据类型
     */
    @TableField("CLASS_TYPE")
    private String classType;

    /**
     * 是否是列表Y/N
     */
    @TableField("LIST_FLAG")
    private String listFlag;

    /**
     * 外键-最新的字段历史ID
     */
    @TableField("HIS_ID")
    private Long hisId;

    /**
     * 外键-项目ID
     */
    @TableField("PROJECT_ID")
    private Long projectId;

    /**
     * 外键-数据对象ID
     */
    @TableField("DO_ID")
    private Long doId;

    /**
     * 备注
     */
    @TableField("COMMENT")
    private String comment;

    /**
     * 外键-创建用户ID
     */
    @TableField("CREATE_UID")
    private Long createUid;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;


}
