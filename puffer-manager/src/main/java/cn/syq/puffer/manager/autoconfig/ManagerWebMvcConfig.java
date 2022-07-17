package cn.syq.puffer.manager.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/17 23:07
 */
public class ManagerWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private List<HandlerInterceptor> interceptors;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        interceptors.forEach(interceptor -> registry.addInterceptor(interceptor).addPathPatterns("/**"));
    }
}
