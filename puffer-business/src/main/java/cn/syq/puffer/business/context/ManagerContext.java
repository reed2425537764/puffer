package cn.syq.puffer.business.context;

import lombok.Data;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/17 17:51
 */
@Data
public class ManagerContext {

    private static final ThreadLocal<ManagerContext> MANAGER_CONTEXT = ThreadLocal.withInitial(ManagerContext::new);

    private long requestTime;

    private long receiveTime;

    private String reqSeq;

    private String respSeq;

    private String reqSys;

    public static ManagerContext getContext() {
        return MANAGER_CONTEXT.get();
    }

    public static void clearContext() {
        MANAGER_CONTEXT.remove();
    }
}
