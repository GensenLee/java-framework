package org.devops.iweb.api.core.oauth2;

import org.devops.iweb.api.model.ApiApplication;
import org.devops.iweb.api.repository.ApiApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IWebApiOAuth2ApplicationCore {

	@Autowired
	private ApiApplicationRepository apiApplicationRepository;
	
	public ApiApplication getByAppId(String appId) {
		return apiApplicationRepository.getByMemory(appId);
	}
}
