package org.devops.mjar.weixin.profileloader;

import org.devops.mjar.weixin.configuration.WxMaProperties;
import org.devops.mjar.weixin.configuration.WxMpProperties;
import org.devops.mjar.weixin.configuration.WxPayProperties;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.List;

/**
 * @author GENSEN
 * @date 2021/9/9 16:17
 * @description：微信扫描器
 */
public interface WeixinProfilesLoader {

    /**
     * 扫描小程序配置
     * @return
     */
    default List<WxMaProperties.Config> loadMaConfigs(){
        return null;
    }

    /**
     * 扫描公众号配置
     * @return
     */
    default List<WxMpProperties.Config> loadMpConfigs(){
        return null;
    }

    /**
     * 加载微信支付配置
     * @return
     */
    default List<WxPayProperties.Config> loadWxPayConfigs(){
        return null;
    }

    /**
     * 绑定工厂
     * @param beanFactory
     */
    default void configBeanFactory(ConfigurableListableBeanFactory beanFactory){

    }

}
