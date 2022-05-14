package org.devops.iweb.file.configuration;

import org.devops.core.utils.datasource.DataSourceMapper;
import org.devops.core.utils.util.StringUtil;
import org.devops.iweb.file.constant.IwebFileConstant;
import org.devops.iweb.file.datasource.FileDynamicDataSource;
import org.devops.iweb.file.model.IFileModel;
import org.devops.iweb.file.web.config.IWebFileApiConfiguration;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author GENSEN
 */
@Configuration
public class IWebFileEnvironmentConfiguration implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        MergedAnnotation<EnableIWebFile> fileMergedAnnotation = metadata.getAnnotations().get(EnableIWebFile.class);
        if (!fileMergedAnnotation.isPresent()) {
            return;
        }

        EnableIWebFile enableIWebFile = fileMergedAnnotation.synthesize();
        String dataSource = resolve(enableIWebFile.datasource());
        DataSourceMapper.setPackage(IFileModel.class.getPackage().getName(), dataSource);
        FileDynamicDataSource.setDataSource(dataSource);
        String path = resolve(enableIWebFile.path());
        IwebFileConstant.setPath(path);
        String domain = resolve(enableIWebFile.domain());
        IwebFileConstant.setDomain(domain);

        if (!enableIWebFile.enableApi()) {
            return;
        }

        RootBeanDefinition beanDefinition = new RootBeanDefinition(IWebFileApiConfiguration.class);
        beanDefinition.setSynthetic(true);
        registry.registerBeanDefinition("iWebFileApiConfiguration" ,beanDefinition);

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
