package org.devops.core.model.autoid;

import org.devops.core.utils.constant.CommonConstant;

import java.util.UUID;

/**
 * @author GENSEN
 * @date 2021/9/16 11:09
 * @description：uuid生成器
 */
public class UuidAutoIdGenerator implements AutoIdGenerator<String> {
    @Override
    public String nextId() {
        return UUID.randomUUID().toString().replaceAll(CommonConstant.CROSS_MARK, CommonConstant.EMPTY_STRING);
    }
}
