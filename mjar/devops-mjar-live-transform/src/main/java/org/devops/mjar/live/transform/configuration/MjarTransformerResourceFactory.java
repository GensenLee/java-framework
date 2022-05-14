package org.devops.mjar.live.transform.configuration;

import org.devops.mjar.live.transform.resolve.xml.XMLDocumentElementKey;
import org.devops.mjar.live.transform.resolve.xml.XMLReader;
import org.devops.mjar.live.transform.process.TransformProcessorChainFactory;
import org.devops.mjar.live.transform.transformer.TransformerFactory;
import lombok.extern.slf4j.Slf4j;
import org.jdom2.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author GENSEN
 * @date 2021/6/28 12:04
 * @description：资源工厂
 */
@Slf4j
@Component
public class MjarTransformerResourceFactory implements InitializingBean {

    @Autowired
    private MjarTransformerConfigurationValueFactory configurationValueFactory;

    @Autowired
    private TransformerFactory transformerFactory;

    private Set<Resource> transformerResourceSet = new HashSet<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        Object value = configurationValueFactory.getValue(MjarTransformerConfigurationValueFactory.Key.XML_LOCATION);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resolverResources = resolver.getResources(value.toString());
        transformerResourceSet.addAll(Arrays.asList(resolverResources));
        processingTransformerRegister();
    }

    /**
     * 注册transformer
     */
    public void processingTransformerRegister() throws ClassNotFoundException {
        if (transformerResourceSet.isEmpty()) {
            // 未注册任何转换器
            log.error("transformer profiles not found ");
            return;
        }
        XMLReader xmlReader = new XMLReader();
        for (Resource resource : transformerResourceSet) {
            MjarTransformerResourceContext.set(resource);
            List<Element> elementList = xmlReader.read(resource);
            for (Element element : elementList) {
                if (element.getName().equalsIgnoreCase(XMLDocumentElementKey.Transformers._transformer)) {
                    // 注册转换器
                    transformerFactory.register(element);
                }else if (element.getName().equalsIgnoreCase(XMLDocumentElementKey._preprocess)){
                    // 注册前置增强
                    TransformProcessorChainFactory.registerGlobalPreTransformProcessorChain(element);
                }else if (element.getName().equalsIgnoreCase(XMLDocumentElementKey._postprocess)){
                    // 注册后置增强
                    TransformProcessorChainFactory.registerGlobalPostTransformProcessorChain(element);
                }
            }
            MjarTransformerResourceContext.remove();
        }

    }

}
