package org.devops.core.utils.vo;

import lombok.Data;
import org.devops.core.utils.util.StreamUtil;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Description 
 * @author xhz
 * @date 2017年5月12日 上午10:02:48
 */
@Data
public class HttpResult extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 检查的URL
	 */
	private String url;
	/**
	 * 请求时间
	 */
	private long reqTime;
	/**
	 * 响应时间
	 */
	private long respPeriod;
	/**
	 * 是否超时
	 */
	private boolean timeout = false;
	/**
	 * 是否出现异常
	 */
	private boolean exception;
	/**
	 * 异常信息
	 */
	private String exceptionMessage;
	/**
	 * 请求的head头
	 */
	private String requestHeader;
	/**
	 * 返回的head头
	 */
	private String responseHeader;
	/**
	 * 返回的数据
	 */
	private InputStream response;
	
	private int responseCode;

	/**
	 * 获取字符串结果
	 * @return
	 */
	public String readResponseAsString(){
		try {
			return StreamUtil.copyToString(this.response, StandardCharsets.UTF_8);
		} catch (Exception ignored) {
		}
		return null;
	}
	
}
