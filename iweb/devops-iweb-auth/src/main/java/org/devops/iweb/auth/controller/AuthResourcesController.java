package org.devops.iweb.auth.controller;

import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.auth.context.AuthContext;
import org.devops.iweb.auth.resource.AuthResource;
import org.devops.iweb.auth.service.AuthResourcesService;
import org.devops.iweb.auth.vo.inVO.AuthResourcesSaveInVO;
import org.devops.iweb.auth.vo.searchVO.AuthResourcesSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AuthResource(appId = 1)
public class AuthResourcesController {

	@Autowired
	private AuthResourcesService authResourcesService;
	
	@RequestMapping(path ="/admin/auth/instance/authResources/list",method = {RequestMethod.POST})
	@AuthResource(parent="资源列表",name="列表搜索")
	public ApiResult<Object> list(@RequestBody AuthResourcesSearchVO svo)
			throws CommonException
	{
		return ApiResult.getSuccess(authResourcesService.list(svo));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authResources/listForApp",method = {RequestMethod.POST})
	@AuthResource(parent="资源列表",name="列表搜索_APP")
	public ApiResult<Object> listForApp(@RequestBody AuthResourcesSearchVO svo)
			throws CommonException
	{
		return ApiResult.getSuccess(authResourcesService.listForApp(svo));
	}
	
	
	@RequestMapping(path ="/admin/auth/instance/authResources/save",method = {RequestMethod.POST})
	@AuthResource(parent="资源列表",name="新增保存",type=AuthResource.BUTTON)
	public ApiResult<Object> save(@RequestBody AuthResourcesSaveInVO authResourcesSaveInVO)
			throws CommonException
	{
		return ApiResult.getSuccess(authResourcesService.save(authResourcesSaveInVO,AuthContext.getUserInfoInnerVO()));
	}

}
