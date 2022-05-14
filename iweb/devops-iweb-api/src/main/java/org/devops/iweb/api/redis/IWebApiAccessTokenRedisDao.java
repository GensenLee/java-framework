package org.devops.iweb.api.redis;

import org.devops.core.utils.modules.redis.AspectRedisDao;
import org.devops.core.utils.modules.redis.annotation.Redis;
import org.devops.core.utils.modules.redis.client.RedisClient;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.StringUtil;
import org.devops.iweb.api.model.ApiApplication;
import org.springframework.stereotype.Component;

@Component
public class IWebApiAccessTokenRedisDao extends AspectRedisDao{

	private static final String ACCESS_TOKEN_TEMPLATE = "DEVOPS.IWEB_API.ACCESS_TOKEN:%s";
	
	private static final String ACCESS_TOKEN_APP_ID_KEY_TEMPLATE = "DEVOPS.IWEB_API.ACCESS_TOKEN.APP_ID:%s";
	
	@Redis
	public void set(String accessToken, ApiApplication apiApplication) {
		
		RedisClient redis = getJedis();
		
		String key = String.format(ACCESS_TOKEN_TEMPLATE, accessToken);
		
		
		redis.setex(key, apiApplication.getExpiresIn() + 60 * 5 ,FastJsonUtils.toJsonString(apiApplication));
		
		// 设置5分钟后过期
		String appIdKey = String.format(ACCESS_TOKEN_APP_ID_KEY_TEMPLATE, apiApplication.getAppId());
		
		String oldAccessToken = redis.get(appIdKey);
		
		// 将旧的accessToken的过期时间设置为5分钟后
		if(StringUtil.isNotEmpty(oldAccessToken)) {
			key = String.format(ACCESS_TOKEN_TEMPLATE, oldAccessToken);
			redis.expire(key, 60 * 5);
		}
		
		
		// 将新的 accessToken 推入redis
		redis.setex(appIdKey, apiApplication.getExpiresIn() + 60 * 5, accessToken);
		
		
	}
	
	@Redis
	public ApiApplication get(String accessToken) {
		RedisClient redis = getJedis();
		
		String key = String.format(ACCESS_TOKEN_TEMPLATE, accessToken);
		
		String value = redis.get(key);
		
		return FastJsonUtils.getBean(value, ApiApplication.class);
	}
}
