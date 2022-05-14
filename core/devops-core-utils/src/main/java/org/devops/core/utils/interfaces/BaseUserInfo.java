package org.devops.core.utils.interfaces;

/**
 * @author GENSEN
 * @date 2021/11/8 9:51
 * @description：基础用户登记信息
 */
public interface BaseUserInfo {

    /**
     * 用户id
     * @return
     */
    String userId();

    /**
     * 用户名
     * @return
     */
    String userName();

    /**
     * 用户类型
     * @return
     */
    String userType();

    /**
     * 用户类型文本
     * @return
     */
    String userTypeText();

    /**
     * @return
     */
    Long companyId();

}
