package org.devops.iweb.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.devops.core.model.emun.ModelOperator;
import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.util.MapUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.verify.VerifyUtil;
import org.devops.iweb.auth.constant.AuthConstant;
import org.devops.iweb.auth.model.AuthAppInstance;
import org.devops.iweb.auth.model.AuthResources;
import org.devops.iweb.auth.repository.AuthAppInstanceRepository;
import org.devops.iweb.auth.repository.AuthResourcesRepository;
import org.devops.iweb.auth.vo.inVO.AuthResourcesSaveInVO;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.devops.iweb.auth.vo.outVO.AuthResourcesOutVO;
import org.devops.iweb.auth.vo.searchVO.AuthResourcesSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthResourcesService {

	@Autowired
	private AuthResourcesRepository authResourcesRepository;
	
	@Autowired
	private AuthAppInstanceRepository authAppInstanceRepository;
	
	public Object list(AuthResourcesSearchVO svo) throws CommonException{
		
		VerifyUtil.verify(svo,"appId");
		
		List<AuthResourcesOutVO> liAuthResourcesOutVO = authResourcesRepository
				.exclude(AuthConstant.Common.EXCLUDE_FIELDS)
				.where(AuthResources.APP_ID,svo.getAppId())
				.list(AuthResourcesOutVO.class);
		
		Map<Long,AuthResourcesOutVO> mapAuthResourcesOutVO = MapUtil.toMap(liAuthResourcesOutVO, AuthResources.ID);
		List<AuthResourcesOutVO> result = new ArrayList<AuthResourcesOutVO>();
		for(AuthResourcesOutVO authResourcesOutVO : liAuthResourcesOutVO){
			if(LongUtil.isZero(authResourcesOutVO.getParentId())){
				result.add(authResourcesOutVO);
				continue;
			}
			AuthResourcesOutVO parentResources = mapAuthResourcesOutVO.get(authResourcesOutVO.getParentId());
			if(parentResources != null){
				parentResources.addChildren(authResourcesOutVO);
			}
		}
		return MapUtil.toMapListResultExcludeFields(result,AuthConstant.Common.EXCLUDE_FIELDS);
	}
	
	public Object listForApp(AuthResourcesSearchVO svo) throws CommonException{
		
		if(LongUtil.isNotZero(svo.getAppInstanceId())){
			AuthAppInstance authAppInstance = authAppInstanceRepository.get(svo.getAppInstanceId());
			if(authAppInstance != null){
				svo.setAppId(authAppInstance.getAppId());
			}
		}
		
		
		
		VerifyUtil.verify(svo,"appId");
		
		if(StringUtil.isNotEmpty(svo.getQuickSearch())){
			authResourcesRepository.where(AuthResources.NAME,svo.getQuickSearch(),ModelOperator.LIKE);
		}
		List<AuthResources> liAuthResources = authResourcesRepository
				.exclude(AuthConstant.Common.EXCLUDE_FIELDS)
				.where(AuthResources.APP_ID,svo.getAppId())
				.where(AuthResources.PARENT_ID,0,ModelOperator.NEQ)
				.list();
		
		return liAuthResources;
	}
	
	
	private void getResources(List<AuthResourcesSaveInVO> out,AuthResourcesSaveInVO authResourcesSaveInVO){
		if(LongUtil.toLong(authResourcesSaveInVO.getId()) != -1 ){
			out.add(authResourcesSaveInVO);
		}
		if(authResourcesSaveInVO.getChildren() == null || authResourcesSaveInVO.getChildren().isEmpty()){
			return;
		}
		for(AuthResourcesSaveInVO authResources : authResourcesSaveInVO.getChildren()){
			getResources(out,authResources);
		}
	}
	private void save(List<AuthResourcesSaveInVO> liAuthResourcesSaveInVO,Long parentId,String parentName,UserInfoInnerVO userInfoInnerVO){
		if(liAuthResourcesSaveInVO == null || liAuthResourcesSaveInVO.isEmpty()){
			return;
		}
		List<AuthResourcesSaveInVO> liUpdate = new ArrayList<AuthResourcesSaveInVO>();
		List<AuthResourcesSaveInVO> liInsert = new ArrayList<AuthResourcesSaveInVO>();
		
		for(AuthResourcesSaveInVO authResourcesSaveInVO : liAuthResourcesSaveInVO){
			authResourcesSaveInVO.setParentId(parentId);
			if(LongUtil.isNotZero(parentId) && StringUtil.isNotEmpty(parentName)
					&& !authResourcesSaveInVO.getName().contains(parentName + "-")){
				authResourcesSaveInVO.setName(parentName+"-"+authResourcesSaveInVO.getName());
			}
			if(LongUtil.isNotZero(authResourcesSaveInVO.getId())){
				BeanUtil.initModify(authResourcesSaveInVO, userInfoInnerVO.getUserName());
				liUpdate.add(authResourcesSaveInVO);
			}else{
				BeanUtil.initCreate(authResourcesSaveInVO, userInfoInnerVO.getUserName());
				liInsert.add(authResourcesSaveInVO);
			}
		}
		if(!liUpdate.isEmpty()){
			authResourcesRepository.updateByObject(liUpdate);
		}
		if(!liInsert.isEmpty()){
			authResourcesRepository.insertByObject(liInsert);
		}
		liInsert.addAll(liUpdate);
		liUpdate.clear();
		for(AuthResourcesSaveInVO authResourcesSaveInVO : liInsert){
			save(authResourcesSaveInVO.getChildren(),authResourcesSaveInVO.getId(),authResourcesSaveInVO.getName(),userInfoInnerVO);
		}
	}
	public Object save(AuthResourcesSaveInVO authResourcesSaveInVO,UserInfoInnerVO userInfoInnerVO) throws CommonException{
		if(authResourcesSaveInVO == null){
			throw new CommonException("请传入正确的参数");
		}
		
		List<AuthResourcesSaveInVO> liAuthResourcesSaveInVO = new ArrayList<AuthResourcesSaveInVO>();
		getResources(liAuthResourcesSaveInVO,authResourcesSaveInVO);
		List<Long> ids = new ArrayList<Long>();
		for(AuthResourcesSaveInVO authResources : liAuthResourcesSaveInVO){
			if(LongUtil.isNotZero(authResources.getId()) && LongUtil.toLong(authResources.getId()) != -1){
				ids.add(authResources.getId());
			}
		}
		if(ids.isEmpty()){
			authResourcesRepository.where(AuthResources.APP_ID,authResourcesSaveInVO.getAppId()).where(AuthResources.ID,0,ModelOperator.NEQ).delete();
		}else{
			authResourcesRepository.where(AuthResources.APP_ID,authResourcesSaveInVO.getAppId()).where(AuthResources.ID,ids,ModelOperator.NOT_IN).delete();
		}
		
		if(authResourcesSaveInVO.getChildren() == null || authResourcesSaveInVO.getChildren().isEmpty()){
			return null;
		}
		
		save(authResourcesSaveInVO.getChildren(),0L,authResourcesSaveInVO.getName(),userInfoInnerVO);
		
		return null;
	}
}
