package org.devops.web.template;

import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.spring.AbstractSpringStarter;
import org.devops.core.utils.util.IPUtil;
import org.devops.mjar.weixin.configuration.EnableWeixin;
import org.devops.mjar.weixin.configuration.ProfileLoadMode;
import org.devops.web.template.config.datasource.DevopsDataSourceType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@Slf4j
@SpringBootApplication
public class WebTemplateApplication extends AbstractSpringStarter {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(WebTemplateApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = IPUtil.getLocalIP();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path", "");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "Swagger-UI: \t\thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");
    }
}
