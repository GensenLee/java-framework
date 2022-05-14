package org.devops.iweb.auth.service;

import java.util.HashMap;
import java.util.Map;

import org.devops.core.model.emun.ModelOperator;
import org.devops.core.utils.util.StringUtil;
import org.devops.iweb.auth.constant.AuthConstant;
import org.devops.iweb.auth.model.AuthAppInstance;
import org.devops.iweb.auth.model.AuthAppUserInstance;
import org.devops.iweb.auth.model.AuthKeyValue;
import org.devops.iweb.auth.repository.AuthAppInstanceRepository;
import org.devops.iweb.auth.repository.AuthAppUserInstanceRepository;
import org.devops.iweb.auth.repository.AuthKeyValueRepository;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthCommonService {

	@Autowired
	private AuthKeyValueRepository authKeyValueRepository;
	
	@Autowired
	private AuthAppUserInstanceRepository authAppUserInstanceRepository;
	
	@Autowired
	private AuthAppInstanceRepository authAppInstanceRepository;
	
	public Object getForCommon(){
		Map<String,Object> result = new HashMap<String,Object>();
		String name = authKeyValueRepository
				.include(AuthKeyValue.VALUE)
				.where(AuthKeyValue.KEY,AuthConstant.KeyValueKey.SYSTEM_NAME)
				.getString();
		
		String copyright = authKeyValueRepository
				.include(AuthKeyValue.VALUE)
				.where(AuthKeyValue.KEY,AuthConstant.KeyValueKey.COPYRIGHT)
				.getString();
		
		result.put(StringUtil.toLHCase(AuthConstant.KeyValueKey.SYSTEM_NAME), name);
		result.put(AuthConstant.KeyValueKey.COPYRIGHT, copyright);
		return result;
	}
	
	public Object getForUser(){
		Map<String,Object> result = new HashMap<String,Object>();
		String name = authKeyValueRepository
				.include(AuthKeyValue.VALUE)
				.where(AuthKeyValue.KEY,AuthConstant.KeyValueKey.SYSTEM_NAME)
				.getString();
		result.put(StringUtil.toLHCase(AuthConstant.KeyValueKey.SYSTEM_NAME), name);
		return result;
	}
	
	public Object getForInstance(UserInfoInnerVO userInfoInnerVO){
		Map<String,Object> result = new HashMap<String,Object>();
		String name = authKeyValueRepository
				.include(AuthKeyValue.VALUE)
				.where(AuthKeyValue.KEY,AuthConstant.KeyValueKey.SYSTEM_NAME)
				.getString();
		result.put(StringUtil.toLHCase(AuthConstant.KeyValueKey.SYSTEM_NAME), name);
		AuthAppInstance authAppInstance = authAppInstanceRepository.include(AuthAppInstance.NAME,AuthAppInstance.CODE,AuthAppInstance.PARENT_APP_INSTANCE_ID)
				.where(AuthAppInstance.ID,
						authAppUserInstanceRepository.createSelect().fields(AuthAppUserInstance.APP_INSTANCE_ID).where(AuthAppUserInstance.ID,userInfoInnerVO.getUserInstanceId()).sql(ModelOperator.EQ)
						,ModelOperator.PLAIN).get();
		AuthAppInstance parentAppInstance = authAppInstanceRepository.get(authAppInstance.getParentAppInstanceId());
		result.put("instanceName", authAppInstance.getName());
		result.put("appCode",authAppInstance.getCode());
		if(parentAppInstance != null) {
			result.put("parentInstanceName", parentAppInstance.getName());
		}
		return result;
	}
}
