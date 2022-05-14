package org.devops.iweb.operationlog.web.config;

import org.devops.core.utils.modules.swagger.EnableSwaggerUIExt;
import org.devops.iweb.operationlog.web.controller.OperationLogController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableSwaggerUIExt
public class IWebOperationLogSwagger2Configuration {

    private static final String MODULE_NAME = "devops-iweb-operationlog";

    @Bean
    public Docket iwebOperationLogCreateRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(OperationLogController.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .groupName(MODULE_NAME);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(MODULE_NAME)
                .description(MODULE_NAME)
                .version("5.0.0")
                .build();
    }

}
