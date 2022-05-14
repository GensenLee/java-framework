package org.devops.iweb.api.core.oauth2;

import java.util.UUID;

import org.devops.core.utils.constant.ApiResultCode;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.util.StringUtil;
import org.devops.iweb.api.model.ApiApplication;
import org.devops.iweb.api.redis.IWebApiAccessTokenRedisDao;
import org.devops.iweb.api.repository.ApiApplicationRepository;
import org.devops.iweb.api.vo.oauth2.dto.IWebApiOAuth2AccessTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IWebApiOAuth2AccessTokenCore {

	@Autowired
	private ApiApplicationRepository apiApplicationRepository;
	
	@Autowired
	private IWebApiAccessTokenRedisDao iWebApiAccessTokenRedisDao;
	
	public IWebApiOAuth2AccessTokenDTO getAccessToken(String appId, String appSecret) {
		
		if(StringUtil.isEmpty(appId)) {
			throw new CommonRuntimeException(ApiResultCode.PARAMETER_MISSING, "appId不能为空");
		}
		if(StringUtil.isEmpty(appSecret)) {
			throw new CommonRuntimeException(ApiResultCode.PARAMETER_MISSING, "appSecret不能为空");
		}
		
		ApiApplication apiApplication = apiApplicationRepository.getByMemory(appId);
		
		if(apiApplication == null || !apiApplication.getAppSecret().equalsIgnoreCase(appSecret)) {
			throw new CommonRuntimeException(40001, "AppId或AppSecret错误");
		}
		
		String accessToken = UUID.randomUUID().toString().replaceAll("-", "");
		
		iWebApiAccessTokenRedisDao.set(accessToken, apiApplication);
		
		IWebApiOAuth2AccessTokenDTO iWebApiOAuth2AccessTokenDTO = new IWebApiOAuth2AccessTokenDTO();
		
		iWebApiOAuth2AccessTokenDTO.setAccessToken(accessToken);
		iWebApiOAuth2AccessTokenDTO.setExpiresIn(apiApplication.getExpiresIn());
		
		return iWebApiOAuth2AccessTokenDTO;
	}
	
	
	/**
	 * 校验token，token失效抛出异常
	 * @param accessToken
	 * @return
	 */
	public ApiApplication verifyAccessToken(String accessToken) {
		if(StringUtil.isEmpty(accessToken)) {
			throw new CommonRuntimeException(40001, "accessToken失效");
		}
		ApiApplication apiApplication = iWebApiAccessTokenRedisDao.get(accessToken);
		if(apiApplication == null) {
			throw new CommonRuntimeException(40001, "accessToken失效");
		}
		return apiApplication;
	}
}
