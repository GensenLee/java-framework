package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author GENSEN
 * @date 2020/11/3 18:32
 * @description：观看日志类型
 */
@Getter
public enum ViewLogType {
    PLAYBACK("vod", "回放"),
    LIVE("live", "直播");

    ViewLogType(String value, String name) {
        this.code = value;
        this.name = name;
    }
    private String code;
    private String name;
}
