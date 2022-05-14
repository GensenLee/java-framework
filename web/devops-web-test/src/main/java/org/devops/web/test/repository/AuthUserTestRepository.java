package org.devops.web.test.repository;

import org.devops.core.model.annotation.ModelRedis;
import org.devops.core.model.core.AbstractModelRepository;
import org.devops.core.model.interfaces.IModelRedis;
import org.devops.core.utils.modules.apiEnhancer.annotation.ApiEnhancer;
import org.devops.core.utils.modules.apiEnhancer.enums.ApiEnhancerType;
import org.devops.web.test.model.AuthUserTest;
import org.springframework.stereotype.Repository;

@Repository
@ModelRedis(redisPriKey = "id")
public class AuthUserTestRepository extends AbstractModelRepository<AuthUserTest, Long>{

	public IModelRedis<AuthUserTest, Long> redis() {
		return super.redis();
	}
	
	@ApiEnhancer(type = ApiEnhancerType.SERIAL)
	public void test() {
		
	}
}
