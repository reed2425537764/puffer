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
 * 日志-节点实例
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LogNodeInstance implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 节点实例ID
     */
    @TableField("NODE_INSTANCE_ID")
    private String nodeInstanceId;

    /**
     * 事件类型
     */
    @TableField("EVENT_TYPE")
    private String eventType;

    /**
     * 连接方向
     */
    @TableField("CONNECTION")
    private String connection;

    /**
     * 实例流程ID
     */
    @TableField("PROCESS_INSTANCE_ID")
    private Long processInstanceId;

    /**
     * 节点ID
     */
    @TableField("NODE_ID")
    private String nodeId;

    /**
     * 节点名称
     */
    @TableField("NODE_NAME")
    private String nodeName;

    /**
     * 节点类型
     */
    @TableField("NODE_TYPE")
    private String nodeType;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;


}
