package org.devops.iweb.api.repository;

import org.devops.core.model.core.AbstractModelRepository;
import org.devops.core.utils.modules.apiEnhancer.annotation.ApiEnhancer;
import org.devops.core.utils.modules.apiEnhancer.enums.ApiEnhancerType;
import org.devops.core.utils.constant.CommonConstant;
import org.devops.iweb.api.model.ApiApplication;
import org.springframework.stereotype.Repository;

@Repository
public class ApiApplicationRepository extends AbstractModelRepository<ApiApplication, Long>{

	// 过期时间2分钟
	@ApiEnhancer(type = ApiEnhancerType.MEMORY,expire = 2 * 60 * 1000)
	public ApiApplication getByMemory(String appId) {
		return this
				.where(ApiApplication.APP_ID, appId)
				.where(ApiApplication.IS_ACTIVE, CommonConstant.ACTIVE)
				.get();
	}
}
