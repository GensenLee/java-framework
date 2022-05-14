package org.devops.web.test.bean.configuration;

import org.devops.core.model.configuration.EnableModel;
import org.devops.core.utils.modules.apiEnhancer.configuration.EnableApiEnhancer;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableModel
@EnableApiEnhancer
public class ApplicationConfiguration {


//    @Bean
//    public WxMaService wxMaService(){
//        WxServiceFactory.loadProfiles(() -> {
//            List<WxProperties.Config> list = new ArrayList<>();
//            WxProperties.Config config = new WxProperties.Config();
//            config.setAppid("wx1eb205a93436afa1");
//            config.setSecret("7342feb06b384cf95eb4662f2e7602d1");
//            list.add(config);
//            return list;
//        });
//        return WxServiceFactory.getMaService();
//    }

}
