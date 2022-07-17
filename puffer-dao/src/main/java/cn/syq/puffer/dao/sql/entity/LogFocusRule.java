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
 * 日志-规则命中
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LogFocusRule implements Serializable {

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
     * 外键-部署ID
     */
    @TableField("DEPLOY_ID")
    private Long deployId;

    /**
     * 实例流程ID
     */
    @TableField("PROCESS_INSTANCE_ID")
    private Long processInstanceId;

    /**
     * 规则流ID
     */
    @TableField("PROCESS_ID")
    private String processId;

    /**
     * 规则集名称
     */
    @TableField("RS_NAME")
    private String rsName;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;


}
