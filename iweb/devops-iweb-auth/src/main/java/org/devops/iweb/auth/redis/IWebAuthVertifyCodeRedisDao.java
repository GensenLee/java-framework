package org.devops.iweb.auth.redis;

import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.modules.redis.AspectRedisDao;
import org.devops.core.utils.modules.redis.annotation.Redis;
import org.devops.core.utils.modules.redis.client.RedisClient;
import org.devops.core.utils.util.StringUtil;
import org.devops.iweb.auth.constant.AuthConstant;
import org.springframework.stereotype.Component;

@Component
public class IWebAuthVertifyCodeRedisDao extends AspectRedisDao{

	private String TOKEN_KEY = "DEVOPS.IWEB_AUTH.VERTIFY_CODE:%s";

	private int expireTime = 120;


	/**
	 * 通过token获取appid
	 * @param sessionKey
     * @return
	 */
	@Redis
	public String get(String sessionKey){
		RedisClient jedis = getJedis();
		String k = String.format(TOKEN_KEY, sessionKey);
		String value = jedis.get(k);
		if(StringUtil.isNotEmpty(value)){
			//重新设置过期时间
			jedis.setex(k, expireTime, value);
			return value;
		}else {
			throw new CommonRuntimeException(AuthConstant.Code.TOKEN_NOT_VALID,"验证码失效");
		}
		
	}

	/**
	 * 设置
     * @param sessionKey
     * @param code
     */
	@Redis
	public void set(String sessionKey,String code){
		RedisClient jedis = getJedis();
		String k = String.format(TOKEN_KEY, sessionKey);
		jedis.setex(k, expireTime, code);
	}
	
	@Redis
	public void delete(String sessionKey) {
		RedisClient jedis = getJedis();
		String k = String.format(TOKEN_KEY, sessionKey);
		jedis.del(k);
	}
}
