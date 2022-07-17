package cn.syq.puffer.business.exception;

import lombok.Getter;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/16 22:10
 */
@Getter
public class ManagerException extends RuntimeException{

    private ManagerErrorCode code;

    private String desc;

    public ManagerException(ManagerErrorCode code) {
        super(code.getDesc());
        this.code = code;
        this.desc = code.getDesc();
    }

    public ManagerException(ManagerErrorCode code, String desc) {
        super(desc);
        this.code = code;
        this.desc = desc;
    }

    public ManagerException(String code, String desc, Throwable throwable) {
        super(desc, throwable);
    }
}
