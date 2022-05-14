package org.devops.mjar.weixin.factory;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.open.api.WxOpenService;
import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.ListUtil;
import org.devops.mjar.weixin.configuration.MjarWeixinEnvironmentConfiguration;
import org.devops.mjar.weixin.configuration.WxMaProperties;
import org.devops.mjar.weixin.configuration.WxMpProperties;
import org.devops.mjar.weixin.configuration.WxPayProperties;
import org.devops.mjar.weixin.profileloader.WeixinProfilesLoader;
import org.devops.mjar.weixin.redis.WxRedisDefaultOps;
import org.devops.mjar.weixin.service.ma.WxMaEnhancerConfigImpl;
import org.devops.mjar.weixin.service.ma.WxMpEnhancerConfigImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author GENSEN
 * @date 2021/9/9 16:23
 * @description：服务工厂
 */
public class WxServiceFactory {

    /**
     * 小程序service
     */
    private static WxMaService MA_SERVICE;

    /**
     * 公众号service
     */
    private static WxMpService MP_SERVICE;

    /**
     * 微信支付service
     */
    private static WxPayService PAY_SERVICE;

    /**
     * 微信第三方平台service
     */
    private static WxOpenService OPEN_SERVICE;

    //<editor-fold desc="服务初始化">

    /**
     * @param wxMaProperties
     */
    private static void init(WxMaProperties wxMaProperties) {
        if (wxMaProperties == null) {
            return;
        }
        MA_SERVICE = convertMaServices(wxMaProperties.getConfigs());
    }

    /**
     * @param wxMpProperties
     */
    private static void init(WxMpProperties wxMpProperties) {
        if (wxMpProperties == null) {
            return;
        }
        MP_SERVICE = convertMpServices(wxMpProperties.getConfigs());
    }

    /**
     * @param wxPayProperties
     */
    private static void init(WxPayProperties wxPayProperties) {
        if (wxPayProperties == null) {
            return;
        }
        PAY_SERVICE = convertPayServices(wxPayProperties.getConfigs());
    }

    /**
     * 初始化注入对象, 用于bean自动注入
     */
    public static void initInjectionBeans(){
        init(new WxMaProperties() {{
            setConfigs(new ArrayList<>());
        }});
        init(new WxMpProperties() {{
            setConfigs(new ArrayList<>());
        }});
        init(new WxPayProperties() {{
            setConfigs(new ArrayList<>());
        }});
    }


    /**
     * 扫描配置, 将配置扫描进service实例
     *
     * @param loader
     */
    private static void loadProfiles(WeixinProfilesLoader loader) {
        if (loader == null) {
            return;
        }
        List<WxMaProperties.Config> maConfigs = loader.loadMaConfigs();
        WxMaProperties wxMaProperties = new WxMaProperties();
        wxMaProperties.setConfigs(maConfigs);

        List<WxMpProperties.Config> mpConfigs = loader.loadMpConfigs();
        WxMpProperties wxMpProperties = new WxMpProperties();
        wxMpProperties.setConfigs(mpConfigs);

        List<WxPayProperties.Config> payConfigs = loader.loadWxPayConfigs();
        WxPayProperties wxPayProperties = new WxPayProperties();
        wxPayProperties.setConfigs(payConfigs);

        init(wxMaProperties);
        init(wxMpProperties);
        init(wxPayProperties);
    }

    /**
     * @param configs
     * @return
     */
    private static WxMaService convertMaServices(List<WxMaProperties.Config> configs) {
        WxMaService service = MA_SERVICE != null ? MA_SERVICE : new WxMaServiceImpl();
        configs = configs == null ? Collections.EMPTY_LIST : configs;

        Map<String, WxMaConfig> maDefaultConfigMap = configs.stream()
                .map(a -> {
                    WxMaDefaultConfigImpl config = new WxMaEnhancerConfigImpl();
                    config.setAppid(a.getAppId());
                    config.setSecret(a.getSecret());
                    config.setToken(a.getToken());
                    config.setAesKey(a.getAesKey());
                    config.setMsgDataFormat(a.getMsgDataFormat());
                    return config;
                }).collect(Collectors.toMap(c -> c.getAppid(), Function.identity()));
        if (!CollectionUtils.isEmpty(maDefaultConfigMap)) {
            service.setMultiConfigs(maDefaultConfigMap);
        }
        return service;
    }

    /**
     * @param configs
     * @return
     */
    private static WxPayService convertPayServices(List<WxPayProperties.Config> configs) {
        WxPayService service = PAY_SERVICE != null ? PAY_SERVICE : new WxPayServiceImpl();
        configs = configs == null ? Collections.EMPTY_LIST : configs;

        Map<String, WxPayConfig> maDefaultConfigMap = configs.stream()
                .map(c -> BeanUtil.copy(c, WxPayConfig.class))
                .collect(Collectors.toMap(c -> c.getAppId(), Function.identity()));
        if (!maDefaultConfigMap.isEmpty()) {
            service.setMultiConfig(maDefaultConfigMap);
        }
        return service;
    }

    private static WxMpService convertMpServices(List<WxMpProperties.Config> configs) {
        WxMpService service = MP_SERVICE != null ? MP_SERVICE : new WxMpServiceImpl();
        configs = configs == null ? Collections.EMPTY_LIST : configs;

        WxRedisDefaultOps defaultOps = new WxRedisDefaultOps();
        Map<String, WxMpConfigStorage> mpDefaultConfigMap = configs.stream()
                .map(a -> {
                    WxMpDefaultConfigImpl config = new WxMpEnhancerConfigImpl(defaultOps);
                    config.setAppId(a.getAppId());
                    config.setSecret(a.getSecret());
                    config.setToken(a.getToken());
                    config.setAesKey(a.getAesKey());
                    return config;
                }).collect(Collectors.toMap(c -> c.getAppId(), Function.identity()));
        if (!CollectionUtils.isEmpty(mpDefaultConfigMap)) {
            service.setMultiConfigStorages(mpDefaultConfigMap);
        }
        return service;
    }
    //</editor-fold>


    //<editor-fold desc="微信service">

    /**
     * @return
     */
    public static WxMaService getMaService() {
        return MA_SERVICE;
    }

    /**
     * @return
     */
    public static WxMpService getMpService() {
        return MP_SERVICE;
    }

    /**
     * @return
     */
    public static WxPayService getPayService() {
        return PAY_SERVICE;
    }
    //</editor-fold>

    //<editor-fold desc="配置加载">
    public static class Loader implements InitializingBean {
        @Override
        public void afterPropertiesSet() throws Exception {
            WeixinProfilesLoader loader = (WeixinProfilesLoader)SpringContextUtil.getBean(MjarWeixinEnvironmentConfiguration.getLoadClass());
            loadProfiles(loader);
        }
    }

    public static class WxBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            // 注册service
            WxServiceFactory.initInjectionBeans();
        }
    }
    //</editor-fold>

}
