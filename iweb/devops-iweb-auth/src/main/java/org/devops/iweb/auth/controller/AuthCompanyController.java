package org.devops.iweb.auth.controller;

import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.auth.context.AuthContext;
import org.devops.iweb.auth.resource.AuthResource;
import org.devops.iweb.auth.service.AuthCompanyService;
import org.devops.iweb.auth.vo.inVO.AuthCompanyInVO;
import org.devops.iweb.auth.vo.searchVO.AuthCompanySearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AuthResource(appId = 1)
public class AuthCompanyController {

	@Autowired
	private AuthCompanyService authCompanyService;
	
	@RequestMapping(path ="/admin/auth/instance/authCompany/list",method = {RequestMethod.POST})
	@AuthResource(parent="集团列表",name="列表搜索")
	public ApiResult<Object> list(@RequestBody AuthCompanySearchVO svo)
			throws CommonException
	{
		return ApiResult.getSuccess(authCompanyService.list(svo));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authCompany/listAll",method = {RequestMethod.POST})
	public ApiResult<Object> listAll(@RequestBody AuthCompanySearchVO svo)
			throws CommonException
	{
		return ApiResult.getSuccess(authCompanyService.listAll(svo));
	}
	
	
	@RequestMapping(path ="/admin/auth/instance/authCompany/save",method = {RequestMethod.POST})
	@AuthResource(parent="集团列表",name="新增保存",type=AuthResource.BUTTON)
	public ApiResult<Object> save(@RequestBody AuthCompanyInVO authCompany)
			throws CommonException
	{
		return ApiResult.getSuccess( authCompanyService.save(authCompany,AuthContext.getUserInfoInnerVO()));
	}
}
