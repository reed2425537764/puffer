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
 * 部署-相关模型内容
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeployContext implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 外键-部署ID
     */
    @TableField("DEPLOY_ID")
    private Long deployId;

    /**
     * 模型内容类型
     */
    @TableField("TYPE")
    private String type;

    /**
     * 外键-该模型内容对应主表ID
     */
    @TableField("MAIN_ID")
    private Long mainId;

    /**
     * 外键-该模型内容对应历史表ID
     */
    @TableField("HIS_ID")
    private Long hisId;

    /**
     * 外键-该模型上级模型的历史表ID
     */
    @TableField("SUPERIOR_HIS_ID")
    private Long superiorHisId;

    /**
     * 模型名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 显示标签
     */
    @TableField("LABEL")
    private String label;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;


}
