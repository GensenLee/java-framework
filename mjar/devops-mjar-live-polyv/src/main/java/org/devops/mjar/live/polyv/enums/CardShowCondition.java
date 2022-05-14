package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author fangzy
 * @description：卡片弹出方式
 */
@Getter
public enum CardShowCondition {

    PUSH("PUSH","推送后立即弹出"),
    WATCH("WATCH","观看后弹出");

    CardShowCondition(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;

}
