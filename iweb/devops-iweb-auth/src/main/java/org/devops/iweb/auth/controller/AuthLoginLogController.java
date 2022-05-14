package org.devops.iweb.auth.controller;

import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.auth.context.AuthContext;
import org.devops.iweb.auth.resource.AuthResource;
import org.devops.iweb.auth.service.AuthLoginLogService;
import org.devops.iweb.auth.vo.searchVO.AuthLoginLogSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AuthResource(appId = 1)
public class AuthLoginLogController {

	@Autowired
	private AuthLoginLogService authLoginLogService;
	
	@RequestMapping(path ="/admin/auth/instance/authLoginLog/list",method = {RequestMethod.POST})
	@AuthResource(parent="登录日志",name="列表搜索")
	public ApiResult<Object> list(@RequestBody AuthLoginLogSearchVO svo)
			throws CommonException
	{
		return ApiResult.getSuccess(authLoginLogService.list(svo,AuthContext.getUserInfoInnerVO()));
	}
}
