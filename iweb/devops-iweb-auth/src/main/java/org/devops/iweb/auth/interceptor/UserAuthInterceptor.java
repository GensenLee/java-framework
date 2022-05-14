package org.devops.iweb.auth.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.constant.ApiResultCode;
import org.devops.core.utils.interceptor.AbstractInterceptor;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.auth.context.AuthContext;
import org.devops.iweb.auth.redis.IWebAuthRedisDao;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class UserAuthInterceptor extends AbstractInterceptor {

	@Autowired
	private IWebAuthRedisDao iWebAuthRedisDao;
	
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
		log.debug("************UserAuthInterceptor exclude excludeUrls:" + excludeUrls);
		if (excludeUrls != null) {
			for (String exc : excludeUrls) {
				log.debug("************UserAuthInterceptor exclude exc:" + exc);
				if (exc.equals(uri)) {
					return true;
				}
			}
		}
		return false;
	}


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		log.debug("[{} preHandle] 进入拦截器",this.getClass().getSimpleName());
		
		AuthContext.clear();
		
		String uri = request.getRequestURI();

		if (exclude(uri)) {
			return true;
		}
		
		String sessionKey = request.getHeader("session-key");
		if(StringUtil.isEmpty(sessionKey)){
			sessionKey = request.getParameter("sessionKey");
		}
		if(StringUtil.isEmpty(sessionKey)){
			sessionKey = request.getParameter("session-key");
		}
		
		if(StringUtil.isEmpty(sessionKey)){
			log.debug("[{} preHandle] sessionKey",this.getClass().getSimpleName());
			//无效用户
			return sendResult(ApiResult.getFailed(ApiResultCode.SESSION_KEY_ERROR),response);
		}
		
		UserInfoInnerVO userInfoInnerVO = iWebAuthRedisDao.get(sessionKey);
		
		if(userInfoInnerVO == null){
			log.debug("[{} preHandle] sessionKey:{} 过期",this.getClass().getSimpleName(),sessionKey);
			return sendResult(ApiResult.getFailed(ApiResultCode.SESSION_KEY_ERROR),response);
		}
		
		AuthContext.setSessionKey(sessionKey);
		AuthContext.setUserInfoInnerVO(userInfoInnerVO);
		
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		AuthContext.clear();
	}

}
