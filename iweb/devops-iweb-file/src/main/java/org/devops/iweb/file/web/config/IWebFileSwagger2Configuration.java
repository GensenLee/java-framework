package org.devops.iweb.file.web.config;

import org.devops.core.utils.modules.swagger.EnableSwaggerUIExt;
import org.devops.iweb.file.web.controller.IWebFileOssController;
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
public class IWebFileSwagger2Configuration {

    private static final String MODULE_NAME = "devops-iweb-file";

    @Bean
    public Docket iwebFileCreateRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(IWebFileOssController.class.getPackage().getName()))
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
