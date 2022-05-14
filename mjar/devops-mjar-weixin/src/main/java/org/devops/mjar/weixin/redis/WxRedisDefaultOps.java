package org.devops.mjar.weixin.redis;

import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.common.util.locks.JedisDistributedLock;
import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.mjar.weixin.exception.WeixinConfigException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author GENSEN
 * @date 2021/9/9 17:41
 * @description：微信缓存操作对象
 */
public class WxRedisDefaultOps implements WxRedisOps {

    private WxRedisDao getWxRedisDao(){
        return SpringContextUtil.getBean(WxRedisDao.class);
    }

    @Override
    public String getValue(String key) {
        return getWxRedisDao().get(key);
    }

    @Override
    public void setValue(String key, String value, int expire, TimeUnit timeUnit) {
        getWxRedisDao().set(key, value, expire, timeUnit);
    }

    @Override
    public Long getExpire(String key) {
        return getWxRedisDao().getExpire(key);
    }

    @Override
    public void expire(String key, int expire, TimeUnit timeUnit) {
        getWxRedisDao().expire(key, expire, timeUnit);
    }

    @Override
    public Lock getLock(String key) {
        Object jedisPool = SpringContextUtil.getBean("jedisPool");
        return new JedisDistributedLock((JedisPool) jedisPool, key);
    }
}
