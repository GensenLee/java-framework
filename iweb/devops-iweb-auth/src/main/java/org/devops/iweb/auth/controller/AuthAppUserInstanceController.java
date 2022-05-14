package org.devops.iweb.auth.controller;

import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.auth.context.AuthContext;
import org.devops.iweb.auth.resource.AuthResource;
import org.devops.iweb.auth.service.AuthAppUserInstanceService;
import org.devops.iweb.auth.vo.inVO.AuthAppUserInstanceSaveInVO;
import org.devops.iweb.auth.vo.inVO.AuthToInstanceInVO;
import org.devops.iweb.auth.vo.searchVO.AuthAppUserInstanceSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AuthResource(appId = 1)
public class AuthAppUserInstanceController {

	@Autowired
	private AuthAppUserInstanceService authAppUserInstanceService;
	
	@RequestMapping(path ="/admin/auth/user/authAppUserInstance/list")
	@AuthResource(parent="用户实例",name="列表搜索",ignoreApp=true)
	public ApiResult<Object> list()
			throws CommonException
	{
		return ApiResult.getSuccess(authAppUserInstanceService.list(AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/user/authAppUserInstance/listForPage")
	@AuthResource(parent="用户实例",name="列表搜索_页面")
	public ApiResult<Object> listForPage(@RequestBody AuthAppUserInstanceSearchVO svo)
			throws CommonException
	{
		if(LongUtil.isZero(svo.getUserId())){
			throw new CommonException("缺少参数user_id");
		}
		return ApiResult.getSuccess(authAppUserInstanceService.listForPage(svo,AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/user/authAppUserInstance/toInstance")
	public ApiResult<Object> toInstance(@RequestBody AuthToInstanceInVO authToInstanceInVO)
			throws CommonException
	{
		return ApiResult.getSuccess(authAppUserInstanceService.toInstance(authToInstanceInVO,AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authAppUserInstance/save",method = {RequestMethod.POST})
	@AuthResource(parent="用户实例",name="新增保存",type=AuthResource.BUTTON)
	public ApiResult<Object> save(@RequestBody AuthAppUserInstanceSaveInVO authAppUserInstanceSaveInVO)
			throws CommonException
	{
		return ApiResult.getSuccess(authAppUserInstanceService.save(authAppUserInstanceSaveInVO,AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authAppUserInstance/saveByRole",method = {RequestMethod.POST})
	@AuthResource(parent="用户实例",name="根据角色保存",type=AuthResource.BUTTON)
	public ApiResult<Object> saveByRole(@RequestBody AuthAppUserInstanceSaveInVO authAppUserInstanceSaveInVO)
			throws CommonException
	{
		return ApiResult.getSuccess(authAppUserInstanceService.saveByRole(authAppUserInstanceSaveInVO,AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authAppUserInstance/get")
	public ApiResult<Object> get(Long id)
			throws CommonException
	{
		return ApiResult.getSuccess(authAppUserInstanceService.get(id,AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authAppUserInstance/delete")
	@AuthResource(parent="用户实例",name="删除",type=AuthResource.BUTTON)
	public ApiResult<Object> delete(String ids)
			throws CommonException
	{
		return ApiResult.getSuccess(authAppUserInstanceService.delete(ids,AuthContext.getUserInfoInnerVO()));
	}
}
