package org.devops.mjar.live.transform.enums;

/**
 * @author GENSEN
 * @date 2021/6/30 14:48
 * @description：请求方法
 */
public enum HttpMethod {
    GET,POST;

    public static HttpMethod get(String code){
        for (HttpMethod value : values()) {
            if (value.name().equalsIgnoreCase(code)) {
                return value;
            }
        }
        return null;
    }
}
