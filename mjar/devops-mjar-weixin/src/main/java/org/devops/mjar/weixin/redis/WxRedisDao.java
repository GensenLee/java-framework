package org.devops.mjar.weixin.redis;

import org.devops.core.utils.modules.redis.AspectRedisDao;
import org.devops.core.utils.modules.redis.annotation.Redis;
import org.devops.core.utils.modules.redis.client.RedisClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author GENSEN
 * @date 2021/9/9 17:33
 * @description：redis操作对象
 */
@Component
public class WxRedisDao extends AspectRedisDao {

    /**
     * @param key
     * @param value
     * @param expire
     */
    @Redis
    public void set(String key, String value, int expire, TimeUnit timeUnit){
        RedisClient jedis = getJedis();
        expire = convert2Second(expire, timeUnit);
        jedis.setex(key, expire, value);
    }

    /**
     * @param key
     * @return
     */
    @Redis
    public String get(String key){
        RedisClient jedis = getJedis();
        return jedis.get(key);
    }

    /**
     * @param key
     * @param expire
     * @param timeUnit
     */
    @Redis
    public void expire(String key, int expire, TimeUnit timeUnit) {
        RedisClient jedis = getJedis();
        expire = convert2Second(expire, timeUnit);
        jedis.expire(key, expire);
    }

    /**
     * @param key
     * @return
     */
    @Redis
    public Long getExpire(String key){
        RedisClient jedis = getJedis();
        return jedis.ttl(key);
    }

    /**
     * @param expire
     * @param timeUnit
     * @return
     */
    public static int convert2Second(int expire, TimeUnit timeUnit){
        if (TimeUnit.MILLISECONDS == timeUnit) {
            expire = expire / 1000;
        }
        if (TimeUnit.MICROSECONDS == timeUnit) {
            expire = expire / 1000 / 1000;
        }
        if (TimeUnit.NANOSECONDS == timeUnit) {
            expire = expire / 1000 / 1000 / 1000;
        }
        return expire;
    }
}
