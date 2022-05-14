package org.devops.iweb.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.vo.ApiResult;
import org.devops.core.utils.vo.PageResult;
import org.devops.iweb.auth.context.AuthContext;
import org.devops.iweb.auth.model.AuthUser;
import org.devops.iweb.auth.resource.AuthResource;
import org.devops.iweb.auth.service.AuthUserService;
import org.devops.iweb.auth.vo.inVO.AuthLoginInVO;
import org.devops.iweb.auth.vo.inVO.AuthUserRepasswdInVO;
import org.devops.iweb.auth.vo.outVO.AuthUserListOutVO;
import org.devops.iweb.auth.vo.searchVO.AuthUserSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AuthResource(appId = 1)
public class AuthUserController {



	@Autowired
	private AuthUserService authUserService;
	

	@RequestMapping(path ="/admin/auth/common/authUser/login",method = {RequestMethod.POST})
	public ApiResult<Object> login(@RequestBody AuthLoginInVO authLoginInVO,HttpServletRequest req, HttpServletResponse resp)
			throws CommonException
	{
		return ApiResult.getSuccess("登录成功",authUserService.login(authLoginInVO,req,resp));
	}
	
	@RequestMapping("/admin/auth/user/authUser/logout")
    public ApiResult<Object> logout(HttpServletRequest req, HttpServletResponse resp)
            throws CommonException {
    	return ApiResult.getSuccess(authUserService.logout(AuthContext.getUserInfoInnerVO(), req, resp));
    }
	
	@RequestMapping(path ="/admin/auth/instance/authUser/list",method = {RequestMethod.POST})
	@AuthResource(parent="用户列表",name="列表搜索")
	public ApiResult<Object> list(@RequestBody AuthUserSearchVO svo)
			throws CommonException
	{
		return ApiResult.getSuccess(authUserService.list(svo,AuthContext.getUserInfoInnerVO()));
	}

	@RequestMapping(path ="/admin/auth/instance/v2/authUser/list",method = {RequestMethod.POST})
	@AuthResource(parent="用户列表v2",name="列表搜索")
	public ApiResult<PageResult<AuthUserListOutVO>> listV2(@RequestBody AuthUserSearchVO svo)
			throws CommonException
	{
		return ApiResult.getSuccess(authUserService.listV2(svo,AuthContext.getUserInfoInnerVO()));
	}

	@RequestMapping(path ="/admin/auth/instance/authUser/save",method = {RequestMethod.POST})
	@AuthResource(parent="用户列表",name="新增保存",type=AuthResource.BUTTON)
	public ApiResult<Object> save(@RequestBody AuthUser authUser)
			throws CommonException
	{
		return ApiResult.getSuccess(authUserService.save(authUser,AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authApp/rePasswd",method = {RequestMethod.POST})
	public ApiResult<Object> rePasswd(@RequestBody AuthUserRepasswdInVO authUserRepasswdInVO)
			throws CommonException
	{
		return ApiResult.getSuccess(authUserService.rePasswd(authUserRepasswdInVO,AuthContext.getUserInfoInnerVO()));
	}
	
	@RequestMapping(path ="/admin/auth/instance/authUser/active",method = {RequestMethod.POST})
	@AuthResource(parent="用户列表",name="账号激活",type=AuthResource.BUTTON)
	public ApiResult<Object> active(@RequestBody AuthUser authUser)
			throws CommonException
	{
		return ApiResult.getSuccess(authUserService.active(authUser,AuthContext.getUserInfoInnerVO()));
	}
	
}
