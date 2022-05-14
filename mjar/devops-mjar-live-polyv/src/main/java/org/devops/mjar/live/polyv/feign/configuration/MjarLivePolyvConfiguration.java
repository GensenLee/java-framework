package org.devops.mjar.live.polyv.feign.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author GENSEN
 * @date 2020/10/21 12:01
 * @description：
 */
@EnableFeignClients(basePackages = {"org.devops.mjar.live.polyv.feign"})
@ComponentScan(basePackages = {"org.devops.mjar.live.polyv", "org.devops.mjar.live.core"})
@Configuration
public class MjarLivePolyvConfiguration {
}
