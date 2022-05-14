package org.devops.mjar.live.transform.resolve.profile;

import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.core.utils.util.ListUtil;
import org.devops.mjar.live.core.exception.LiveApiRuntimeException;
import org.devops.mjar.live.core.operator.LiveApiLogger;
import org.devops.mjar.live.core.sign.LiveApiProfiles;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author GENSEN
 * @date 2021/6/25 12:11
 * @description：配置工厂
 */
@Component
public class MjarTransformProfileFactory implements InitializingBean {

    private List<MjarTransformProfileProvider> profileProviderList;

    @Override
    public void afterPropertiesSet() throws Exception {
        profileProviderList = SpringContextUtil.getBeanList(MjarTransformProfileProvider.class);
        if (ListUtil.isNull(profileProviderList)) {
            throw new LiveApiRuntimeException("can not found implements of MjarTransformProfileProvider");
        }
    }

    /**
     * 获取配置
     * @param request
     * @return
     */
    public LiveApiProfiles getProfile(HttpServletRequest request){
        for (MjarTransformProfileProvider profileProvider : profileProviderList) {
            LiveApiProfiles profiles = profileProvider.apply(request);
            if (profiles != null) {
                return profiles;
            }
        }
        LiveApiLogger.log(this.getClass(), "遍历 PolyvTransformProfileProvider.apply 后未获取到profile");
        throw new LiveApiRuntimeException("can not found profile of this request");
    }

}
