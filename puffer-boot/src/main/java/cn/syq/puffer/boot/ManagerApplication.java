package cn.syq.puffer.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/16 20:26
 */
@SpringBootApplication
@MapperScan("cn.syq.puffer.dao.sql.mapper")
@ComponentScan({"cn.syq.puffer.manager","cn.syq.puffer.business"})
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ManagerApplication.class);
        springApplication.setAdditionalProfiles("manager");
        springApplication.run();
    }
}
