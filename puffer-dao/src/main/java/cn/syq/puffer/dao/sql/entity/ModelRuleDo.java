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
 * 模型-规则与数据对象关系
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModelRuleDo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 外键-规则ID
     */
    @TableField("RULE_ID")
    private Long ruleId;

    /**
     * 外键-规则历史ID
     */
    @TableField("RULE_HIS_ID")
    private Long ruleHisId;

    /**
     * 外键-数据对象ID
     */
    @TableField("DO_ID")
    private Long doId;

    /**
     * 外键-字段ID
     */
    @TableField("FIELD_ID")
    private Long fieldId;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;


}
