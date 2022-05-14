package org.devops.core.utils.modules.apiEnhancer.redis;

import org.devops.core.utils.modules.redis.AspectRedisDao;
import org.devops.core.utils.modules.redis.annotation.Redis;
import org.devops.core.utils.modules.redis.client.RedisClient;
import org.springframework.stereotype.Component;

import redis.clients.jedis.params.SetParams;

@Component
public class ApiEnhancerSerialRedisDao extends AspectRedisDao{

	private String SERIAL_KEY = "DEVOPS.API_ENHANCER.SERIAL.KEY.%s";
	
	
	@Redis
	public boolean lock(String lockKey,long expire) {
		String key = String.format(SERIAL_KEY, lockKey);
		RedisClient jedis = getJedis();
		SetParams params = new SetParams();
		params.ex((int)(expire / 1000));
		params.nx();
		
		String result = jedis.set(key, key, params);
		if(result != null && (result.equalsIgnoreCase("1") || result.equalsIgnoreCase("ok"))) {
			return true;
		}
		return false;
	}
	
	@Redis
	public void unlock(String key){
		RedisClient jedis = getJedis();
		String k = String.format(SERIAL_KEY , key);
		jedis.del(k);
	}
}
