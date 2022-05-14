package org.devops.core.utils.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.devops.core.utils.constant.ApiResultCode;
import org.devops.core.utils.modules.log.request.RequestLogHandlerInterceptor;
import org.slf4j.MDC;

/**
 * Api 层返回的结果
 * @author xhz
 *
 */
@ApiModel(value = "ApiResult",description = "返回结果")
public class ApiResult<T> extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 请求标识，每个请求唯一，自动生成
	 */
	@ApiModelProperty("请求标识，每个请求唯一")
	private String requestId;

	/**
	 * 错误码
	 */
	@ApiModelProperty("错误码 200为成功，其他为失败")
	private int code = ApiResultCode.UNKNOWN.getCode();
	
	/**
	 * 错误码中文信息
	 */
	@ApiModelProperty("错误码中文信息")
	private String message = "";
	
	/**
	 * 内容
	 */
	@ApiModelProperty("内容")
	private T object = null;
	
//	public final static ApiResult<Object> SUCCESS = new ApiResult<Object>(ApiResultCode.SUCCESS);
//
//	public final static ApiResult<Object> FAILED  = new ApiResult<Object>(ApiResultCode.UNKNOWN);
	
	public ApiResult(){
		this(ApiResultCode.SUCCESS);
	}
	
	public ApiResult(int code){
		this.setCode(code);
		this.setRequestId(MDC.get(RequestLogHandlerInterceptor.DEFAULT_KEY));
	}
	
	public ApiResult(ApiResultCode apiResultCode){
		this(apiResultCode.getCode());
	}
	
	public ApiResult(int code,T object){
		this(code);
		this.setObject(object);
	}
	
	public ApiResult(int code,String message){
		this(code);
		this.setMessage(message);
	}
	
	public ApiResult(String message){
		this.setMessage(message);
	}
	
	public ApiResult(ApiResultCode apiResultCode,T object){
		this(apiResultCode.getCode(),object);
	}
	
	public ApiResult(ApiResultCode apiResultCode,String message){
		this(apiResultCode.getCode(),message);
	}

	
	public ApiResult(int code,String message,T object){
		this(code,object);
		this.setMessage(message);
	}
	
	public ApiResult(ApiResultCode apiResultCode,String message,T object){
		this(apiResultCode.getCode(),message,object);
	}
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
		
	}
	
	public void setCode(ApiResultCode resultEnum) {
		this.setCode(resultEnum.getCode());
	}

	public boolean isSuccess() {
		return this.code == ApiResultCode.SUCCESS.getCode();
	}

	public String getMessage() {
		if(message == null || "".equals(message)){
			return ApiResultCode.getMessage(code);
		}
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
	
	
	public static <T> ApiResult<T> getSuccess(T object){
		return new ApiResult<T>(ApiResultCode.SUCCESS,object);
	}
	
	public static <T> ApiResult<T> getSuccess(String message){
		return new ApiResult<T>(ApiResultCode.SUCCESS,message);
	}
	
	public static <T> ApiResult<T> getSuccess(String message, T object){
		return new ApiResult<T>(ApiResultCode.SUCCESS,message,object);
	}
	
	public static <T> ApiResult<T> getFailed(String message){
		return new ApiResult<T>(ApiResultCode.UNKNOWN,message);
	}
	
	public static <T> ApiResult<T> getFailed(ApiResultCode apiResultCode){
		return new ApiResult<T>(apiResultCode);
	}
	
	public static <T> ApiResult<T> get(){
		return new ApiResult<T>();
	}
	
	public static <T> ApiResult<T> get(ApiResultCode apiResultCode, String message){
		return new ApiResult<T>(apiResultCode,message);
	}
	
	public static <T> ApiResult<T> get(int code, String message){
		return new ApiResult<T>(code,message);
	}

	public static <T> ApiResult<T> success(){
		return new ApiResult<T>(ApiResultCode.SUCCESS);
	}

	public static <T> ApiResult<T> failed(){
		return new ApiResult<T>(ApiResultCode.UNKNOWN);
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
