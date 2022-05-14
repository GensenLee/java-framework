package org.devops.mjar.live.transform.resolve.mapper.converter;

import org.devops.mjar.live.transform.resolve.mapper.ConvertResult;
import org.devops.mjar.live.transform.resolve.mapper.ParamsConverter;
import org.devops.mjar.live.transform.resolve.mapper.TransformConvertField;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/6/29 10:50
 * @description：参数表-参数表
 */
public class QueryQueryConverter implements ParamsConverter {

    @Override
    public ConvertResult convertRequest(Map<String, TransformConvertField> convertFieldMap, HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> objectMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            objectMap.put(entry.getKey(), entry.getValue()[0]);
        }

        Map<String, Object> convertMap = getConvertMap(convertFieldMap, objectMap);
        return new ConvertResult(convertMap);
    }
}
