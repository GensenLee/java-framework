package org.devops.iweb.auth.controller;

import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.auth.context.AuthContext;
import org.devops.iweb.auth.model.AuthApp;
import org.devops.iweb.auth.resource.AuthResource;
import org.devops.iweb.auth.service.AuthAppService;
import org.devops.iweb.auth.vo.searchVO.AuthAppSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AuthResource(appId = 1)
public class AuthAppController {

	@Autowired
	private AuthAppService authAppService;
	
	@RequestMapping(path ="/admin/auth/instance/authApp/list",method = {RequestMethod.POST})
	@AuthResource(parent="系统列表",name="列表搜索")
	public ApiResult<Object> list(@RequestBody AuthAppSearchVO svo)
			throws CommonException
	{
		return ApiResult.getSuccess(authAppService.list(svo));
	}
	
	
	@RequestMapping(path ="/admin/auth/instance/authApp/save",method = {RequestMethod.POST})
	@AuthResource(parent="系统列表",name="新增保存",type=AuthResource.BUTTON)
	public ApiResult<Object> save(@RequestBody AuthApp authApp)
			throws CommonException
	{
		return ApiResult.getSuccess(authAppService.save(authApp,AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authApp/del",method = {RequestMethod.POST})
	@AuthResource(parent="系统列表",name="删除",type=AuthResource.BUTTON)
	public ApiResult<Object> del(String ids)
			throws CommonException
	{
		return ApiResult.getSuccess(authAppService.del(ids));
	}
}
