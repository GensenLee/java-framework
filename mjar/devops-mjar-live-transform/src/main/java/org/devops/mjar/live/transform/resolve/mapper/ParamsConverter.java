package org.devops.mjar.live.transform.resolve.mapper;

import org.devops.core.utils.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author GENSEN
 * @date 2021/6/29 10:47
 * @description：转换器
 */
public interface ParamsConverter {

    /**
     * convert request params
     *
     * @param convertFieldMap
     * @param request
     * @return
     */
    ConvertResult convertRequest(Map<String, TransformConvertField> convertFieldMap, HttpServletRequest request);


    /**
     * 转换一层参数
     *
     * @param data            当前转换层数据
     * @param parentGroupCollect 全部TransformConvertField分组集合
     * @return data 和  parentGroupCollect.get(parentGroupCollect) 处于同一层
     */
    default Object convertDepth(Object data, TransformConvertField parent, Map<TransformConvertField, List<TransformConvertField>> parentGroupCollect) {
        if (data == null || StringUtil.isEmpty(data)) {
            return null;
        }
        List<TransformConvertField> convertFieldList = parentGroupCollect.get(parent);
        Map<String, TransformConvertField> fieldMap = convertFieldList.stream()
                .collect(Collectors.toMap(TransformConvertField::getFrom, Function.identity()));

        Object convertData = parent.getFieldType().convertData(data);

        if (convertData instanceof List){
            List<Map<String, Object>> listMap = new ArrayList<>();
            for (Object datum : (List) convertData) {
                Map<String, Object> fromMap = (Map)datum;
                Map<String, Object> tmpMap = new HashMap<>();
                doConvert(parentGroupCollect, tmpMap, fieldMap, fromMap);
                listMap.add(tmpMap);
            }
            return listMap;
        }else {
            Map<String, Object> toMap = new HashMap<>();
            Map<String, Object> fromMap = (Map)convertData;
            doConvert(parentGroupCollect, toMap, fieldMap, fromMap);
            return toMap;
        }
    }

    /**
     * 单对象转化
     * @param parentGroupCollect
     * @param toMap
     * @param fieldMap
     * @param fromMap
     */
    default void doConvert(Map<TransformConvertField, List<TransformConvertField>> parentGroupCollect, Map<String, Object> toMap, Map<String, TransformConvertField> fieldMap, Map<String, Object> fromMap) {
        for (Map.Entry<String, TransformConvertField> entry : fieldMap.entrySet()) {
            String key = entry.getKey();
            Object tmpData = fromMap.get(key);

            TransformConvertField convertField = entry.getValue();
            // 字段转换
            if (!parentGroupCollect.containsKey(convertField)) {
                toMap.put(convertField.getTo(), convertField.convertTo(tmpData, parentGroupCollect));
                continue;
            }

            // 集合转换
            toMap.put(convertField.getTo(), convertDepth(tmpData, convertField, parentGroupCollect));
        }
    }

    /**
     * @param convertFieldMap
     * @param objectMap
     * @return
     */
    default Map<String, Object> getConvertMap(Map<String, TransformConvertField> convertFieldMap, Map<String, Object> objectMap) {
        /* 根据字段父级分组
         * a.id      b.id
         * a.name    b.name
         * */
        Map<TransformConvertField, List<TransformConvertField>> parentGroupCollect =
                convertFieldMap.values().stream()
                        .filter(f -> f.getParent() != null)
                        .collect(Collectors.groupingBy(TransformConvertField::getParent));
        TransformConvertField convertField = convertFieldMap.get(TransformConvertField.ROOT_KEY);
        return (Map<String, Object>)convertDepth(objectMap, convertField, parentGroupCollect);
    }

}
