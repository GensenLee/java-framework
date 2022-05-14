package org.devops.web.test;

import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.modules.redis.configuration.EnableRedis;
import org.devops.core.utils.spring.AbstractSpringStarter;
import org.devops.core.utils.util.IPUtil;
import org.devops.iweb.auth.configuration.EnableIWebAuth;
import org.devops.iweb.file.configuration.EnableIWebFile;
import org.devops.iweb.operationlog.configuration.EnableIWebOperationLog;
import org.devops.iweb.operationlog.enums.SubTableMode;
import org.devops.iweb.xxl.configuration.EnableIWebXxl;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.weixin.configuration.EnableWeixin;
import org.devops.mjar.weixin.configuration.ProfileLoadMode;
import org.devops.web.test.bean.datasource.DataSourceType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication //spring boot启动
@EnableTransactionManagement //事务
@EnableIWebAuth(datasource = DataSourceType.NAME)
// @EnableIWebFile(path = "${uploadPath}")

//xxl
//@EnableIWebXxl(datasource = DataSourceType.NAME)
//@EnableIWebExt
//@EnableMjarMessage

// topic消息中心
// @EnableMjarMessageTopic("TEST")
@EnableIWebOperationLog(datasource = DataSourceType.NAME, subTableMode = SubTableMode.YEAR, enableApi = true)
@EnableIWebFile(datasource = DataSourceType.NAME)
@EnableRedis(prefix = "devops-test")
@EnableWeixin(datasource = DataSourceType.NAME, initialMode = ProfileLoadMode.DatabaseLoader)
@EnableMjarLivePolyv
@Slf4j
public class DevopsTestBootstrap extends AbstractSpringStarter {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(DevopsTestBootstrap.class, args);
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

