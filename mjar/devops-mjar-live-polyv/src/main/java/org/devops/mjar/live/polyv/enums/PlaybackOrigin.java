package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

@Getter
public enum PlaybackOrigin {
    RECORD("record", "暂存"),
    VOD("vod", "点播列表"),
    PLAYBACK("playback", "回放列表");

    PlaybackOrigin(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;
}