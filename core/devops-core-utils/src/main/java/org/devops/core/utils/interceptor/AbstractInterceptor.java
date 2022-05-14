package org.devops.core.utils.interceptor;

import org.devops.core.utils.vo.BaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
public abstract class AbstractInterceptor implements HandlerInterceptor{

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	protected boolean sendResult(BaseBean result,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "application/json; charset=utf-8");
		try {
			response.getWriter().print(result.toJsonString());
			response.flushBuffer();
		} catch (Exception ignored) {
		}
		return false;
	}
}
