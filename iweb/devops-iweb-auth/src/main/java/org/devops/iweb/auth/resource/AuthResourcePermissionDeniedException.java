package org.devops.iweb.auth.resource;

import org.devops.core.utils.constant.ApiResultCode;
import org.devops.core.utils.exception.CommonRuntimeException;

public class AuthResourcePermissionDeniedException extends CommonRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthResourcePermissionDeniedException(){
		super(ApiResultCode.PERMISSION_DENIED);
	}
}
