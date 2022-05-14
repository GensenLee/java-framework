package org.devops.core.utils.modules.swagger;

import org.devops.core.utils.spring.configsupport.DevopsModuleConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@ComponentScan(basePackages = {
    "org.devops.core.utils.modules.swagger"
})
public class SwaggerUIExtConfiguration implements DevopsModuleConfiguration {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
