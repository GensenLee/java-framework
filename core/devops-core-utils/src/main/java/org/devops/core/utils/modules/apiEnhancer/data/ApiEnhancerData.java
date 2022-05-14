package org.devops.core.utils.modules.apiEnhancer.data;

import org.devops.core.utils.modules.apiEnhancer.enums.ApiEnhancerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiEnhancerData {

	@Autowired
	private ApiEnhancerDataRedisDao apiEnhancerDataRedisDao;
	
	public String get(ApiEnhancerType apiEnhancerType, String key) {
		if(apiEnhancerType.equals(ApiEnhancerType.MEMORY)) {
			return ApiEnhancerDataMemory.getInstance().get(key);
		} else {
			return apiEnhancerDataRedisDao.get(key);
		}
	}

	public void set(ApiEnhancerType apiEnhancerType, String key, long expire, String value) {
		if(expire == 0) {
			return;
		}
		if(apiEnhancerType.equals(ApiEnhancerType.MEMORY)) {
			ApiEnhancerDataMemory.getInstance().set(key, expire, value);
		} else {
			apiEnhancerDataRedisDao.set(key, expire, value);
		}
	}
}
