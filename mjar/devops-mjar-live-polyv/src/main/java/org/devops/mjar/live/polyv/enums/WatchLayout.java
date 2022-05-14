package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author GENSEN
 * @date 2020/11/2 18:53
 * @description：三分屏频道的观看布局
 */
@Getter
public enum  WatchLayout {
    PPT("ppt ", "文档为主"),
    VIDEO("video   ", "视频为主");

    WatchLayout(String value, String name) {
        this.value = value;
        this.name = name;
    }
    private String value;
    private String name;
}
