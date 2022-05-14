package org.devops.iweb.xxl.configuration;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
	    "org.devops.iweb.xxl"
	})
@Slf4j
public class IWebXxlExecutorConfiguration{

	@Value("${xxl.job.admin.addresses:null}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname:null}")
    private String appName;

    @Value("${xxl.job.executor.ip:null}")
    private String ip;

    @Value("${xxl.job.executor.port:0}")
    private int port;

    @Value("${xxl.job.accessToken:null}")
    private String accessToken;

    @Value("${xxl.job.executor.logpath:null}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays:0}")
    private int logRetentionDays;
    
    
    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init.");
        if(adminAddresses == null || "null".equalsIgnoreCase(adminAddresses.trim())) {
        	adminAddresses = null;
        }
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppName(appName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }
}
