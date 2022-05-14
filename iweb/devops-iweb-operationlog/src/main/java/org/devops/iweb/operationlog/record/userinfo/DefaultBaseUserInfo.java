package org.devops.iweb.operationlog.record.userinfo;

import org.devops.core.utils.interfaces.BaseUserInfo;

/**
 * @author GENSEN
 * @date 2021/11/19 10:47
 * @description：默认用户信息实现
 */
public class DefaultBaseUserInfo implements BaseUserInfo {
    @Override
    public String userId() {
        return "";
    }

    @Override
    public String userName() {
        return "";
    }

    @Override
    public String userType() {
        return "";
    }

    @Override
    public String userTypeText() {
        return "";
    }

    @Override
    public Long companyId() {
        return 0L;
    }
}
