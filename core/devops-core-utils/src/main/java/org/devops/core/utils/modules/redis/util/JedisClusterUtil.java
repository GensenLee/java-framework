package org.devops.core.utils.modules.redis.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import redis.clients.jedis.*;
import redis.clients.jedis.util.JedisClusterCRC16;

import java.lang.reflect.Field;

@Slf4j
public class JedisClusterUtil {

	/**
	* 集裙中根據 key对应的slot 获取槽位 或 key 返回對應的某個Jedis 實例
	* @param key
	* @return Jedis
	*/
	
	public static int getSlot(String key) {
		return JedisClusterCRC16.getSlot(key);
	}
	
	public static Jedis getJedisByKey(JedisCluster jedisCluster,String key) {
		return getJedisBySlot(jedisCluster,getSlot(key));
	}
	 
	public static Jedis getJedisBySlot(JedisCluster jedisCluster,int slot) {
	 
		try {
			
			// org.springframework.util.ReflectionUtils 工具類  BinaryJedisCluster 下的  JedisClusterConnectionHandler
			 
			Field field = ReflectionUtils.findField(BinaryJedisCluster.class, null, JedisClusterConnectionHandler.class);
			 
			field.setAccessible(true);
			 
			JedisClusterConnectionHandler connectionHandler =  (JedisClusterConnectionHandler) field.get(jedisCluster);
			 
			Field jedisclusterinfocache = ReflectionUtils.findField(JedisClusterConnectionHandler.class, null, JedisClusterInfoCache.class);
			 
			jedisclusterinfocache.setAccessible(true);
			 
			JedisClusterInfoCache cache = (JedisClusterInfoCache) jedisclusterinfocache.get(connectionHandler);
			 
			JedisPool pool = cache.getSlotPool(slot);
			 
			Jedis jedis = pool.getResource();
			 
			return jedis;
		 
		} catch (Exception e) {
			log.error("[Exception 出错啦！]", e);
		}
		 
		return null;
		 
	}
}
