package org.devops.mjar.weixin.profileloader;

import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.ListUtil;
import org.devops.mjar.weixin.configuration.WxMaProperties;
import org.devops.mjar.weixin.configuration.WxMpProperties;
import org.devops.mjar.weixin.configuration.WxPayProperties;
import org.devops.mjar.weixin.model.WeixinConfig;
import org.devops.mjar.weixin.repository.WeixinConfigRepository;

import java.util.Collections;
import java.util.List;

/**
 * @author GENSEN
 * @date 2021/9/23 11:50
 * @description：数据库读取
 */
public class DatabaseWeixinProfilesLoader implements WeixinProfilesLoader {


    @Override
    public List<WxMaProperties.Config> loadMaConfigs() {
        WeixinConfigRepository weixinConfigRepository = SpringContextUtil.getBean(WeixinConfigRepository.class);
        List<WeixinConfig> list = weixinConfigRepository
                .where(WeixinConfig.TYPE, WeixinConfig.Dict.Type.MA)
                .list();
        if (ListUtil.isNull(list)) {
            return Collections.EMPTY_LIST;
        }
        return BeanUtil.copy(list, WxMaProperties.Config.class);
    }

    @Override
    public List<WxPayProperties.Config> loadWxPayConfigs() {
        WeixinConfigRepository weixinConfigRepository = SpringContextUtil.getBean(WeixinConfigRepository.class);
        List<WeixinConfig> list = weixinConfigRepository
                .where(WeixinConfig.TYPE, WeixinConfig.Dict.Type.PAY)
                .list();
        if (ListUtil.isNull(list)) {
            return Collections.EMPTY_LIST;
        }
        return BeanUtil.copy(list, WxPayProperties.Config.class);
    }

    @Override
    public List<WxMpProperties.Config> loadMpConfigs() {
        WeixinConfigRepository weixinConfigRepository = SpringContextUtil.getBean(WeixinConfigRepository.class);
        List<WeixinConfig> list = weixinConfigRepository
                .where(WeixinConfig.TYPE, WeixinConfig.Dict.Type.MP)
                .list();
        if (ListUtil.isNull(list)) {
            return Collections.EMPTY_LIST;
        }
        return BeanUtil.copy(list, WxMpProperties.Config.class);
    }
}
