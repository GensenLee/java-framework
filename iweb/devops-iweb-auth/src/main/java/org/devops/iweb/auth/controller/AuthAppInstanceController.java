package org.devops.iweb.auth.controller;

import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.auth.context.AuthContext;
import org.devops.iweb.auth.model.AuthAppInstance;
import org.devops.iweb.auth.resource.AuthResource;
import org.devops.iweb.auth.service.AuthAppInstanceService;
import org.devops.iweb.auth.vo.searchVO.AuthAppInstanceSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AuthResource(appId = 1)
public class AuthAppInstanceController {

	@Autowired
	private AuthAppInstanceService authAppInstanceService;
	
	@RequestMapping(path ="/admin/auth/instance/authAppInstance/list",method = {RequestMethod.POST})
	@AuthResource(parent="系统实例",name="列表搜索")
	public ApiResult<Object> list(@RequestBody AuthAppInstanceSearchVO svo)
			throws CommonException
	{
		return ApiResult.getSuccess(authAppInstanceService.list(svo,AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authAppInstance/save",method = {RequestMethod.POST})
	@AuthResource(parent="系统实例",name="新增保存",type=AuthResource.BUTTON)
	public ApiResult<Object> save(@RequestBody AuthAppInstance authAppInstance)
			throws CommonException
	{
		return ApiResult.getSuccess(authAppInstanceService.save(authAppInstance,AuthContext.getUserInfoInnerVO()));
	}
}
