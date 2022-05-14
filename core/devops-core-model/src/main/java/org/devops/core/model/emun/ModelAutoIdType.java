package org.devops.core.model.emun;

import org.devops.core.model.annotation.Table;
import org.devops.core.model.autoid.AutoIdGenerator;
import org.devops.core.model.autoid.SnowflakeAutoIdGenerator;
import org.devops.core.model.autoid.UlidAutoIdGenerator;
import org.devops.core.model.autoid.UuidAutoIdGenerator;

/**
 * @author GENSEN
 * @date 2021/9/15 16:40
 * @description：id生成方式
 */
public enum ModelAutoIdType {

    /**
     * 空，不自动生成键
     */
    None{
        @Override
        public AutoIdGenerator<?> createInstance(String key) {
            return null;
        }
    },

    /**
     * 雪花id
     * 仅支持number类型字段
     */
    Snowflake{
        @Override
        public AutoIdGenerator<?> createInstance(String key) {
            return new SnowflakeAutoIdGenerator(key, 10);
        }
    },
    /**
     * 雪花id的16位形式
     */
    SnowflakeTo16{
        @Override
        public AutoIdGenerator<?> createInstance(String key) {
            return new SnowflakeAutoIdGenerator(key, 16);
        }
    },
    /**
     * 雪花id的32位形式
     */
    SnowflakeTo32{
        @Override
        public AutoIdGenerator<?> createInstance(String key) {
            return new SnowflakeAutoIdGenerator(key, 32);
        }
    },
    /**
     * 雪花id的36位形式
     */
    SnowflakeTo36{
        @Override
        public AutoIdGenerator<?> createInstance(String key) {
            return new SnowflakeAutoIdGenerator(key, 36);
        }
    },
    /**
     * https://github.com/ulid/spec
     * 仅支持string类型字段
     */
    ULID{
        @Override
        public AutoIdGenerator<?> createInstance(String key) {
            return new UlidAutoIdGenerator();
        }
    },
    /**
     * 仅支持string类型字段
     */
    UUID{
        @Override
        public AutoIdGenerator<?> createInstance(String key) {
            return new UuidAutoIdGenerator();
        }
    };

    public abstract AutoIdGenerator<?> createInstance(String key);



}
