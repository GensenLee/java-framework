package org.devops.core.utils.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {

	/**
	 * 获取域名
	 * @param request
	 * @return
	 */
	public static String getDomain(HttpServletRequest request){
		StringBuffer url = request.getRequestURL();  
		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();  
		return tempContextUrl;
	}

	public static Map<String, Object> getRequestQueryAsMap(HttpServletRequest request){
		Map<String, Object> result = new HashMap<>();
		Map<String, String[]> parameterMap = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			result.put(entry.getKey(), entry.getValue()[0]);
		}
		return result;
	}

	public static Map<String, Object> getRequestBodyAsMap(HttpServletRequest request){
		String body = null;
		try {
			body = StreamUtil.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (StringUtil.isEmpty(body)) {
			return null;
		}
		return FastJsonUtils.getMap(body);
	}

	/**
	 * @param request
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T getRequestBodyAsObject(HttpServletRequest request, Class<T> clazz){
		String body = null;
		try {
			body = StreamUtil.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (StringUtil.isEmpty(body)) {
			return null;
		}
		return FastJsonUtils.getBean(body, clazz);
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getRequestBodyAsString(HttpServletRequest request){
		String body = null;
		try {
			body = StreamUtil.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return body;
	}

	/**
	 * @param response
	 * @param content
	 */
	public static void writeToResponse(HttpServletResponse response, String content) {
		response.addHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.write(content);
		writer.flush();
		try {
			response.flushBuffer();
		} catch (IOException ignored) {
		}
	}

	/**
	 * @param response
	 * @param content
	 */
	public static void writeToResponse(HttpServletResponse response, Object content){
		if (content instanceof String){
			writeToResponse(response, (String) content);
			return;
		}
		response.addHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.write(FastJsonUtils.toJsonString(content));
		writer.flush();
		try {
			response.flushBuffer();
		} catch (IOException ignored) {
		}
	}
}
