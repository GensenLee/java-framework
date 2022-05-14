package org.devops.mjar.live.transform.resolve.xml;

import org.devops.mjar.live.transform.resolve.xml.define.PolyvTransformerDefineType;
import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.util.AssertUtil;
import org.devops.core.utils.util.ListUtil;
import org.devops.mjar.live.core.exception.LiveApiRuntimeException;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GENSEN
 * @date 2021/6/25 16:50
 * @description：xml解析
 */
@Slf4j
public class XMLReader {


    /**
     * @param resource
     * @return Element列表 每一个Element代表一个transformer转换配置
     */
    public List<Element> read(Resource resource) {
        SAXBuilder builder = new SAXBuilder();
        Document doc = null;
        try {
            doc = builder.build(resource.getURL());
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
        checkout(doc, resource);
        Element rootElement = doc.getRootElement();
        List<Element> transformerList = rootElement.getChildren();
        List<Element> result = new ArrayList<>();
        for (Element transformer : transformerList) {
            Element element = getElement(transformer);
            if (element == null) {
                continue;
            }
            result.add(transformer);
        }
        return result;
    }

    /**
     * 检查节点是否合法
     *
     * @param element
     * @return
     */
    private Element getElement(Element element) {
        if (ListUtil.isNull(element.getChildren())) {
            return null;
        }
        if (XMLDocumentElementKey._preprocess.equals(element.getName()) || XMLDocumentElementKey._postprocess.equals(element.getName())) {
            return element;
        }

        if (!XMLDocumentElementKey.Transformers._transformer.equals(element.getName())) {
            return null;
        }
        Attribute attribute = element.getAttribute(XMLDocumentElementKey.Transformers._type);
        if (attribute == null) {
            element.setAttribute(XMLDocumentElementKey.Transformers._type, PolyvTransformerDefineType.Parsing.name());
        }
        Element path = element.getChild(XMLDocumentElementKey.Transformers.Transformer._path);
        if (path == null) {
            throw new LiveTransformerXmlParsingException("illegal xml node. missing <path>");
        }
        Attribute type = element.getAttribute(XMLDocumentElementKey.Transformers._type);
        String value = type.getValue();
        PolyvTransformerDefineType polyvTransformerDefineType = PolyvTransformerDefineType.get(value);
        Element child;
        switch (polyvTransformerDefineType) {
            case JClass:
                child = element.getChild(XMLDocumentElementKey.Transformers.Transformer._class);
                break;
            case Parsing:
                child = element.getChild(XMLDocumentElementKey.Transformers.Transformer._manual);
                break;
            default:
                throw new LiveTransformerXmlParsingException("unsupported transformer type: " + value);
        }
        if (child == null) {
            log.error("JClass matching <class>, Parsing matching <manual>");
            throw new LiveTransformerXmlParsingException("missing necessary element");
        }
        return element;
    }


    /**
     * @param doc
     * @param resource
     */
    private void checkout(Document doc, Resource resource) {
        AssertUtil.notNull(doc, "read xml failure");
        if (!doc.hasRootElement()) {
            throw new LiveApiRuntimeException("xml file: " + resource.getFilename() + " do not has a root element");
        }
        Element rootElement = doc.getRootElement();
        if (!rootElement.getName().equals(XMLDocumentElementKey._transformers)) {
            throw new LiveApiRuntimeException("xml must has the root element <transformers>");
        }
    }

}
