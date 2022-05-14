package org.devops.mjar.live.core.servlet;

/**
 * @author GENSEN
 * @date 2021/7/9 10:19
 * @description：
 */
public interface CommonRequestWrapper {

    /**
     * 暂存转换参数
     * @param data
     */
    void setConvertedData(Object data);

    /**
     * 获取转换参数
     * @return
     */
    Object getConvertedData();

}
