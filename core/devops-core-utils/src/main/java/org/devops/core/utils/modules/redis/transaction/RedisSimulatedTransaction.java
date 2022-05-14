package org.devops.core.utils.modules.redis.transaction;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author GENSEN
 * @date 2021/12/1 12:19
 * @description：redis事务
 */
@Getter
public class RedisSimulatedTransaction {

    public RedisSimulatedTransaction(String method, Class<?>[] rollbackFor) {
        this.method = method;
        this.rollbackFor = rollbackFor;
        this.methodKeyMap = new LinkedMultiValueMap<>();
    }

    /**
     * 当前控制范围redis key集合
     * 方法名 -> redis键值
     */
    private MultiValueMap<String, String> methodKeyMap;

    /**
     * 控制的方法
     */
    private String method;

    /**
     *
     */
    private Class<?>[] rollbackFor;

    /**
     * 捕捉的异常
     */
    @Setter
    private Throwable throwable;

    /**
     * @param key 键值
     * @param endpoint 调用方法
     */
    public void addKey(String key, String endpoint){
        this.methodKeyMap.add(endpoint, key);
    }


}

