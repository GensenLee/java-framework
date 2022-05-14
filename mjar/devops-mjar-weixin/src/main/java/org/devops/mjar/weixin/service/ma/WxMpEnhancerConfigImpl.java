package org.devops.mjar.weixin.service.ma;

import com.github.jedis.lock.JedisLock;
import me.chanjar.weixin.common.enums.TicketType;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl;
import org.devops.core.utils.spring.SpringContextUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author GENSEN
 * @date 2021/9/24 16:21
 * @description：自定义增强处理
 */
public class WxMpEnhancerConfigImpl extends WxMpRedisConfigImpl {
    public static final String redisKeyPrefix = "devops-wx";

    private static final String ACCESS_TOKEN_KEY_TPL = "%s:access_token:%s";
    private static final String LOCK_KEY_TPL = "%s:lock:%s:";

    public WxMpEnhancerConfigImpl(WxRedisOps redisOps) {
        super(redisOps, redisKeyPrefix);
    }

    @Override
    public void setAppId(String appId) {
        setAccessTokenKey(String.format(ACCESS_TOKEN_KEY_TPL, getKeyPrefix(), appId));
        setLockKey(String.format(LOCK_KEY_TPL, getKeyPrefix(), appId));
        super.appId = appId;
    }

    private String getRedisKey(String key) {
        StringBuilder redisKey = new StringBuilder(redisKeyPrefix).append(":");
        if (super.appId == null) {
            return redisKey.append(key).toString();
        } else {
            return redisKey.append(super.appId).append(":").append(key).toString();
        }
    }


    @Override
    public Lock getTicketLock(TicketType type) {
        switch (type) {
            case SDK:
                return getCardApiTicketLock();
            case JSAPI:
                return getJsapiTicketLock();
            case WX_CARD:
                return getCardApiTicketLock();
            default:
                return null;
        }
    }

    @Override
    public Lock getAccessTokenLock() {
        if (accessTokenLock == null) {
            synchronized (this) {
                if (accessTokenLock == null) {
                    accessTokenLock = new DistributedLock(getRedisKey("accessTokenLock"));
                }
            }
        }
        return accessTokenLock;
    }

    @Override
    public Lock getJsapiTicketLock() {
        if (jsapiTicketLock == null) {
            synchronized (this) {
                if (jsapiTicketLock == null) {
                    jsapiTicketLock = new DistributedLock(getRedisKey("jsapiTicketLock"));
                }
            }
        }
        return jsapiTicketLock;
    }

    @Override
    public Lock getSdkTicketLock() {
        if (sdkTicketLock == null) {
            synchronized (this) {
                if (sdkTicketLock == null) {
                    sdkTicketLock = new DistributedLock(getRedisKey("sdkTicketLock"));
                }
            }
        }
        return cardApiTicketLock;
    }

    @Override
    public Lock getCardApiTicketLock() {
        if (cardApiTicketLock == null) {
            synchronized (this) {
                if (cardApiTicketLock == null) {
                    cardApiTicketLock = new DistributedLock(getRedisKey("cardApiTicketLock"));
                }
            }
        }
        return cardApiTicketLock;
    }

    private Jedis getConfiguredJedis() {
        JedisPool jedisPool = (JedisPool) SpringContextUtil.getBean("jedisPool");
        return jedisPool.getResource();
    }

    /**
     * 基于redis的简单分布式锁.
     */
    private class DistributedLock implements Lock {

        private JedisLock lock;

        private DistributedLock(String key) {
            this.lock = new JedisLock(getRedisKey(key));
        }

        @Override
        public void lock() {
            try (Jedis jedis = getConfiguredJedis()) {
                if (!lock.acquire(jedis)) {
                    throw new WxRuntimeException("acquire timeouted");
                }
            } catch (InterruptedException e) {
                throw new WxRuntimeException("lock failed", e);
            }
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            try (Jedis jedis = getConfiguredJedis()) {
                if (!lock.acquire(jedis)) {
                    throw new WxRuntimeException("acquire timeouted");
                }
            }
        }

        @Override
        public boolean tryLock() {
            try (Jedis jedis = getConfiguredJedis()) {
                return lock.acquire(jedis);
            } catch (InterruptedException e) {
                throw new WxRuntimeException("lock failed", e);
            }
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            try (Jedis jedis = getConfiguredJedis()) {
                return lock.acquire(jedis);
            }
        }

        @Override
        public void unlock() {
            try (Jedis jedis = getConfiguredJedis()) {
                lock.release(jedis);
            }
        }

        @Override
        public Condition newCondition() {
            throw new WxRuntimeException("unsupported method");
        }

    }
}
