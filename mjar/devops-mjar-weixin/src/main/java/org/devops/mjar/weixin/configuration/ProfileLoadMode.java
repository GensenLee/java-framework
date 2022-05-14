package org.devops.mjar.weixin.configuration;

import org.devops.mjar.weixin.model.WeixinConfig;
import org.devops.mjar.weixin.profileloader.DatabaseWeixinProfilesLoader;
import org.devops.mjar.weixin.profileloader.PropertiesWeixinProfilesLoader;

/**
 * @author GENSEN
 * @date 2021/9/23 12:12
 * @description：配置文件加载模式
 */
public enum ProfileLoadMode {
    /**
     * 从配置文件读取
     * wx:
     *   common:
     *     configs:
     *         - appid: xxxxxxxxxx
     *           secret: xxxxxxxxxxxxxxxxxxxxx
     */
    PropertiesLoader(PropertiesWeixinProfilesLoader.class, false, false),

    /**
     * @see WeixinConfig
     */
    DatabaseLoader(DatabaseWeixinProfilesLoader.class, false, true),

    /**
     *
     */
    CustomLoader(null, true, true);

    private Class<?> loader;

    private boolean needCustomLoader;

    private boolean needDatasource;

    public boolean isNeedDatasource() {
        return needDatasource;
    }

    public Class<?> getLoader() {
        return loader;
    }

    public boolean isNeedCustomLoader() {
        return needCustomLoader;
    }

    ProfileLoadMode(Class<?> loader, boolean needCustomLoader, boolean needDatasource) {
        this.loader = loader;
        this.needCustomLoader = needCustomLoader;
        this.needDatasource = needDatasource;
    }

}
