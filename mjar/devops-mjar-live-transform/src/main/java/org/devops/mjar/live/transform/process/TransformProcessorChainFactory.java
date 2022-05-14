package org.devops.mjar.live.transform.process;

import org.devops.mjar.live.transform.resolve.xml.XMLDocumentElementKey;
import org.devops.core.utils.util.ListUtil;
import org.devops.core.utils.util.StringUtil;
import org.jdom2.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

import java.util.List;

/**
 * @author GENSEN
 * @date 2021/7/2 16:19
 * @description：调度链工厂
 */
public class TransformProcessorChainFactory {

    /**
     * 全局前置增强
     */
    private static PreTransformProcessorChain globalPreTransformProcessorChain = null;

    /**
     * 全局后置增强
     */
    private static PostTransformProcessorChain globalPostTransformProcessorChain = null;


    public static final Class<?> INTERFACE_CLASS = TransformProcessor.class;

    /**
     * 注册全局前置增强
     * @param postprocess
     * @throws ClassNotFoundException
     */
    public static void registerGlobalPreTransformProcessorChain(Element postprocess) throws ClassNotFoundException {
        PreTransformProcessorChain preTransformProcessorChain = createPreTransformProcessorChain(postprocess);
        globalPreTransformProcessorChain = preTransformProcessorChain;
    }

    /**
     * 注册全局前置增强
     * @param postprocess
     * @throws ClassNotFoundException
     */
    public static void registerGlobalPostTransformProcessorChain(Element postprocess) throws ClassNotFoundException {
        PostTransformProcessorChain postTransformProcessorChain = createPostTransformProcessorChain(postprocess);
        globalPostTransformProcessorChain = postTransformProcessorChain;
    }

    public static PreTransformProcessorChain getGlobalPreTransformProcessorChain() {
        return globalPreTransformProcessorChain;
    }

    public static PostTransformProcessorChain getGlobalPostTransformProcessorChain() {
        return globalPostTransformProcessorChain;
    }

    /**
     * 注册前置增强
     * @param preprocess
     * @return
     * @throws ClassNotFoundException
     */
    public static PreTransformProcessorChain createPreTransformProcessorChain(Element preprocess) throws ClassNotFoundException {
        if (preprocess == null) {
            return null;
        }
        List<Element> elementList = preprocess.getChildren(XMLDocumentElementKey.Process._processor);
        if (ListUtil.isNull(elementList)) {
            return null;
        }
        PreTransformProcessorChain processorChain = new PreTransformProcessorChain();
        for (Element element : elementList) {
            if (!element.getName().equalsIgnoreCase(XMLDocumentElementKey.Process._processor)) {
                continue;
            }
            Class<?> clazz = loadClass(element.getValue());
            Object o = BeanUtils.instantiateClass(clazz);
            ProcessorConfig processorConfig = new ProcessorConfig((TransformProcessor) o);
            processorChain.addProcessor(processorConfig);
        }
        return processorChain;
    }

    /**
     * 注册后置增强
     * @param postprocess
     * @return
     */
    public static PostTransformProcessorChain createPostTransformProcessorChain(Element postprocess) throws ClassNotFoundException {
        if (postprocess == null) {
            return null;
        }
        List<Element> elementList = postprocess.getChildren(XMLDocumentElementKey.Process._processor);
        if (ListUtil.isNull(elementList)) {
            return null;
        }
        PostTransformProcessorChain processorChain = new PostTransformProcessorChain();
        for (Element element : elementList) {
            if (!element.getName().equalsIgnoreCase(XMLDocumentElementKey.Process._processor)) {
                continue;
            }
            Class<?> clazz = loadClass(element.getValue());
            Object o = BeanUtils.instantiateClass(clazz);
            ProcessorConfig processorConfig = new ProcessorConfig((TransformProcessor) o);
            processorChain.addProcessor(processorConfig);
        }
        return processorChain;
    }


    /**
     * @param value
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> loadClass(String value) throws ClassNotFoundException {
        if (StringUtil.isEmpty(value)) {
            throw new ClassNotFoundException(value);
        }
        Class<?> forName = ClassUtils.forName(value, Thread.currentThread().getContextClassLoader());
        if (!INTERFACE_CLASS.isAssignableFrom(forName)) {
            throw new IllegalArgumentException(value + " should implement com.sinmn.polyv.transform.process.TransformProcessor");
        }
        return forName;
    }

}
