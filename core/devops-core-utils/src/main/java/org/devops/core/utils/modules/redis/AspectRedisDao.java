package org.devops.core.utils.modules.redis;

import org.devops.core.utils.modules.redis.client.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;

public abstract class AspectRedisDao {

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	private ThreadLocal<RedisClient> jedisHolder = new ThreadLocal<RedisClient>();
	
	public RedisClient getJedis(){
		return jedisHolder.get();
	}
	
	public void setJedis(RedisClient jedis){
		jedisHolder.set(jedis);
	}

	/**
	 * 出现事务回滚时回调该方法
	 * 入参为事务线程中使用到的redis key
	 * @param keys 方法名 -> redis键值列表
	 */
	public void afterRollback(final MultiValueMap<String, String> keys){
	}
}
