package org.devops.iweb.auth.controller;

import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.auth.context.AuthContext;
import org.devops.iweb.auth.resource.AuthResource;
import org.devops.iweb.auth.service.AuthCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AuthResource(appId = 1)
public class AuthCommonController {

	@Autowired
	private AuthCommonService authCommonService;
	
	@RequestMapping(path ="/admin/auth/common/authCommon/get")
	public ApiResult<Object> getForCommon()
			throws CommonException
	{
		return ApiResult.getSuccess(authCommonService.getForCommon());
	}
	
	@RequestMapping(path ="/admin/auth/user/authCommon/get")
	public ApiResult<Object> getForUser()
			throws CommonException
	{
		return ApiResult.getSuccess(authCommonService.getForUser());
	}
	
	@RequestMapping(path ="/admin/auth/instance/authCommon/get")
	public ApiResult<Object> getForInstance()
			throws CommonException
	{
		
		return ApiResult.getSuccess( authCommonService.getForInstance(AuthContext.getUserInfoInnerVO()));
	}
}
