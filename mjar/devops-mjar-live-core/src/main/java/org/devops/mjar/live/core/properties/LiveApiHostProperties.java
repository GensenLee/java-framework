package org.devops.mjar.live.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author GENSEN
 * @date 2020/11/25 9:43
 * @description：默认域名配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "live.host")
@PropertySource(value = "classpath:base-live.properties")
public class LiveApiHostProperties {

    /**
     * 默认接口域名
     */
    private String api;
    /**
     * 默认直播域名
     */
    private String live;
    /**
     * 默认开播域名
     */
    private String start;
}
