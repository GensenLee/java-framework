package org.devops.web.template.config.configuration;

import org.devops.core.utils.modules.swagger.EnableSwaggerUIExt;
import org.devops.web.template.controller.HealthController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableSwaggerUIExt
public class Swagger2Configuration {

    @Bean
    public Docket moduleSwagger2Docket(@Value("${spring.application.name:}") String moduleName) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(moduleName)
                        .description(moduleName)
                        .version("1.0.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage(HealthController.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .groupName(moduleName);
    }

}
