package cn.syq.puffer.manager.model.api;

import lombok.Data;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/17 16:43
 */
@Data
public class ManagerResponseHead {

    private long requestTime;

    private long receiveTime;

    private long responseTime;

    private String reqSeq;

    private String respSeq;

    private String reqSys;
}
