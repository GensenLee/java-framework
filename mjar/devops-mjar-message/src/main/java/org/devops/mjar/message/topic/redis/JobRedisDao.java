package org.devops.mjar.message.topic.redis;

import org.devops.core.utils.modules.redis.AspectRedisDao;
import org.devops.core.utils.modules.redis.annotation.Redis;
import org.devops.core.utils.modules.redis.client.RedisClient;
import org.devops.mjar.message.topic.constant.MjarMessageTopicConstant;
import org.springframework.stereotype.Component;

import redis.clients.jedis.params.SetParams;

@Component
public class JobRedisDao extends AspectRedisDao {

	private final String KEY = "DEVOPS.MJAR_MESSAGE.TOPIC.SWITCH.%s";
	
	private final String LOCK_KEY = "DEVOPS.MJAR_MESSAGE.TOPIC.LOCK.%s";
	
	@Redis
	public boolean isRun() {
		String key = String.format(KEY,MjarMessageTopicConstant.key);
		RedisClient jedis = getJedis();
		String value = jedis.get(key);
		return !"false".equalsIgnoreCase(value);
	}
	
	@Redis
	public void toggle() {
		String key = String.format(KEY,MjarMessageTopicConstant.key);
		RedisClient jedis = getJedis();
		String value = "true";
		if(isRun()){
			value = "false";
		}
		jedis.set(key,value);
	}
	
	@Redis
	public boolean tryLock(String topic) {
		
		String key = String.format(LOCK_KEY,topic);
		RedisClient jedis = getJedis();
		SetParams params = new SetParams();
		params.ex(60);
		params.nx();
		
		String result = jedis.set(key, key, params);
		if(result != null && (result.equalsIgnoreCase("1") || result.equalsIgnoreCase("ok"))) {
			return true;
		}
		return false;
	}
	
	@Redis
	public void releaseLock(String topic) {
		String key = String.format(LOCK_KEY,topic);
		RedisClient jedis = getJedis();
		jedis.del(key);
	}
}
