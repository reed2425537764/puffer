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
 * 部署-规则集与数据对象关系
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeployRsDo implements Serializable {

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
     * 外键-规则集历史ID
     */
    @TableField("RS_HIS_ID")
    private Long rsHisId;

    /**
     * 外键-数据对象ID
     */
    @TableField("DO_ID")
    private Long doId;

    /**
     * 外键-数据对象历史ID
     */
    @TableField("DO_HIS_ID")
    private Long doHisId;

    /**
     * 包名
     */
    @TableField("PACKAGE_NAME")
    private String packageName;

    /**
     * 类名
     */
    @TableField("CLASS_NAME")
    private String className;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;


}
