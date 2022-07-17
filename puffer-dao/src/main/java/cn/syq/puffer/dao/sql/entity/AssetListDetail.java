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
 * 资产-名单明细
 * </p>
 *
 * @author shiyuqin
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AssetListDetail implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 对应主表NAME
     */
    @TableField("LIST_NAME")
    private String listName;

    /**
     * 名单值
     */
    @TableField("LIST_VALUE")
    private String listValue;

    /**
     * 来源
     */
    @TableField("SOURCE")
    private String source;

    /**
     * 到期日yyyyMMddHHmmss
     */
    @TableField("DEAD_LINE")
    private String deadLine;

    /**
     * 外键-创建用户ID
     */
    @TableField("CREATE_UID")
    private Integer createUid;

    /**
     * CREATE_TIME
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
