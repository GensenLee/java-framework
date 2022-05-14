package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author fangzy
 * @description：直播场景
 */
@Getter
public enum LiveNewScene {
    TOPCLASS("topclass", "大班课"),
    DOUBLE("double", "双师课"),
    TRAIN("train", "企业培训"),
    ALONE("alone", "活动营销"),
    SEMINAR("seminar", "研讨会");

    LiveNewScene(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;

    public static LiveNewScene get(String code){
        for (LiveNewScene value : values()) {
            if (value.value.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
