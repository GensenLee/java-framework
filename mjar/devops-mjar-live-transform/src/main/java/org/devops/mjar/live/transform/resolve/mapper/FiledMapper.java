package org.devops.mjar.live.transform.resolve.mapper;

/**
 * @author GENSEN
 * @date 2021/6/26 16:20
 * @description：字段转换器
 */
public interface FiledMapper {

    /**
     * 参数转化
     * @param data
     * @return
     */
    Object mapping(Object data);

}
