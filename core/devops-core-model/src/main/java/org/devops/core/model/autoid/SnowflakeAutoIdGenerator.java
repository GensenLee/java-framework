package org.devops.core.model.autoid;

import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.util.IPUtil;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.util.identifier.SnowflakeIdGenerator;

import java.math.BigInteger;

/**
 * @author GENSEN
 * @date 2021/9/15 16:21
 * @description：雪花id
 */
public class SnowflakeAutoIdGenerator implements AutoIdGenerator<String> {

    /**
     * 将生成值装成radix位的字符串
     */
    private final int radix;

    /**
     * 机器 ID 部分(影响雪花ID)
     */
    private final Long workerId;

    /**
     * 数据标识 ID 部分(影响雪花ID)(workerId 和 datacenterId 一起配置才能重新初始化 Sequence)
     * <p>
     * 每张表对应一个datacenterId
     */
    private final Long datacenterId;

    private final SnowflakeIdGenerator snowflakeIdGenerator;

    public SnowflakeAutoIdGenerator(String key, int radix) {
        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX){
            throw new CommonRuntimeException("radix 超出限制");
        }
        this.radix = radix;
        String localIP = IPUtil.getLocalIP();
        this.workerId = IPUtil.ipv4ToLong(localIP) % 31;
        this.datacenterId = getNameId(key) % 31;
        this.snowflakeIdGenerator = new SnowflakeIdGenerator(workerId, datacenterId);
    }

    @Override
    public String nextId() {
        long nextId = snowflakeIdGenerator.nextId();
        return Long.toUnsignedString(nextId, this.radix);
    }

    /**
     * 使用ascll码生成数字
     *
     * @param name
     * @return
     */
    private static long getNameId(String name) {
        int maxLength = String.valueOf(Long.MAX_VALUE).length();
        String tmp = "";
        for (char c : name.toCharArray()) {
            tmp += (int) c;
            if (tmp.length() >= maxLength) {
                tmp = tmp.substring(tmp.length() - maxLength, maxLength);
            }
        }
        if (tmp.compareTo(String.valueOf(Long.MAX_VALUE)) <= 0) {
            return LongUtil.toLong(tmp);
        }
        BigInteger bigInteger = new BigInteger(tmp);
        return bigInteger.mod(new BigInteger(String.valueOf(Long.MAX_VALUE))).longValue();
    }

    public static void main(String[] args) {
        SnowflakeAutoIdGenerator snowflakeAutoIdGenerator = new SnowflakeAutoIdGenerator("equity_buyback_template", 36);


        for (int i = 0; i < 20; i++) {
            System.out.println(snowflakeAutoIdGenerator.nextId());
        }

    }

}
