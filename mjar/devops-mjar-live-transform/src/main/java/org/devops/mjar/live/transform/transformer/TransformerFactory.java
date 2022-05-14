package org.devops.mjar.live.transform.transformer;

import org.devops.mjar.live.transform.resolve.xml.LiveTransformerXmlParsingException;
import org.devops.mjar.live.transform.resolve.xml.XMLDocumentElementKey;
import org.devops.mjar.live.transform.resolve.xml.define.PolyvTransformerDefineType;
import org.devops.mjar.live.transform.resolve.xml.define.parser.PolyvTransformerDefineParser;
import org.devops.mjar.live.transform.process.PostTransformProcessorChain;
import org.devops.mjar.live.transform.process.PreTransformProcessorChain;
import org.devops.mjar.live.transform.process.TransformProcessorChainFactory;
import org.devops.core.utils.constant.CommonConstant;
import org.devops.mjar.live.core.exception.LiveApiRuntimeException;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author GENSEN
 * @date 2021/6/24 20:25
 * @description：转换器工厂
 */
@Component
public class TransformerFactory {

    /**
     * 处理器实例缓存集合
     * key = method + uri
     */
    private final Map<String, TransformerConfig> processors = new ConcurrentHashMap<>();


    /**
     * 注册转换器
     *
     * @param element
     */
    public void register(Element element) throws ClassNotFoundException {
        Element path = element.getChild(XMLDocumentElementKey.Transformers.Transformer._path);
        // 匹配请求路径
        String uri = path.getValue();
        if (processors.containsKey(uri)) {
            throw new LiveApiRuntimeException("duplicate path: " + uri);
        }
        Attribute type = element.getAttribute(XMLDocumentElementKey.Transformers._type);
        if (type == null) {
            throw new LiveTransformerXmlParsingException("<transformer> missing attribute: type");
        }

        PolyvTransformerDefineType defineType = PolyvTransformerDefineType.get(type.getValue());
        PolyvTransformerDefineParser parser = defineType.getParser();
        Transformer transformer = parser.parsing(element);
        Attribute method = path.getAttribute(XMLDocumentElementKey._method);
        if (method == null) {
            throw new LiveTransformerXmlParsingException("<path> missing attribute: method");
        }

        if (transformer == null) {
            throw new IllegalStateException("failure to create transformer");
        }
        // 解析 processor
        Element preprocess = element.getChild(XMLDocumentElementKey._preprocess);
        PreTransformProcessorChain preTransformProcessorChain = TransformProcessorChainFactory.createPreTransformProcessorChain(preprocess);
        Element postprocess = element.getChild(XMLDocumentElementKey._postprocess);
        PostTransformProcessorChain postTransformProcessorChain = TransformProcessorChainFactory.createPostTransformProcessorChain(postprocess);
        processors.put(getKey(uri, method.getValue()), new TransformerConfig(transformer, preTransformProcessorChain, postTransformProcessorChain));
    }


    /**
     * @param uri
     * @param method
     * @return
     */
    public TransformerConfig get(String uri, String method) {
        return doGet(getKey(uri, method));
    }

    private TransformerConfig doGet(String key) {
        return processors.get(key);
    }

    /**
     * 获取 Transformer 唯一键值
     *
     * @param uri
     * @param method
     * @return
     */
    private String getKey(String uri, String method) {
        return method + CommonConstant.POUND_MARK + uri;
    }

}
