package org.devops.mjar.live.core.processor;

import lombok.Getter;

/**
 * @author GENSEN
 * @date 2021/3/19 15:07
 * @description：处理器类型
 */
@Getter
public enum ProcessorType {

    Root("root.", Key.APP_ID, Key.APP_SECRET),
    App("app.", Key.APP_ID, Key.APP_SECRET, Key.USER_ID),
    Channel("channel.", Key.APP_ID, Key.APP_SECRET, Key.USER_ID, Key.CHANNEL_ID);

    ProcessorType(String prefix, String... keys) {
        this.prefix = COMMON_PREFIX + prefix;
        this.keys = keys;
    }

    private static final String COMMON_PREFIX = "polyv.client.default.";
    private String prefix;
    private String[] keys;

    static class Key {
        static final String APP_ID = "appId";
        static final String APP_SECRET = "appSecret";
        static final String USER_ID = "userId";
        static final String CHANNEL_ID = "channelId";
    }
}
