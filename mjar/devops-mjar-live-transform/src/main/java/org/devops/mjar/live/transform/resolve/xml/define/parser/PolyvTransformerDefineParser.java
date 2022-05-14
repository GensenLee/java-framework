package org.devops.mjar.live.transform.resolve.xml.define.parser;

import org.devops.mjar.live.transform.transformer.Transformer;
import org.jdom2.Element;

/**
 * @author GENSEN
 * @date 2021/6/25 17:52
 * @description：转化器实例生成器
 */
public interface PolyvTransformerDefineParser {

    /**
     * @param element
     * @return
     */
    Transformer parsing(Element element);


}
