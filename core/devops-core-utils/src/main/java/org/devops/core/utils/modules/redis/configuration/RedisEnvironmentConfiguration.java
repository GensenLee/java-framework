package org.devops.core.utils.modules.redis.configuration;

import org.devops.core.utils.util.StringUtil;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author GENSEN
 */
@Configuration
public class RedisEnvironmentConfiguration implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        MergedAnnotation<EnableRedis> redisMergedAnnotation = metadata.getAnnotations().get(EnableRedis.class);
        if (!redisMergedAnnotation.isPresent()) {
            return;
        }
        EnableRedis enableRedis = redisMergedAnnotation.synthesize();
        RedisConfiguration.setPrefix(resolve(enableRedis.prefix()));
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    private String resolve(String value) {
        if (StringUtil.isNotEmpty(value)) {
            return this.environment.resolvePlaceholders(value);
        }
        return value;
    }

}
