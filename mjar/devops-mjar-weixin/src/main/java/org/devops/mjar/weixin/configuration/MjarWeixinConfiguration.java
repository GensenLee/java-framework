package org.devops.mjar.weixin.configuration;

import lombok.extern.slf4j.Slf4j;
import org.devops.core.model.configuration.EnableModel;
import org.devops.core.utils.modules.redis.configuration.EnableRedis;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author GENSEN
 * @date 2021/9/9 15:21
 * @description：配置
 */
@Slf4j
@ComponentScan(basePackages = {
        "org.devops.mjar.weixin"
})
@Configuration
@EnableRedis
@EnableModel
@EnableConfigurationProperties({WxMaProperties.class, WxMpProperties.class, WxPayProperties.class, WxOpenProperties.class})
public class MjarWeixinConfiguration {
}
