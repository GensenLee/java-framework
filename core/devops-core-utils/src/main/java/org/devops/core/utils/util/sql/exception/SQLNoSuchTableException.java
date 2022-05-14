package org.devops.core.utils.util.sql.exception;

import org.devops.core.utils.constant.ApiResultCode;
import org.devops.core.utils.exception.CommonRuntimeException;

public class SQLNoSuchTableException extends CommonRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SQLNoSuchTableException(String message){
		super(ApiResultCode.NO_SUCH_TABLE,message);
	}
	
	public SQLNoSuchTableException(String message,Throwable throwable){
		super(message,throwable);
	}

}
