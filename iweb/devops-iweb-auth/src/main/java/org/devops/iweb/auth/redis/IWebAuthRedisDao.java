package org.devops.iweb.auth.redis;

import org.devops.core.utils.modules.redis.AspectRedisDao;
import org.devops.core.utils.modules.redis.annotation.Redis;
import org.devops.core.utils.modules.redis.client.RedisClient;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.StringUtil;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.springframework.stereotype.Component;

@Component
public class IWebAuthRedisDao extends AspectRedisDao {

	private String KEY_TEMPLATE = "DEVOPS.IWEB_AUTH.SESSION_KEY:%s";
	
	private int expireTime = 60*60*24;
	
	@Redis
	public UserInfoInnerVO get(String sessionKey){
		RedisClient jedis = getJedis();
		String key = String.format(KEY_TEMPLATE, sessionKey);
		String value = jedis.get(key);
		if(StringUtil.isNotEmpty(value)){
			//重新设置过期时间
			jedis.setex(key, expireTime, value);
		}
		return FastJsonUtils.getBean(value, UserInfoInnerVO.class);
	}
	
	
	@Redis
	public void set(UserInfoInnerVO userInfoInnerVO){
		RedisClient jedis = getJedis();
		String key = String.format(KEY_TEMPLATE, userInfoInnerVO.getSessionKey());
		jedis.setex(key, expireTime, userInfoInnerVO.toJsonString());
	}
	
	@Redis
	public void del(String sessionKey) {
		RedisClient jedis = getJedis();
		String key = String.format(KEY_TEMPLATE, sessionKey);
		jedis.del(key);
	}
}
