package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author fangzy
 * @description：视频列表类型
 */
@Getter
public enum VideoListType {
    PLAYBACK("playback","回放列表"),
    VOD("vod","点播列表");

    VideoListType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;
}
