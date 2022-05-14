package org.devops.iweb.auth.controller;

import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.auth.context.AuthContext;
import org.devops.iweb.auth.resource.AuthResource;
import org.devops.iweb.auth.service.AuthRoleService;
import org.devops.iweb.auth.vo.inVO.AuthRoleSaveInVO;
import org.devops.iweb.auth.vo.searchVO.AuthRoleSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AuthResource(appId = 1)
public class AuthRoleController {

	@Autowired
	private AuthRoleService authRoleService;
	
	@RequestMapping(path ="/admin/auth/user/authRole/list")
	@AuthResource(parent="角色列表",name="列表搜索")
	public ApiResult<Object> list(@RequestBody AuthRoleSearchVO svo)
			throws CommonException
	{
		return ApiResult.getSuccess(authRoleService.list(svo,AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/user/authRole/listForPage")
	@AuthResource(parent="角色列表",name="列表搜索_页面")
	public ApiResult<Object> listForPage(@RequestBody AuthRoleSearchVO svo)
			throws CommonException
	{
		return ApiResult.getSuccess(authRoleService.listForPage(svo,AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authRole/save",method = {RequestMethod.POST})
	@AuthResource(parent="角色列表",name="修改",type=AuthResource.BUTTON)
	public ApiResult<Object> save(@RequestBody AuthRoleSaveInVO authAppUserInstanceSaveInVO)
			throws CommonException
	{
		return ApiResult.getSuccess(authRoleService.save(authAppUserInstanceSaveInVO,AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authRole/singleSave",method = {RequestMethod.POST})
	@AuthResource(parent="角色列表",name="新增",type=AuthResource.BUTTON)
	public ApiResult<Object> singleSave(@RequestBody AuthRoleSaveInVO authAppUserInstanceSaveInVO)
			throws CommonException
	{
		return ApiResult.getSuccess(authRoleService.singleSave(authAppUserInstanceSaveInVO,AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authRole/get")
	public ApiResult<Object> get(Long id)
			throws CommonException
	{
		return ApiResult.getSuccess(authRoleService.get(id,AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authRole/delete")
	@AuthResource(parent="角色列表",name="删除",type=AuthResource.BUTTON)
	public ApiResult<Object> delete(String ids)
			throws CommonException
	{
		return ApiResult.getSuccess(authRoleService.delete(ids,AuthContext.getUserInfoInnerVO()));
	}
}
