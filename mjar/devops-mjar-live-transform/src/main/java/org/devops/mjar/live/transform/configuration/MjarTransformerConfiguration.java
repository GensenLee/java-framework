package org.devops.mjar.live.transform.configuration;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author GENSEN
 * @date 2021/6/28 11:32
 * @description：配置
 */
@ComponentScan(basePackages = {
        "org.devops.mjar.live.transform"
})
@Configuration
@ServletComponentScan("org.devops.mjar.live.transform.filter")
public class MjarTransformerConfiguration {
}
