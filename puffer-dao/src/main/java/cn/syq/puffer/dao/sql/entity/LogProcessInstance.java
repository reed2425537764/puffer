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
 * 日志-流程实例
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LogProcessInstance implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 流程ID
     */
    @TableField("PROCESS_ID")
    private String processId;

    /**
     * 流程名称
     */
    @TableField("PROCESS_NAME")
    private String processName;

    /**
     * 流程版本
     */
    @TableField("PROCESS_VERSION")
    private Integer processVersion;

    /**
     * 流程实例ID
     */
    @TableField("PROCESS_INSTANCE_ID")
    private Long processInstanceId;

    /**
     * 状态
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 事件类型
     */
    @TableField("EVENT_TYPE")
    private String eventType;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;


}
