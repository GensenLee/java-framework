package org.devops.core.utils.modules.apiEnhancer.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "org.devops.core.utils.modules.apiEnhancer"
})
public class ApiEnhancerConfiguration {

}
