package org.devops.mjar.live.transform.resolve.mapper;


import org.devops.core.utils.constant.CommonConstant;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.ListUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/6/30 18:20
 * @description：字段类型
 */
public enum FieldType {

    list {
        @Override
        public Object convert(Object data, TransformConvertField convertField, Map<TransformConvertField, List<TransformConvertField>> parentGroupCollect) {
            List<Map> mapList = FastJsonUtils.getBeanList(FastJsonUtils.toJsonString(data), Map.class);
            if (ListUtil.isNull(mapList) || mapList.isEmpty()) {
                return data;
            }
            List<TransformConvertField> convertFieldList = parentGroupCollect.get(convertField);
            List<Map> result = new ArrayList<>();
            for (Map map : mapList) {
                Map<String, Object> tmp = new HashMap<>();
                for (TransformConvertField field : convertFieldList) {
                    Object from = map.get(field.getFrom());
                    Object to = convertField(from, field);
                    tmp.put(field.getTo(), to);
                }
                result.add(tmp);
            }
            return result;
        }

        @Override
        public Object convertData(Object data) {
            return FastJsonUtils.getBeanMapList(FastJsonUtils.toJsonString(data));
        }
    }
    ,single {
        @Override
        public Object convert(Object data, TransformConvertField convertField, Map<TransformConvertField, List<TransformConvertField>> parentGroupCollect) {
            return convertField(data, convertField);
        }

        @Override
        public Object convertData(Object data) {
            return FastJsonUtils.getMap(data);
        }
    };

    /**
     * @param value
     * @return
     */
    public Object convertField(Object value, TransformConvertField convertField){
        Map<String, String> valueMappings = convertField.getValueMappings();
        if (value != null && !valueMappings.isEmpty()) {
            if (valueMappings.containsKey(value.toString())) {
                return valueMappings.get(value.toString());
            }
            if (valueMappings.containsKey(CommonConstant.ASTERISK_MARK)) {
                return valueMappings.get(CommonConstant.ASTERISK_MARK);
            }
        }
        return convertField.getFiledMapper().mapping(value);
    }

    public abstract Object convert(Object data, TransformConvertField convertField, Map<TransformConvertField, List<TransformConvertField>> parentGroupCollect);

    public abstract Object convertData(Object data);

    public static FieldType get(String name){
        for (FieldType value : values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return null;
    }

}
