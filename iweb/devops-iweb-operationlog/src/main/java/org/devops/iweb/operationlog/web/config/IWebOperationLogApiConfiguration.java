package org.devops.iweb.operationlog.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author GENSEN
 */
@Configuration
@ComponentScan(basePackages = {
        "org.devops.iweb.operationlog.web",
        "org.devops.iweb.operationlog.service"
})
public class IWebOperationLogApiConfiguration {

}
