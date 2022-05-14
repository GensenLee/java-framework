package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author fangzy
 * @description：卡片推送样式
 */
@Getter
public enum CardImageType {

    GIFTBOX("giftbox","礼物领取样式"),
    REDPACK("redpack","红包样式");

    CardImageType(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;
}
