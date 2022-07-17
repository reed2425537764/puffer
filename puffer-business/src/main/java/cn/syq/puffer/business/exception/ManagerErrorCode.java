package cn.syq.puffer.business.exception;

import lombok.Getter;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/16 22:14
 */
@Getter
public enum ManagerErrorCode {

    E00("00", "成功"),

    E10("10", "参数校验类错误"),

    ;

    private final String code;

    private final String desc;

    ManagerErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
