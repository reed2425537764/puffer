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
 * 模型-数据对象目录
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModelDoCatalog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 目录名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 显示标签
     */
    @TableField("LABEL")
    private String label;

    /**
     * 外键-项目ID
     */
    @TableField("PROJECT_ID")
    private Long projectId;

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
