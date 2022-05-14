package org.devops.iweb.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.devops.core.model.dto.ModelWhere;
import org.devops.core.model.emun.ModelOperator;
import org.devops.core.utils.constant.ApiResultCode;
import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.IntUtil;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.util.MapUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.verify.VerifyUtil;
import org.devops.core.utils.vo.PageResult;
import org.devops.iweb.auth.constant.AuthConstant;
import org.devops.iweb.auth.model.AuthApp;
import org.devops.iweb.auth.model.AuthAppInstance;
import org.devops.iweb.auth.model.AuthAppUserInstance;
import org.devops.iweb.auth.model.AuthRole;
import org.devops.iweb.auth.model.AuthRoleMenu;
import org.devops.iweb.auth.repository.AuthAppInstanceRepository;
import org.devops.iweb.auth.repository.AuthAppRepository;
import org.devops.iweb.auth.repository.AuthAppUserInstanceRepository;
import org.devops.iweb.auth.repository.AuthRoleMenuRepository;
import org.devops.iweb.auth.repository.AuthRoleRepository;
import org.devops.iweb.auth.vo.inVO.AuthRoleSaveInVO;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.devops.iweb.auth.vo.searchVO.AuthRoleSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthRoleService {

	@Autowired
	private AuthRoleRepository authRoleRepository;
	
	@Autowired
	private AuthAppRepository authAppRepository;
	
	@Autowired
	private AuthAppInstanceRepository authAppInstanceRepository;

	@Autowired
	private AuthAppUserInstanceRepository authAppUserInstanceRepository;
	
	@Autowired
	private AuthRoleMenuRepository authRoleMenuRepository;
	
	@SuppressWarnings("rawtypes")
	public Object list(AuthRoleSearchVO svo,UserInfoInnerVO userInfoInnerVO){
		
		ModelWhere mw = new ModelWhere();
		
		mw.add(AuthRole.DEL_FLAG,AuthConstant.Common.NO);
		
		if(StringUtil.isNotEmpty(svo.getQuickSearch())){
			mw.add(AuthRole.NAME,svo.getQuickSearch(),ModelOperator.LIKE);
		}
		
		if(LongUtil.isNotZero(svo.getAppId())){
			authRoleRepository.where(AuthRole.APP_ID,svo.getAppId());
		}else if(LongUtil.isNotZero(svo.getAppInstanceId())){
			AuthAppInstance authAppInstance = authAppInstanceRepository
				.where(AuthAppInstance.COMPANY_ID,userInfoInnerVO.getCompanyId())
				.where(AuthAppInstance.ID,svo.getAppInstanceId())
				.get();
			if(authAppInstance != null){
				authRoleRepository.where(AuthRole.APP_ID,authAppInstance.getAppId());
			}
		}
		
		List<AuthRole> liAuthRole = authRoleRepository
				.where(mw)
				.where(AuthRole.DEL_FLAG,AuthConstant.Common.NO)
				.where(AuthRole.COMPANY_ID,userInfoInnerVO.getCompanyId())
				.list();
		
		if(liAuthRole.isEmpty()){
			return null;
		}
		
		List<Map> mapResult = authAppRepository.join(liAuthRole,
				AuthRole.APP_ID,AuthApp.ID,
				AuthApp.CN_NAME,"appName",
				AuthApp.URL,"url")
				.list(Map.class);
		
		return MapUtil.toMapListResultExcludeFields(mapResult,"isAdmin");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object listForPage(AuthRoleSearchVO svo,UserInfoInnerVO userInfoInnerVO){
		
		ModelWhere mw = new ModelWhere();
		
		mw.add(AuthRole.DEL_FLAG,AuthConstant.Common.NO);
		
		if(StringUtil.isNotEmpty(svo.getQuickSearch())){
			mw.add(AuthRole.NAME,svo.getQuickSearch(),ModelOperator.LIKE);
		}
		
		if(LongUtil.isZero(svo.getCompanyId())){
			mw.add(AuthRole.COMPANY_ID,userInfoInnerVO.getCompanyId());
		}else if(LongUtil.toLong(svo.getCompanyId()) != AuthConstant.Common.ALL){
			mw.add(AuthRole.COMPANY_ID,svo.getCompanyId());
		}
		
		
		PageResult pageResult = new PageResult();
		List<AuthRole> liAuthRole = authRoleRepository
				.where(mw).limit(svo.getStart(),svo.getSize()).list();
		
		if(liAuthRole.isEmpty()){
			return pageResult;
		}
		
		List<Map> mapResult = authAppRepository.join(liAuthRole,
				AuthRole.APP_ID,AuthApp.ID,
				AuthApp.CN_NAME,"appName",
				AuthApp.URL,"url")
				.list(Map.class);
		
		pageResult.setList(MapUtil.toMapListResultExcludeFields(mapResult,"isAdmin"));
		pageResult.setCount(authRoleRepository
				.where(mw).count());
		return pageResult;
	}
	
	public Object save(AuthRoleSaveInVO authRoleSaveInVO,UserInfoInnerVO userInfoInnerVO)
		throws CommonException
	{
		VerifyUtil.verify(authRoleSaveInVO,
				AuthRole.APP_ID,AuthRole.IS_ADMIN);
		
		if(LongUtil.isNotZero(authRoleSaveInVO.getId())){
			BeanUtil.initModify(authRoleSaveInVO, userInfoInnerVO.getUserName());
			
			AuthRole authRole = authRoleRepository
					.where(AuthRole.APP_ID,authRoleSaveInVO.getAppId())
					.where(AuthRole.COMPANY_ID,authRoleSaveInVO.getCompanyId())
					.get(authRoleSaveInVO.getId());
			
			if(authRole == null || LongUtil.toLong(authRole.getCompanyId()) != userInfoInnerVO.getCompanyId()){
				throw new CommonException("无此角色");
			}
			
			if(authRole.getIsEdit() == AuthConstant.Common.NO){
				throw new CommonException("此角色不能被修改");
			}
			authRoleRepository.update(authRoleSaveInVO);
		}else{
			BeanUtil.initCreate(authRoleSaveInVO, userInfoInnerVO.getUserName());
			authRoleSaveInVO.setCompanyId(userInfoInnerVO.getCompanyId());
			
			authRoleRepository.insert(authRoleSaveInVO);
		}
		
		//删除对应关系
		authRoleMenuRepository
		.where(AuthRoleMenu.ROLE_ID,authRoleSaveInVO.getId())
		.delete();
		
		if(authRoleSaveInVO.getIsAdmin() == AuthConstant.Common.NO
				&& authRoleSaveInVO.getMenus() != null){
			
			List<AuthRoleMenu> liInsertMapperDao = new ArrayList<AuthRoleMenu>();
			for(AuthRoleMenu authRoleMenu : authRoleSaveInVO.getMenus()){
				authRoleMenu.setRoleId(authRoleSaveInVO.getId());
			}
			liInsertMapperDao = authRoleSaveInVO.getMenus();
			if(liInsertMapperDao.size() != 0){
				authRoleMenuRepository.insert(liInsertMapperDao);
			}
		}
		
		return null;
	}
	
	public Object singleSave(AuthRoleSaveInVO authRoleSaveInVO,UserInfoInnerVO userInfoInnerVO)
			throws CommonException
		{
			VerifyUtil.verify(authRoleSaveInVO,
					AuthRole.APP_ID,AuthRole.NAME,AuthRole.CODE);
			
			if(LongUtil.isNotZero(authRoleSaveInVO.getId())){
				BeanUtil.initModify(authRoleSaveInVO, userInfoInnerVO.getUserName());
				
				AuthRole authRole = authRoleRepository
						.where(AuthRole.APP_ID,authRoleSaveInVO.getAppId())
						.where(AuthRole.COMPANY_ID,authRoleSaveInVO.getCompanyId())
						.get(authRoleSaveInVO.getId());
				
				if(authRole == null || LongUtil.toLong(authRole.getCompanyId()) != userInfoInnerVO.getCompanyId()){
					throw new CommonException("无此角色");
				}
				
				if(authRole.getIsEdit() == AuthConstant.Common.NO){
					throw new CommonException("此角色不能被修改");
				}
				authRoleRepository.include(AuthRole.NAME).update(authRoleSaveInVO);
			}else{
				BeanUtil.initCreate(authRoleSaveInVO, userInfoInnerVO.getUserName());
				authRoleSaveInVO.setCompanyId(userInfoInnerVO.getCompanyId());
				authRoleRepository.insert(authRoleSaveInVO);
			}

			return null;
		}
	
	public Object get(Long id,UserInfoInnerVO userInfoInnerVO) throws CommonException{
		AuthRole authRole = authRoleRepository
				.where(AuthRole.COMPANY_ID,userInfoInnerVO.getCompanyId())
				.where(AuthRole.ID,id)
				.get();
		
		if(authRole == null){
			throw new CommonException("无此角色");
		}
		
		Map<String,Object> result = authRole.toMap();
		
		if(IntUtil.toInt(authRole.getIsAdmin()) == AuthConstant.Common.NO){
			result.put("menuList", authRoleMenuRepository.where(AuthRoleMenu.ROLE_ID,authRole.getId()).list());
		}
		
		return result;
	}

	@Transactional(rollbackFor = Exception.class)
	public Object delete(String ids, UserInfoInnerVO userInfoInnerVO) throws CommonException {
		if (StringUtil.isEmpty(ids)) {
			throw new CommonException("请传入需要删除的id");
		}
		if (authRoleRepository
				.where(AuthRole.COMPANY_ID, userInfoInnerVO.getCompanyId())
				.where(AuthRole.ID, ids, ModelOperator.IN)
				.where(AuthRole.IS_EDIT, AuthConstant.Common.YES)
				.isNotExists()) {
			throw new CommonException(ApiResultCode.RECORD_NOT_FOUND);
		}

		long count = authAppUserInstanceRepository
				.include("count(distinct user_id)")
				.where(AuthAppUserInstance.COMPANY_ID, userInfoInnerVO.getCompanyId())
				.where(AuthAppUserInstance.ROLE_ID, ids, ModelOperator.IN)
				.where(AuthAppUserInstance.DEL_FLAG, AuthConstant.Common.NO)
				.getLong();
		if (count > 0) {
			throw new CommonException(String.format("查询到 %d 个用户绑定了该角色，无法删除", count));
		}
		authRoleRepository
				.where(AuthRole.COMPANY_ID, userInfoInnerVO.getCompanyId())
				.where(AuthRole.ID, ids, ModelOperator.IN)
				.where(AuthRole.IS_EDIT, AuthConstant.Common.YES)
				.update(AuthRole.DEL_FLAG, AuthConstant.Common.YES);
		return null;
	}
}
