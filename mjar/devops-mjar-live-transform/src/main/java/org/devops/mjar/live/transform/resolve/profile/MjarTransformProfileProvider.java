package org.devops.mjar.live.transform.resolve.profile;

import org.devops.mjar.live.core.sign.LiveApiProfiles;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GENSEN
 * @date 2021/6/25 12:05
 * @description：配置转化 others -> polyv
 */
public interface MjarTransformProfileProvider {

    /**
     * 配置转换
     * @param request
     * @return
     */
    LiveApiProfiles apply(HttpServletRequest request);

}
