package org.devops.iweb.xxl.conf;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.devops.iweb.xxl.repository.XxlJobGroupRepository;
import org.devops.iweb.xxl.repository.XxlJobInfoRepository;
import org.devops.iweb.xxl.repository.XxlJobLogRepository;
import org.devops.iweb.xxl.repository.XxlJobRegistryRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.AdminBiz;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */
@Component
public class XxlJobAdminConfig implements InitializingBean{
    private static XxlJobAdminConfig adminConfig = null;
    public static XxlJobAdminConfig getAdminConfig() {
        return adminConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        adminConfig = this;
    }
    
    // conf
    @Value("${xxl.job.i18n}")
    private String i18n;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${spring.mail.username}")
    private String emailUserName;

    // dao, service

    @Autowired
    private XxlJobLogRepository xxlJobLogRepository;
    
    @Autowired
    private XxlJobInfoRepository xxlJobInfoRepository;
    
    @Autowired
    private XxlJobRegistryRepository xxlJobRegistryRepository;
    
    @Autowired
    private XxlJobGroupRepository xxlJobGroupRepository;
    
    @Resource
    private AdminBiz adminBiz;
    
    @Resource
    private JavaMailSender mailSender;
    
    @Resource
    private DataSource dataSource;


    public String getI18n() {
        return i18n;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getEmailUserName() {
        return emailUserName;
    }

    

    public XxlJobLogRepository getXxlJobLogRepository() {
		return (XxlJobLogRepository)xxlJobLogRepository.closeLog();
	}

	public void setXxlJobLogRepository(XxlJobLogRepository xxlJobLogRepository) {
		this.xxlJobLogRepository = xxlJobLogRepository;
	}

	public XxlJobInfoRepository getXxlJobInfoRepository() {
		return (XxlJobInfoRepository)xxlJobInfoRepository.closeLog();
	}

	public void setXxlJobInfoRepository(XxlJobInfoRepository xxlJobInfoRepository) {
		this.xxlJobInfoRepository = xxlJobInfoRepository;
	}

	public XxlJobRegistryRepository getXxlJobRegistryRepository() {
		return (XxlJobRegistryRepository)xxlJobRegistryRepository.closeLog();
	}

	public void setXxlJobRegistryRepository(XxlJobRegistryRepository xxlJobRegistryRepository) {
		this.xxlJobRegistryRepository = xxlJobRegistryRepository;
	}

	public XxlJobGroupRepository getXxlJobGroupRepository() {
		return (XxlJobGroupRepository)xxlJobGroupRepository.closeLog();
	}

	public void setXxlJobGroupRepository(XxlJobGroupRepository xxlJobGroupRepository) {
		this.xxlJobGroupRepository = xxlJobGroupRepository;
	}

	public AdminBiz getAdminBiz() {
        return adminBiz;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

}
