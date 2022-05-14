package org.devops.core.utils.modules.redis.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.modules.redis.annotation.Redis;
import org.devops.core.utils.modules.redis.client.RedisClient;
import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.core.utils.modules.redis.configuration.RedisConfiguration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

@Slf4j
@Aspect
public class RedisAspect {

    protected boolean handleJedisException(JedisException jedisException) {
        if (jedisException instanceof JedisConnectionException) {
            log.error("[RedisAspect.handleJedisException] Redis connection  lost.", jedisException);
        } else if (jedisException instanceof JedisDataException) {
            if ((jedisException.getMessage() != null) && (jedisException.getMessage().contains("READONLY"))) {
                log.error("[RedisAspect.handleJedisException] Redis connection are read-only slave.", jedisException);
            } else {
                log.error("[RedisAspect.handleJedisException]", jedisException);
                // dataException, isBroken=false
                return false;
            }
        } else {
            log.error("[RedisAspect.handleJedisException] Jedis exception happen.", jedisException);
        }
        return true;
    }

    protected void closeResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }

    }

    //定义环绕通知
    @Around("execution( * *(..)) && @annotation(redis)")
    public Object around(ProceedingJoinPoint pjp, Redis redis) throws Throwable {

        if (log.isDebugEnabled()) {
            log.debug("[RedisAspect.around] [{} {}() start] redis aop启动 ......", pjp.getTarget().getClass().getName(), pjp.getSignature().getName());
        }
        RedisClient redisClinet = null;
        PropertyDescriptor pd = new PropertyDescriptor("jedis", pjp.getTarget().getClass());
        Method setMethod = pd.getWriteMethod();
        if (setMethod == null) {
            return null;
        }

        JedisCluster jedisCluster = null;
        JedisPool jedisPool = null;
        Jedis jedis = null;
        try {
            if (SpringContextUtil.getContext().containsBean("jedisCluster")) {
                jedisCluster = (JedisCluster) SpringContextUtil.getBean("jedisCluster");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        if (jedisCluster != null) {
            redisClinet = new RedisClient(RedisConfiguration.getPrefix(), jedisCluster);
            setMethod.invoke(pjp.getTarget(), redisClinet);
        } else {
            if (!SpringContextUtil.getContext().containsBean("jedisPool")) {
                throw new CommonRuntimeException("请配置redis数据源");
            }
            jedisPool = (JedisPool) SpringContextUtil.getBean("jedisPool");
            try {
                jedis = jedisPool.getResource();
                redisClinet = new RedisClient(RedisConfiguration.getPrefix(), jedis);
                setMethod.invoke(pjp.getTarget(), redisClinet);
            } catch (JedisException e) {
                handleJedisException(e);
            } catch (Exception e) {
                log.error("[RedisAspect exception] 出错啦!!!", e);
            }
        }

        try {
            Object[] args = pjp.getArgs();
            Object result = pjp.proceed(args);
            if (log.isDebugEnabled()) {
                log.debug("[RedisAspect.around] [{} {}() end] redis 执行完毕......", pjp.getTarget().getClass().getName(), pjp.getSignature().getName());
            }
            return result;
        } catch (Exception e) {
            log.error("[RedisAspect exception] 出错啦!!!", e);
        } finally {
            closeResource(jedis);
            if (redisClinet != null) {
                redisClinet.close();
            }
            //设置一个空值
            setMethod.invoke(pjp.getTarget(), new Object[]{null});
        }

        return null;
    }
}
