package org.devops.mjar.weixin.profileloader;

import org.devops.mjar.weixin.configuration.WxMaProperties;
import org.devops.mjar.weixin.configuration.WxMpProperties;
import org.devops.mjar.weixin.configuration.WxPayProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GENSEN
 * @date 2021/9/23 11:41
 * @description：配置文件读取
 */
public class PropertiesWeixinProfilesLoader implements WeixinProfilesLoader {

    @Autowired
    private WxMaProperties wxMaProperties;

    @Autowired
    private WxMpProperties wxMpProperties;

    @Autowired
    private WxPayProperties wxPayProperties;

    @Override
    public List<WxMaProperties.Config> loadMaConfigs() {
        if (wxMaProperties == null) {
            return new ArrayList<>();
        }
        return wxMaProperties.getConfigs();
    }

    @Override
    public List<WxMpProperties.Config> loadMpConfigs() {
        if (wxMpProperties == null) {
            return new ArrayList<>();
        }
        return wxMpProperties.getConfigs();
    }

    @Override
    public List<WxPayProperties.Config> loadWxPayConfigs() {
        if (wxPayProperties == null) {
            return new ArrayList<>();
        }
        return wxPayProperties.getConfigs();
    }
}
