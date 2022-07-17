package cn.syq.puffer.manager.autoconfig;

import cn.syq.puffer.business.context.ManagerContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HandlerInterceptorAdapter
 * @Author: shiyuqin
 * @Date: 2022/7/17 18:53
 */
public class ManagerContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ManagerContext context = ManagerContext.getContext();
        context.setReceiveTime(System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ManagerContext.clearContext();
    }
}
