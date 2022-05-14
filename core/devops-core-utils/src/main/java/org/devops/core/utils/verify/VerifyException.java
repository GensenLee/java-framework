package org.devops.core.utils.verify;

import org.devops.core.utils.constant.ApiResultCode;
import org.devops.core.utils.exception.CommonRuntimeException;

/**
 * @author xhz
 * 异常
 */
public class VerifyException extends CommonRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String field;
	
	public VerifyException(String field)
	{
		super(ApiResultCode.PARAMETER_VERIFY_ERROR,field);
		this.field = field;
	}
	
	public VerifyException(String field,String msg)
	{
		super(ApiResultCode.PARAMETER_VERIFY_ERROR,msg);
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	
	
}
