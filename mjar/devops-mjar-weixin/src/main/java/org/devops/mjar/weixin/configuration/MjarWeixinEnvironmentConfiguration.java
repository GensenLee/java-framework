package org.devops.mjar.weixin.configuration;

import org.devops.core.utils.datasource.DataSourceMapper;
import org.devops.core.utils.util.StringUtil;
import org.devops.mjar.weixin.exception.WeixinConfigException;
import org.devops.mjar.weixin.exception.WeixinProfileErrorException;
import org.devops.mjar.weixin.profileloader.WeixinProfilesLoader;
import org.devops.mjar.weixin.model.WeixinConfig;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class MjarWeixinEnvironmentConfiguration implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static ProfileLoadMode mode;

    private static Class<?> loadClass;

    private static Environment environment;

    public static Environment getEnvironment() {
        return environment;
    }

    public static ProfileLoadMode getMode() {
        return mode;
    }

    public static Class<?> getLoadClass() {
        return loadClass;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        Map<String, Object> defaultAttrs = metadata.getAnnotationAttributes(EnableWeixin.class.getName());

        if (defaultAttrs == null) {
            return;
        }
        mode = (ProfileLoadMode) defaultAttrs.get("initialMode");
        if (mode.isNeedDatasource()) {
            String dataSource = resolve(defaultAttrs.get("datasource").toString());
            if (StringUtil.isEmpty(dataSource)) {
                throw new WeixinConfigException("未指定数据源 datasource");
            }
            DataSourceMapper.setPackage(WeixinConfig.class.getPackage().getName(), dataSource);
        }
        if (mode.isNeedCustomLoader()) {
            Class<?> loader = (Class<?>) defaultAttrs.get("loader");
            if (!WeixinProfilesLoader.class.isAssignableFrom(loader)) {
                throw new WeixinProfileErrorException("loader 需要实现 WeixinProfilesLoader 接口");
            }
            loadClass = loader;
        } else {
            loadClass = mode.getLoader();
        }
        registerLoader(registry);
    }

    /**
     * 注册loader
     *
     * @param registry
     */
    private void registerLoader(BeanDefinitionRegistry registry) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClassName(loadClass.getName());
        beanDefinition.setBeanClass(loadClass);
        registry.registerBeanDefinition(loadClass.getSimpleName(), beanDefinition);
    }

    private String resolve(String value) {
        if (StringUtil.isNotEmpty(value)) {
            return MjarWeixinEnvironmentConfiguration.getEnvironment().resolvePlaceholders(value);
        }
        return value;
    }

    @Override
    public void setEnvironment(Environment environment) {
        MjarWeixinEnvironmentConfiguration.environment = environment;
    }
}
