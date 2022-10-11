package cn.syq.puffer.business.model.rule.api.json;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/10/11 14:57
 */
@Data
public class SubAction extends Fact {

    /**
     * 类型， I或null为insert(null为了兼容历史) U或true为update(true为了兼容历史) L为list T为tag
     */
    private String flag;

    /**
     * 当flag为T时，index 0 表示对客户ID的引用; index 1 表示tag的值
     */
    private List<ReferenceFieldValue> constraints;
}
