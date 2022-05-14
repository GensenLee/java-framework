package org.devops.iweb.xxl.handle;

import com.xxl.job.core.biz.model.ReturnT;
import lombok.extern.slf4j.Slf4j;

import org.devops.iweb.xxl.exception.XxlJobException;
import org.devops.iweb.xxl.util.JacksonUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * xxl-job-admin异常统一处理
 */
@ControllerAdvice
@Slf4j
public class XxlJobAdminControllerExceptionHandler {
	
	@ExceptionHandler(value = XxlJobException.class)
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// if json
		boolean isJson = false;
		
		if(handler instanceof HandlerMethod){
			HandlerMethod method = (HandlerMethod)handler;
			ResponseBody responseBody = method.getMethodAnnotation(ResponseBody.class);
			if (responseBody != null) {
				isJson = true;
			}
		}

		// error result
		ReturnT<String> errorResult = new ReturnT<String>(ReturnT.FAIL_CODE, ex.toString().replaceAll("\n", "<br/>"));

		// response
		ModelAndView mv = new ModelAndView();
		if (isJson) {
			try {
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(JacksonUtil.writeValueAsString(errorResult));
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
			return mv;
		} else {

			mv.addObject("exceptionMsg", errorResult.getMsg());
			mv.setViewName("/common/common.exception");
			return mv;
		}
	}
}
