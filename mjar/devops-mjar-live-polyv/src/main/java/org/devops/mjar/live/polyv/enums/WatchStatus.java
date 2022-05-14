package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author GENSEN
 * @date 2020/10/29 16:51
 * @description：观看也状态
 * 观看页状态，live-直播中，playback-回放中，end-已结束，waiting-未开始
 */
@Getter
public enum WatchStatus {
    LIVE("live" ,"直播中"),
    PLAYBACK("playback" ,"回放中"),
    END("end" ,"已结束"),
    WAITING("waiting" ,"未开始");

    WatchStatus(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;
}
