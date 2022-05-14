package org.devops.mjar.live.core.sign;

import org.devops.core.utils.exception.CommonException;

/**
 * @author GENSEN
 * @date 2020/10/12 14:29
 * @description：可签名接口
 */
public interface SignParameter {
    /**
     * 签名
     * @param profile
     * @throws CommonException
     */
    void sign(LiveApiProfiles profile) throws CommonException;
}
