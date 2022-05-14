package org.devops.core.utils.modules.redis.transaction;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.devops.core.utils.constant.CommonConstant;
import org.devops.core.utils.modules.redis.AspectRedisDao;
import org.devops.core.utils.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author GENSEN
 * @date 2021/12/1 14:06
 * @description：事务处理切面
 */
@Slf4j
@Aspect
public class RedisSimulatedTransactionAspect {

    @Autowired(required = false)
    private List<AspectRedisDao> redisDaoList;

    /**
     * 方法执行前构建事务控制相关信息
     * 用于方法抛出异常后做事务跟踪
     *
     * @param pjp
     * @param transactional
     * @return
     * @throws Throwable
     */
    @Around("execution( * *(..)) && @annotation(transactional)")
    public Object around(ProceedingJoinPoint pjp, Transactional transactional) throws Throwable {
        RedisSimulatedTransactionHandler.remove();
        Class<?>[] rollbackFor = transactional.rollbackFor();
        if (rollbackFor.length == 0) {
            rollbackFor = new Class[]{Exception.class};
        }
        Signature signature = pjp.getSignature();
        String method = signature.getDeclaringTypeName() + CommonConstant.POUND_MARK + signature.getName();
        log.debug("{} redis will handle for [{}]", method, rollbackFor);
        RedisSimulatedTransactionHandler.setRedisTransaction(new RedisSimulatedTransaction(method, rollbackFor));
        return pjp.proceed(pjp.getArgs());
    }

    /**
     * 抛异常情况时做事务跟踪
     *
     * @param transactional
     * @param throwable
     */
    @AfterThrowing(value = "execution( * *(..)) && @annotation(transactional)", throwing = "throwable")
    public void afterThrowing(Transactional transactional, Throwable throwable) {
        RedisSimulatedTransaction redisSimulatedTransaction = RedisSimulatedTransactionHandler.getRedisTransaction();
        if (redisSimulatedTransaction == null) {
            return;
        }
        log.debug("{} redis handle for Exception[{}][{}]", redisSimulatedTransaction.getMethod(), throwable.getClass(), throwable.getMessage());
        if (redisSimulatedTransaction.getMethodKeyMap().isEmpty()) {
            return;
        }
        if (ListUtil.isNull(redisDaoList)) {
            log.debug("AspectRedisDao instance not found");
            return;
        }
        boolean handle = false;
        for (Class<?> clazz : redisSimulatedTransaction.getRollbackFor()) {
            if (clazz.isAssignableFrom(throwable.getClass())) {
                handle = true;
                break;
            }
        }
        if (!handle) {
            return;
        }
        // method name -> redis key list
        final MultiValueMap<String, String> keyMap = redisSimulatedTransaction.getMethodKeyMap();
        Map<String, List<Map.Entry<String, List<String>>>> classKeysCollect = keyMap
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getKey().split(CommonConstant.POUND_MARK)[0]));
        for (AspectRedisDao redisDao : redisDaoList) {
            Class<?> clazz = redisDao.getClass();
            if (clazz.getSuperclass() != AspectRedisDao.class) {
                clazz = clazz.getSuperclass();
            }
            if (!classKeysCollect.containsKey(clazz.getName())) {
                continue;
            }
            List<Map.Entry<String, List<String>>> entryList = classKeysCollect.get(clazz.getName());
            final MultiValueMap<String, String> classScopeKeys = new LinkedMultiValueMap<>();
            for (Map.Entry<String, List<String>> entry : entryList) {
                classScopeKeys.addAll(entry.getKey(), entry.getValue());
            }
            redisDao.afterRollback(classScopeKeys);
        }

    }

    @After(value = "execution( * *(..)) && @annotation(transactional)")
    public void after(Transactional transactional) {
        RedisSimulatedTransactionHandler.remove();
    }

}
