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
 * 模型-规则流历史表
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModelBpmHis implements Serializable {

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
     * 流程ID
     */
    @TableField("PROCESS_ID")
    private String processId;

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
     * 规则流内容
     */
    @TableField("BPMN")
    private String bpmn;

    /**
     * 外键-项目ID
     */
    @TableField("PROJECT_ID")
    private Long projectId;

    /**
     * 备注
     */
    @TableField("COMMENT")
    private String comment;

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


}
