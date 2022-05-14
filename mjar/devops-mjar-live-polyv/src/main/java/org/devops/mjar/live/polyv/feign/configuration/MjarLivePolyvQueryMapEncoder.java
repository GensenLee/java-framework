package org.devops.mjar.live.polyv.feign.configuration;

import feign.QueryMapEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/3/4 14:24
 * @description：自定义Get请求参数解析
 */
@Slf4j
public class MjarLivePolyvQueryMapEncoder implements QueryMapEncoder {

    @Override
    public Map<String, Object> encode(Object object) {
        return ParameterConverterHelper.converter(object);
    }

}
