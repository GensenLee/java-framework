package org.devops.core.utils.modules.redis.transaction;

import org.devops.core.utils.util.StringUtil;

/**
 * @author GENSEN
 * @date 2021/12/1 12:17
 * @description：事务信息持有
 */
public class RedisSimulatedTransactionHandler {

    private static final ThreadLocal<RedisSimulatedTransaction> TRANSACTIONS = new ThreadLocal<>();

    public static void addKey(String key, String endpoint){
        RedisSimulatedTransaction redisSimulatedTransaction = TRANSACTIONS.get();
        if (redisSimulatedTransaction == null || StringUtil.isEmpty(key)) {
            return;
        }
        redisSimulatedTransaction.addKey(key, endpoint);
    }

    static void setRedisTransaction(RedisSimulatedTransaction redisSimulatedTransaction){
        TRANSACTIONS.set(redisSimulatedTransaction);
    }

    public static RedisSimulatedTransaction getRedisTransaction(){
        return TRANSACTIONS.get();
    }

    static void remove(){
        TRANSACTIONS.remove();
    }

}
