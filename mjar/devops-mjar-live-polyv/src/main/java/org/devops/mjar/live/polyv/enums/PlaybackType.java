package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

@Getter
public enum PlaybackType {
    SINGLE("single", "单个回放"),
    LIST("list", "列表回放");

    PlaybackType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;
}