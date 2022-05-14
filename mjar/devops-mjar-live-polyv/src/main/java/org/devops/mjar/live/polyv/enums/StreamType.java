package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author fangzy
 * @description：直播推流方式
 */
@Getter
public enum StreamType {
    CLIENT("client", "客户端推流"),
    DISK("disk", "硬盘推流"),
    AUDIO("audio", "音频直播"),
    PULL("pull", "拉流直播");

    StreamType(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;
}
