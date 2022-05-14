package org.devops.mjar.live.transform.resolve.mapper.converter;

import org.devops.mjar.live.transform.resolve.mapper.ConvertResult;
import org.devops.mjar.live.transform.resolve.mapper.ParamsConverter;
import org.devops.mjar.live.transform.resolve.mapper.TransformConvertField;
import org.devops.core.utils.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/6/29 10:50
 * @description：请求体-请求体
 */
public class BodyBodyConverter implements ParamsConverter {

    @Override
    public ConvertResult convertRequest(Map<String, TransformConvertField> convertFieldMap, HttpServletRequest request) {
        Map<String, Object> objectMap = RequestUtil.getRequestBodyAsMap(request);

        Map<String, Object> convertMap = getConvertMap(convertFieldMap, objectMap);
        return new ConvertResult((Object) convertMap);
    }
}
