package org.devops.mjar.live.transform.resolve.mapper;

/**
 * @author GENSEN
 * @date 2021/6/28 16:59
 * @description：
 */
public enum ParamsType {
    BODY,
    QUERY;

    public static final ParamsType get(String name){
        for (ParamsType value : values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException("illegal content-type " + name);
    }

}
