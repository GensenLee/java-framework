package org.devops.mjar.live.transform.resolve.mapper;

import lombok.Data;

import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/6/29 12:17
 * @description：转换结果
 */
@Data
public class ConvertResult {

    public ConvertResult(Object body, Map<String, Object> queryMap) {
        this.body = body;
        this.queryMap = queryMap;
    }

    public ConvertResult(Object body) {
        this.body = body;
    }

    public ConvertResult(Map<String, Object> queryMap) {
        this.queryMap = queryMap;
    }

    private Object body;

    private Map<String, Object> queryMap;

}
