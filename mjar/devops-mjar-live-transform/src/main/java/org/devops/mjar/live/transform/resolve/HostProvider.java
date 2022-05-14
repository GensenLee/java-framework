package org.devops.mjar.live.transform.resolve;

import org.devops.core.utils.spring.SpringContextUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author GENSEN
 * @date 2021/6/25 11:30
 * @description：域名处理
 */
@Component
public class HostProvider implements InitializingBean {

    /**
     * polyv api host
     */
    private static String apiHost;

    public static String apiHost(){
        return apiHost;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Environment environment = SpringContextUtil.getContext().getEnvironment();
        apiHost = environment.getProperty("live.host.api");
    }
}
