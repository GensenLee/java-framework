package org.devops.mjar.weixin.service.ma;

import cn.binarywang.wx.miniapp.config.impl.WxMaRedisConnectionConfigImpl;
import org.devops.core.utils.spring.SpringContextUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author GENSEN
 * @date 2021/9/9 18:17
 * @description：自定义增强处理
 */
public class WxMaEnhancerConfigImpl extends WxMaRedisConnectionConfigImpl {

    public static final String redisKeyPrefix = "devops-wx";

    public WxMaEnhancerConfigImpl() {
        super(null);
        setRedisKeyPrefix(redisKeyPrefix);
    }

    @Override
    protected Jedis getJedis() {
        JedisPool jedisPool = (JedisPool) SpringContextUtil.getBean("jedisPool");
        return jedisPool.getResource();
    }
}
