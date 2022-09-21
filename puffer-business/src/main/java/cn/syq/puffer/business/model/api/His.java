package cn.syq.puffer.business.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/21 11:03
 */
@Data
@AllArgsConstructor
public class His {

    private HisType type;

    private String remark;

    public static His of(HisType type, String remark) {
        return new His(type, remark);
    }
}
