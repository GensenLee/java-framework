package org.devops.web.test.bean.configuration;

import org.devops.core.utils.modules.swagger.EnableSwaggerUIExt;
import org.devops.web.test.controller.HealthController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spi.service.contexts.DocumentationContextBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


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
