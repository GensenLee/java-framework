package org.devops.iweb.operationlog.configuration;

import org.devops.core.utils.datasource.DataSourceMapper;
import org.devops.core.utils.util.StringUtil;
import org.devops.iweb.operationlog.model.IOperationLogModel;
import org.devops.iweb.operationlog.repository.SubTableControl;
import org.devops.iweb.operationlog.web.config.IWebOperationLogApiConfiguration;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author GENSEN
 */
@Configuration
public class IWebOperationLogEnvironmentConfiguration implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;

    private static EnableIWebOperationLog enableIWebOperationLog;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        MergedAnnotations annotations = metadata.getAnnotations();
        MergedAnnotation<EnableIWebOperationLog> annotation = annotations.get(EnableIWebOperationLog.class);
        if (!annotation.isPresent()) {
            return;
        }
        enableIWebOperationLog = annotation.synthesize();
        SubTableControl.setMode(enableIWebOperationLog.subTableMode());
        String dataSource = resolve(enableIWebOperationLog.datasource());
        DataSourceMapper.setPackage(IOperationLogModel.class.getPackage().getName(), dataSource);
        String defaultUserInfoDefine = resolve(enableIWebOperationLog.defaultUserInfoDefine());
        IWebOperationLogConfiguration.setDefaultUserInfoDefine(defaultUserInfoDefine);

        // 注册web api
        if (!enableIWebOperationLog.enableApi()) {
            return;
        }

        RootBeanDefinition beanDefinition = new RootBeanDefinition(IWebOperationLogApiConfiguration.class);
        beanDefinition.setSynthetic(true);
        registry.registerBeanDefinition("iWebOperationLogApiConfiguration" ,beanDefinition);
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
