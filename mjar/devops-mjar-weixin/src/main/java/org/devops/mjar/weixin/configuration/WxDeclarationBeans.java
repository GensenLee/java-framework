package org.devops.mjar.weixin.configuration;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.github.binarywang.wxpay.service.WxPayService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.devops.mjar.weixin.factory.WxServiceFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @author GENSEN
 * @date 2021/9/22 18:56
 * @description：bean声明
 */
@Configuration
public class WxDeclarationBeans {

    @Bean
    public BeanFactoryPostProcessor wxBeanFactoryPostProcessor(){
        return new WxServiceFactory.WxBeanFactoryPostProcessor();
    }

    @Bean
    public WxServiceFactory.Loader wxServiceLoader(){
        return new WxServiceFactory.Loader();
    }

    @Bean
    @Lazy
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @ConditionalOnMissingBean
    public WxMaService wxMaService(){
        return WxServiceFactory.getMaService();
    }

    @Bean
    @Lazy
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @ConditionalOnMissingBean
    public WxMpService wxMpService(){
        return WxServiceFactory.getMpService();
    }

    @Bean
    @Lazy
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @ConditionalOnMissingBean
    public WxPayService wxPayService(){
        return WxServiceFactory.getPayService();
    }

}
