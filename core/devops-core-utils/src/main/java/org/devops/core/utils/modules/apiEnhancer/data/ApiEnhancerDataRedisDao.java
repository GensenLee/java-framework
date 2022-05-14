package org.devops.core.utils.modules.apiEnhancer.data;

import org.devops.core.utils.modules.redis.AspectRedisDao;
import org.devops.core.utils.modules.redis.annotation.Redis;
import org.devops.core.utils.modules.redis.client.RedisClient;
import org.springframework.stereotype.Component;

@Component
public class ApiEnhancerDataRedisDao extends AspectRedisDao implements IApiEnhancerData{

	private String CACHEABLE_KEY = "POLYV.LIVE.CACHEABLE.KEY.%s";
	
	@Redis
	@Override
	public String get(String key){
		RedisClient jedis = getJedis();
		
		String k = String.format(CACHEABLE_KEY , key);
		return jedis.get(k);
	}

	@Redis
	@Override
	public void set(String key, long expire, String value){
		RedisClient jedis = getJedis();
		String k = String.format(CACHEABLE_KEY , key);
		if(expire == -1) {
			jedis.set(k, value);
		} else {
			jedis.setex(k, (int)(expire / 1000), value);
		}
	}
}
