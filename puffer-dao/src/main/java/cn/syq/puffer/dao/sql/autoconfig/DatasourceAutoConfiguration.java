package cn.syq.puffer.dao.sql.autoconfig;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/16 22:28
 */
@ConditionalOnBean({MybatisPlusProperties.class})
@AutoConfigureAfter({MybatisPlusAutoConfiguration.class})
public class DatasourceAutoConfiguration {

    @Bean
    public MetaObjectHandler metaAutoInjectHandler() {
        return new MetaAutoInjectHandler();
    }
}
