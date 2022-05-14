package org.devops.iweb.auth.controller;

import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.auth.context.AuthContext;
import org.devops.iweb.auth.model.AuthKeyValue;
import org.devops.iweb.auth.resource.AuthResource;
import org.devops.iweb.auth.service.AuthConfigService;
import org.devops.iweb.auth.vo.searchVO.AuthConfigSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AuthResource(appId = 1)
public class AuthConfigController {

	@Autowired
	private AuthConfigService authConfigService;
	
	@RequestMapping(path ="/admin/auth/instance/authConfig/list",method = {RequestMethod.POST})
	@AuthResource(parent="参数列表",name="列表搜索")
	public ApiResult<Object> list(@RequestBody AuthConfigSearchVO svo)
			throws CommonException
	{
		return ApiResult.getSuccess( authConfigService.list(svo));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authConfig/save",method = {RequestMethod.POST})
	@AuthResource(parent="参数列表",name="新增保存",type=AuthResource.BUTTON)
	public ApiResult<Object> save(@RequestBody AuthKeyValue authKeyValue)
			throws CommonException
	{
		return ApiResult.getSuccess( authConfigService.save(authKeyValue,AuthContext.getUserInfoInnerVO()));
	}
}
