package cn.syq.puffer.manager.model.api;

import cn.syq.puffer.business.context.ManagerContext;
import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.utils.ModelUtils;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/17 16:42
 */
@Data
@Builder
public class ManagerResponse<T> {

    private String code;

    private String desc;

    private ManagerResponseHead head;

    private T data;

    public static <R> ManagerResponse<R> buildSuccess(R data) {
        ManagerResponseHead head = new ManagerResponseHead();
        ManagerContext context = ManagerContext.getContext();
        head.setReqSeq(context.getReqSeq());
        head.setRequestTime(context.getRequestTime());
        head.setReceiveTime(context.getReceiveTime());
        head.setReqSys(context.getReqSys());
        head.setResponseTime(System.currentTimeMillis());
        head.setRespSeq(ModelUtils.generateModelName());
        return ManagerResponse.<R>builder()
                .code(ManagerErrorCode.E00.getCode())
                .desc(ManagerErrorCode.E00.getDesc())
                .head(head)
                .data(data)
                .build();
    }
}
