package org.devops.mjar.live.transform.configuration;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/6/28 11:34
 * @description：组件配置值管理
 */
@Component
public class MjarTransformerConfigurationValueFactory implements ImportBeanDefinitionRegistrar {

    private final static Map<String, Object> profiles = new HashMap<>();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableMjarTransform.class.getName());
        if (annotationAttributes == null) {
            return;
        }
        for (Key key : Key.values()) {
            Object o = annotationAttributes.get(key.fieldName);
            if (o != null) {
                profiles.put(key.name(), o);
            }
        }
    }

    public Object getValue(Key key){
        return profiles.get(key.name());
    }


    public enum Key{
        /**
         * xml配置路径
         */
        XML_LOCATION("locationPattern");
        private String fieldName;

        Key(String fieldName) {
            this.fieldName = fieldName;
        }
    }

}
