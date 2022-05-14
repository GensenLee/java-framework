package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author fangzy
 * @description：卡片观看后弹出的观看时长单位
 */
@Getter
public enum CardConditionUnit {

    SECONDS("SECONDS","秒"),
    MINUTES("MINUTES","分钟");

    CardConditionUnit(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;
}
