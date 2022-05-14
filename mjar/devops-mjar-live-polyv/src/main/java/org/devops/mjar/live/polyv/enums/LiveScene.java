package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author GENSEN
 * @date 2020/11/2 18:49
 * @description：直播场景
 */
@Getter
public enum LiveScene {
    ALONE("alone", "活动拍摄"),
    PPT ("ppt", "三分屏"),
    TOPCLASS ("topclass", "大班课"),
    SEMINAR("seminar", "研讨会");


    LiveScene(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;

    public static LiveScene get(String code){
        for (LiveScene value : values()) {
            if (value.value.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
