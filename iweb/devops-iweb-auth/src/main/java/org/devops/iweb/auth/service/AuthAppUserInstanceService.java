package org.devops.iweb.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.devops.core.model.dto.ModelWhere;
import org.devops.core.model.emun.ModelOperator;
import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.IntUtil;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.util.MapUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.verify.VerifyUtil;
import org.devops.core.utils.vo.PageResult;
import org.devops.iweb.auth.constant.AuthConstant;
import org.devops.iweb.auth.core.AuthAppUserInstanceCore;
import org.devops.iweb.auth.model.AuthApp;
import org.devops.iweb.auth.model.AuthAppInstance;
import org.devops.iweb.auth.model.AuthAppUserInstance;
import org.devops.iweb.auth.model.AuthAppUserInstanceMenu;
import org.devops.iweb.auth.model.AuthRole;
import org.devops.iweb.auth.model.AuthUser;
import org.devops.iweb.auth.repository.AuthAppInstanceRepository;
import org.devops.iweb.auth.repository.AuthAppRepository;
import org.devops.iweb.auth.repository.AuthAppUserInstanceMenuRepository;
import org.devops.iweb.auth.repository.AuthAppUserInstanceRepository;
import org.devops.iweb.auth.repository.AuthRoleRepository;
import org.devops.iweb.auth.repository.AuthUserRepository;
import org.devops.iweb.auth.vo.inVO.AuthAppUserInstanceSaveInVO;
import org.devops.iweb.auth.vo.inVO.AuthToInstanceInVO;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.devops.iweb.auth.vo.searchVO.AuthAppUserInstanceSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthAppUserInstanceService {

	@Autowired
	private AuthAppUserInstanceRepository authAppUserInstanceRepository;
	
	@Autowired
	private AuthAppInstanceRepository authAppInstanceRepository;
	
	@Autowired
	private AuthAppRepository authAppRepository;
	
	@Autowired
	private AuthUserRepository authUserRepository;
	
	
	@Autowired
	private AuthRoleRepository authRoleRepository;
	
	@Autowired
	private AuthAppUserInstanceMenuRepository authAppUserInstanceMenuRepository;
	
	@Autowired
	private AuthAppUserInstanceCore authAppUserInstanceCore;
	
	@SuppressWarnings("rawtypes")
	public Object list(UserInfoInnerVO userInfoInnerVO){
		List<AuthAppUserInstance> liAuthAppUserInstance = authAppUserInstanceRepository
				.where(AuthAppUserInstance.DEL_FLAG,AuthConstant.Common.NO)
				.where(AuthAppUserInstance.USER_ID,userInfoInnerVO.getUserId())
				.where(AuthAppUserInstance.COMPANY_ID,userInfoInnerVO.getCompanyId())
				.list();
		
		if(liAuthAppUserInstance.isEmpty()){
			return null;
		}
		
		List<Map> mapResult = authAppInstanceRepository.join(liAuthAppUserInstance,
				StringUtil.toLHCase(AuthAppUserInstance.APP_INSTANCE_ID),AuthAppInstance.ID,
				AuthAppInstance.NAME,"appInstanceName",AuthAppInstance.PARENT_APP_INSTANCE_ID,"parentAppInstanceId")
				.list(Map.class);
		
		mapResult = authAppRepository.join(mapResult,
				StringUtil.toLHCase(AuthAppUserInstance.APP_ID),AuthAppInstance.ID,
				AuthApp.CN_NAME,"appName",
				AuthApp.CODE,"appCode",
				AuthApp.URL,"url")
				.list(Map.class);

		mapResult = authAppInstanceRepository.join(mapResult,
				StringUtil.toLHCase(AuthAppInstance.PARENT_APP_INSTANCE_ID),AuthAppInstance.ID,
				AuthAppInstance.NAME,"appParentName")
				.list(Map.class);

		mapResult = authRoleRepository.join(mapResult, StringUtil.toLHCase(AuthAppUserInstance.ROLE_ID), AuthRole.ID,
				AuthRole.NAME, "roleName")
				.list(Map.class);

		return MapUtil.toMapListResultExcludeFields(mapResult,"isAdmin");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object listForPage(AuthAppUserInstanceSearchVO svo,UserInfoInnerVO userInfoInnerVO){
		
		ModelWhere mw = new ModelWhere();
		
		mw.add(AuthAppUserInstance.DEL_FLAG,AuthConstant.Common.NO)
		.add(AuthAppUserInstance.USER_ID,svo.getUserId())
		.add(AuthAppUserInstance.COMPANY_ID,userInfoInnerVO.getCompanyId());
		
		
		PageResult pageResult = new PageResult();
		List<AuthAppUserInstance> liAuthAppUserInstance = authAppUserInstanceRepository
				.where(mw).limit(svo.getStart(),svo.getSize()).list();
		
		if(liAuthAppUserInstance.isEmpty()){
			return pageResult;
		}
		
		List<Map> mapResult = authAppInstanceRepository.join(liAuthAppUserInstance,
				StringUtil.toLHCase(AuthAppUserInstance.APP_INSTANCE_ID),AuthAppInstance.ID,
				AuthAppInstance.NAME,"appInstanceName")
				.list(Map.class);
		
		mapResult = authAppRepository.join(mapResult,
				StringUtil.toLHCase(AuthAppUserInstance.APP_ID),AuthApp.ID,
				AuthApp.CN_NAME,"appName",
				AuthApp.URL,"url")
				.list(Map.class);
		
		mapResult = authRoleRepository.join(mapResult,
				StringUtil.toLHCase(AuthAppUserInstance.ROLE_ID),AuthRole.ID,
				AuthRole.NAME,"roleName")
				.list(Map.class);
		
		pageResult.setList(MapUtil.toMapListResultExcludeFields(mapResult,"isAdmin"));
		pageResult.setCount(authAppUserInstanceRepository
				.where(mw).count());
		return pageResult;
	}
	
	public Object toInstance(AuthToInstanceInVO authToInstanceInVO,UserInfoInnerVO userInfoInnerVO) throws CommonException{
		VerifyUtil.verify(authToInstanceInVO);
		AuthAppUserInstance authAppUserInstance = authAppUserInstanceRepository
				.where(AuthAppUserInstance.DEL_FLAG,AuthConstant.Common.NO)
				.where(AuthAppUserInstance.ID,authToInstanceInVO.getUserInstanceId())
				.where(AuthAppUserInstance.USER_ID,userInfoInnerVO.getUserId())
				.get();
		
		if(authAppUserInstance == null){
			throw new CommonException("找不到用户对应的系统实例");
		}
		authAppUserInstanceCore.toInstance(authAppUserInstance,userInfoInnerVO);
		return null;
	}
	
	public Object save(AuthAppUserInstanceSaveInVO authAppUserInstanceSaveInVO,UserInfoInnerVO userInfoInnerVO)
		throws CommonException
	{
		VerifyUtil.verify(authAppUserInstanceSaveInVO,
				AuthAppUserInstance.USER_ID,AuthAppUserInstance.APP_INSTANCE_ID,AuthAppUserInstance.IS_ADMIN);
		if(LongUtil.isNotZero(authAppUserInstanceSaveInVO.getId())){
			BeanUtil.initModify(authAppUserInstanceSaveInVO, userInfoInnerVO.getUserName());
			AuthAppUserInstance authAppUserInstance = authAppUserInstanceRepository
					.where(AuthAppUserInstance.APP_ID,authAppUserInstanceSaveInVO.getAppId())
					.where(AuthAppUserInstance.COMPANY_ID,authAppUserInstanceSaveInVO.getCompanyId())
					.where(AuthAppUserInstance.USER_ID,authAppUserInstanceSaveInVO.getUserId())
					.get(authAppUserInstanceSaveInVO.getId());
			if(authAppUserInstance == null || LongUtil.toLong(authAppUserInstance.getCompanyId()) != userInfoInnerVO.getCompanyId()){
				throw new CommonException("无此用户实例权限");
			}
			if(authAppUserInstance.getIsEdit() == AuthConstant.Common.NO){
				throw new CommonException("此实例不能被修改");
			}
			authAppUserInstanceRepository.update(authAppUserInstanceSaveInVO);
		}else{
			BeanUtil.initCreate(authAppUserInstanceSaveInVO, userInfoInnerVO.getUserName());
			authAppUserInstanceSaveInVO.setCompanyId(userInfoInnerVO.getCompanyId());
			AuthAppInstance authAppInstance = authAppInstanceRepository
					.where(AuthAppUserInstance.COMPANY_ID,authAppUserInstanceSaveInVO.getCompanyId())
					.where(AuthAppInstance.ID,authAppUserInstanceSaveInVO.getAppInstanceId()).get();
			if(authAppInstance == null || authUserRepository
					.where(AuthUser.COMPANY_ID,authAppUserInstanceSaveInVO.getCompanyId())
					.isNotExists(authAppUserInstanceSaveInVO.getUserId())){
				throw new CommonException("无此用户实例权限");
			}
			authAppUserInstanceSaveInVO.setAppId(authAppInstance.getAppId());
			authAppUserInstanceRepository.insert(authAppUserInstanceSaveInVO);
		}
		
		//删除对应关系
		authAppUserInstanceMenuRepository
		.where(AuthAppUserInstanceMenu.APP_USER_INSTANCE_ID,authAppUserInstanceSaveInVO.getId())
		.delete();
		if(authAppUserInstanceSaveInVO.getIsAdmin() == AuthConstant.Common.NO
				&& authAppUserInstanceSaveInVO.getMenus() != null){
			List<AuthAppUserInstanceMenu> liInsertMapperDao = new ArrayList<AuthAppUserInstanceMenu>();
			for(AuthAppUserInstanceMenu authAppUserInstanceMenu : authAppUserInstanceSaveInVO.getMenus()){
				authAppUserInstanceMenu.setAppUserInstanceId(authAppUserInstanceSaveInVO.getId());
			}
			liInsertMapperDao = authAppUserInstanceSaveInVO.getMenus();
			if(liInsertMapperDao.size() != 0){
				authAppUserInstanceMenuRepository.insert(liInsertMapperDao);
			}
		}
		
		return null;
	}
	
	
	public Object saveByRole(AuthAppUserInstanceSaveInVO authAppUserInstanceSaveInVO,UserInfoInnerVO userInfoInnerVO)
			throws CommonException
		{
			VerifyUtil.verify(authAppUserInstanceSaveInVO,
					AuthAppUserInstance.USER_ID,AuthAppUserInstance.APP_INSTANCE_ID,AuthAppUserInstance.ROLE_ID);
			if(LongUtil.isNotZero(authAppUserInstanceSaveInVO.getId())){
				BeanUtil.initModify(authAppUserInstanceSaveInVO, userInfoInnerVO.getUserName());
				AuthAppUserInstance authAppUserInstance = authAppUserInstanceRepository
						.where(AuthAppUserInstance.APP_INSTANCE_ID,authAppUserInstanceSaveInVO.getAppInstanceId())
						.where(AuthAppUserInstance.COMPANY_ID,authAppUserInstanceSaveInVO.getCompanyId())
						.where(AuthAppUserInstance.USER_ID,authAppUserInstanceSaveInVO.getUserId())
						.get(authAppUserInstanceSaveInVO.getId());
				if(authAppUserInstance == null || LongUtil.toLong(authAppUserInstance.getCompanyId()) != userInfoInnerVO.getCompanyId()){
					throw new CommonException("无此用户实例权限");
				}
				if(authAppUserInstance.getIsEdit() == AuthConstant.Common.NO){
					throw new CommonException("此实例不能被修改");
				}
				authAppUserInstanceRepository.update(authAppUserInstanceSaveInVO);
			}else{
				BeanUtil.initCreate(authAppUserInstanceSaveInVO, userInfoInnerVO.getUserName());
				authAppUserInstanceSaveInVO.setCompanyId(userInfoInnerVO.getCompanyId());
				AuthAppInstance authAppInstance = authAppInstanceRepository
						.where(AuthAppUserInstance.COMPANY_ID,authAppUserInstanceSaveInVO.getCompanyId())
						.where(AuthAppInstance.ID,authAppUserInstanceSaveInVO.getAppInstanceId()).get();
				if(authAppInstance == null || authUserRepository
						.where(AuthUser.COMPANY_ID,authAppUserInstanceSaveInVO.getCompanyId())
						.isNotExists(authAppUserInstanceSaveInVO.getUserId())){
					throw new CommonException("无此用户实例权限");
				}
				authAppUserInstanceSaveInVO.setAppId(authAppInstance.getAppId());
				authAppUserInstanceRepository.insert(authAppUserInstanceSaveInVO);
			}
			return null;
		}
	
	public Object get(Long id,UserInfoInnerVO userInfoInnerVO) throws CommonException{
		AuthAppUserInstance authAppUserInstance = authAppUserInstanceRepository
				.where(AuthAppUserInstance.COMPANY_ID,userInfoInnerVO.getCompanyId())
				.where(AuthAppUserInstance.ID,id)
				.get(AuthAppUserInstance.class);
		if(authAppUserInstance == null){
			throw new CommonException("无此用户实例权限");
		}
		Map<String,Object> result = FastJsonUtils.getMap(authAppUserInstance);
		
		if(IntUtil.toInt(authAppUserInstance.getIsAdmin()) == AuthConstant.Common.NO){
			result.put("menuList", authAppUserInstanceMenuRepository.where(AuthAppUserInstanceMenu.APP_USER_INSTANCE_ID,authAppUserInstance.getId()).list());
		}
		
		return result;
	}
	
	public Object delete(String ids,UserInfoInnerVO userInfoInnerVO) throws CommonException{
		if(StringUtil.isEmpty(ids)){
			throw new CommonException("请传入需要删除的id");
		}
		authAppUserInstanceRepository
		.where(AuthAppUserInstance.COMPANY_ID,userInfoInnerVO.getCompanyId())
		.where(AuthAppUserInstance.ID,ids,ModelOperator.IN)
		.where(AuthAppUserInstance.IS_EDIT,AuthConstant.Common.YES)
		.update(AuthAppUserInstance.DEL_FLAG,AuthConstant.Common.YES);
		return null;
	}
}
