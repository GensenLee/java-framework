package org.devops.mjar.live.transform.resolve.xml.define.parser;

import org.devops.mjar.live.transform.resolve.xml.LiveTransformerXmlParsingException;
import org.devops.mjar.live.transform.resolve.xml.XMLDocumentElementKey;
import org.devops.mjar.live.transform.transformer.Transformer;
import org.devops.core.utils.util.StringUtil;
import org.jdom2.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

/**
 * @author GENSEN
 * @date 2021/6/25 17:58
 * @description：JClass 类型转化
 */
public class PolyvTransformerJClassParser implements PolyvTransformerDefineParser {

    @Override
    public Transformer parsing(Element element) {
        Element child = element.getChild(XMLDocumentElementKey.Transformers.Transformer._class);
        if (child == null) {
            // JClass 类型transformer缺失class节点
            throw new LiveTransformerXmlParsingException("JClass missing <class>");
        }
        String className = child.getValue();
        return createInstance(className);
    }


    /**
     * @param className
     * @return
     */
    private Transformer createInstance(String className){
        if (StringUtil.isEmpty(className)) {
            throw new LiveTransformerXmlParsingException("JClass missing <class> value");
        }
        Class<?> clazz;
        try {
            clazz = ClassUtils.forName(className, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        if (!Transformer.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(clazz + " should implement com.sinmn.polyv.transform.transformer.Transformer");
        }
        Object o = BeanUtils.instantiateClass(clazz);
        return (Transformer)o;
    }

}
