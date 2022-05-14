package org.devops.iweb.auth.interceptor;

import org.devops.core.utils.interceptor.AbstractInterceptor;
import org.devops.iweb.auth.context.AuthContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Component
public class GlobalAuthInterceptor extends AbstractInterceptor {

    private String[] excludeUrls;

    public String[] getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(String[] excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    /**
     * 不需要权限控制URL
     *
     * @param uri
     * @return
     */
    private boolean exclude(String uri) {
        log.debug("************GlobalAuthInterceptor exclude excludeUrls [{}]", excludeUrls);
        if (excludeUrls != null) {
            for (String exc : excludeUrls) {
                log.debug("************GlobalAuthInterceptor exclude exc [{}]", exc);
                if (exc.equals(uri)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContext.clear();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("[{} preHandle] 进入拦截器", this.getClass().getSimpleName());
        AuthContext.clear();
        return true;
    }
}
