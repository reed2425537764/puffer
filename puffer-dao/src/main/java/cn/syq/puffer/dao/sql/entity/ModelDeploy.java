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
 * 模型-部署
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModelDeploy implements Serializable {

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
     * 项目名
     */
    @TableField("ARTIFACT_ID")
    private String artifactId;

    /**
     * 显示标签
     */
    @TableField("LABEL")
    private String label;

    /**
     * 发布说明
     */
    @TableField("DEPLOY_DESC")
    private String deployDesc;

    /**
     * 外键-项目ID
     */
    @TableField("PROJECT_ID")
    private Long projectId;

    /**
     * 是否最新(YN)
     */
    @TableField("LATEST")
    private String latest;

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
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


}
