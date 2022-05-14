package org.devops.web.test.redis;

import org.devops.core.utils.modules.redis.AspectRedisDao;
import org.devops.core.utils.modules.redis.annotation.Redis;
import org.devops.core.utils.modules.redis.client.RedisClient;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class TestReids extends AspectRedisDao {

	@Redis
	public void set(String k,String v){
		RedisClient jedis = getJedis();
		jedis.set(k, v);
	}
	
	@Redis
	public String get(){
		RedisClient jedis = getJedis();
		return jedis.get("test");
	}

	@Override
	public void afterRollback(final MultiValueMap<String, String> keys) {
		log.info("#### afterRollback {}", keys.values());
	}
}
