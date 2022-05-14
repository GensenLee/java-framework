package org.devops.core.model.autoid;

import org.devops.core.utils.util.identifier.ULID;

/**
 * @author GENSEN
 * @date 2021/9/16 11:09
 * @description：ulid生成器 https://github.com/ulid/spec
 */
public class UlidAutoIdGenerator implements AutoIdGenerator<String> {

    private final ULID ulid;

    public UlidAutoIdGenerator() {
        ulid = new ULID();
    }

    @Override
    public String nextId() {
        return ulid.nextULID();
    }
}
