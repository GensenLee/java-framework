package org.devops.mjar.live.transform.resolve.xml.define.parser;

import org.devops.mjar.live.transform.resolve.mapper.FieldType;
import org.devops.mjar.live.transform.resolve.mapper.FiledMapper;
import org.devops.mjar.live.transform.resolve.mapper.ParamsConvertType;
import org.devops.mjar.live.transform.resolve.mapper.TransformConvertField;
import org.devops.mjar.live.transform.resolve.xml.LiveTransformerXmlParsingException;
import org.devops.mjar.live.transform.resolve.xml.XMLDocumentElementKey;
import org.devops.mjar.live.transform.enums.HttpMethod;
import org.devops.mjar.live.transform.transformer.TransformerManualTransformer;
import org.devops.mjar.live.transform.transformer.Transformer;
import org.devops.core.utils.util.BooleanUtil;
import org.devops.core.utils.util.ListUtil;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/6/25 17:58
 * @description：Parsing 类型转化
 */
public class PolyvTransformerParsingParser implements PolyvTransformerDefineParser {

    @Override
    public Transformer parsing(Element element) {
        Element manual = element.getChild(XMLDocumentElementKey.Transformers.Transformer._manual);
        Element target = manual.getChild(XMLDocumentElementKey.Transformers.Transformer.Manual._target);
        if (target == null){
            throw new LiveTransformerXmlParsingException("missing <target>");
        }
        Element request = manual.getChild(XMLDocumentElementKey.Transformers.Transformer.Manual._request);
        Element response = manual.getChild(XMLDocumentElementKey.Transformers.Transformer.Manual._response);
        if (request == null || response == null) {
            throw new LiveTransformerXmlParsingException("<manual> must contains <request> and <response>");
        }
        Attribute fromContentType = request.getAttribute(XMLDocumentElementKey.Transformers.Transformer.Manual._from_content_type);
        Attribute toContentType = request.getAttribute(XMLDocumentElementKey.Transformers.Transformer.Manual._to_content_type);
        if (fromContentType == null || toContentType == null) {
            throw new LiveTransformerXmlParsingException("<request> must contains attributes: from-content-type, to-content-type");
        }
        ParamsConvertType paramsConvertType = ParamsConvertType.find(fromContentType.getValue(), toContentType.getValue());

        // 解析 to polyv
        List<TransformConvertField> requestFieldConvertList = new ArrayList<>();
        List<Element> requestFields = request.getChildren(XMLDocumentElementKey.Transformers.Transformer.Manual._field);
        if (ListUtil.isNotNull(requestFields)) {
            for (Element field : requestFields) {
                getFieldConvert(field, null, requestFieldConvertList);
            }
        }

        // 解析 from polyv
        List<TransformConvertField> responseFieldConvertList = new ArrayList<>();
        List<Element> responseFields = response.getChildren(XMLDocumentElementKey.Transformers.Transformer.Manual._field);
        if (ListUtil.isNotNull(responseFields)) {
            for (Element field : responseFields) {
                getFieldConvert(field, null, responseFieldConvertList);
            }
        }
        TransformerManualTransformer transformer = new TransformerManualTransformer(requestFieldConvertList, responseFieldConvertList);
        transformer.setTargetUri(target.getValue());
        transformer.setParamsConvertType(paramsConvertType);
        Attribute method = target.getAttribute(XMLDocumentElementKey._method);
        String value = method.getValue();
        HttpMethod httpMethod = HttpMethod.valueOf(value.toUpperCase());
        transformer.setHttpMethod(httpMethod);

        // TODO: 2021/6/29
        
        return transformer;
    }


    /**
     * @param field
     * @return
     */
    private void getFieldConvert(Element field, TransformConvertField parent, List<TransformConvertField> convertFieldList){
        Attribute from = field.getAttribute(XMLDocumentElementKey.Transformers.Transformer.Manual.Field._from);
        Attribute to = field.getAttribute(XMLDocumentElementKey.Transformers.Transformer.Manual.Field._to);
        Attribute required = field.getAttribute(XMLDocumentElementKey.Transformers.Transformer.Manual.Field._required);

        if (from == null || to == null) {
            throw new LiveTransformerXmlParsingException("<filed> must contains attribute: from, to");
        }
        TransformConvertField fieldConvert = new TransformConvertField(from.getValue(), to.getValue());
        fieldConvert.setParent(parent);
        if (required != null && BooleanUtil.isTrue(required.getValue())) {
            fieldConvert.setRequired(Boolean.TRUE);
        }

        convertFieldList.add(fieldConvert);
        Element mapper = field.getChild(XMLDocumentElementKey.Transformers.Transformer.Manual.Field._mapper);
        if (mapper != null) {
            Attribute jmapper = mapper.getAttribute(XMLDocumentElementKey.Transformers.Transformer.Manual.Field.Mapper._jmapper);
            FiledMapper fieldMapper = getFieldMapper(jmapper);
            if (fieldMapper != null) {
                fieldConvert.setFiledMapper(fieldMapper);
            }

            Map<String, String> valueMappings = new HashMap<>();
            List<Element> mappingElementList = mapper.getChildren(XMLDocumentElementKey.Transformers.Transformer.Manual.Field.Mapper._mapping);
            for (Element element : mappingElementList) {
                Attribute attribute = element.getAttribute(XMLDocumentElementKey.Transformers.Transformer.Manual.Field.Mapper._if);
                if (attribute == null) {
                    throw new LiveTransformerXmlParsingException("<mapping> must contains attribute: if");
                }
                valueMappings.put(attribute.getValue(), element.getValue());
            }
            fieldConvert.getValueMappings().putAll(valueMappings);
            return;
        }

        Attribute fieldType = field.getAttribute(XMLDocumentElementKey.Transformers.Transformer.Manual.Field._type);
        if (fieldType != null) {
            FieldType fieldTypeValue = FieldType.get(fieldType.getValue());
            if (fieldTypeValue != null) {
                fieldConvert.setFieldType(fieldTypeValue);
            }
        }

        // 嵌套子节点
        List<Element> childrenList = field.getChildren(XMLDocumentElementKey.Transformers.Transformer.Manual._field);
        List<TransformConvertField> childrenFieldConvertList = new ArrayList<>();
        for (Element element : childrenList) {
            getFieldConvert(element, fieldConvert, convertFieldList);
        }
        fieldConvert.addChildren(childrenFieldConvertList);
        convertFieldList.addAll(childrenFieldConvertList);
    }

    /**
     * @param jmapper
     * @return
     */
    private FiledMapper getFieldMapper(Attribute jmapper){
        if (jmapper == null) {
            return null;
        }
        String value = jmapper.getValue();
        try {
            Class<?> mapperClass = ClassUtils.forName(value, Thread.currentThread().getContextClassLoader());
            if (!FiledMapper.class.isAssignableFrom(mapperClass)) {
                throw new IllegalArgumentException(mapperClass + " should implement com.sinmn.polyv.transform.core.mapper.FiledMapper");
            }
            Object o = BeanUtils.instantiateClass(mapperClass);
            return (FiledMapper)o;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
