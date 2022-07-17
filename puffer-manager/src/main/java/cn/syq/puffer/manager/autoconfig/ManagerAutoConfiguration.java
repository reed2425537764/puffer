package cn.syq.puffer.manager.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/17 15:59
 */
@Profile("manager")
@AutoConfigureAfter({ServletWebServerFactoryAutoConfiguration.class})
public class ManagerAutoConfiguration {

    @Bean
    public HandlerInterceptor managerContextInterceptor() {
        return new ManagerContextInterceptor();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new ManagerWebMvcConfig();
    }
}
