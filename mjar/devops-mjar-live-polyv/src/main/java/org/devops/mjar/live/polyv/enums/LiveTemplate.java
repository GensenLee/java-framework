package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author fangzy
 * @description：直播模板
 */
@Getter
public enum LiveTemplate {
    PPT("ppt", "文档+视频"),
    ALONE("alone", "纯视频(专业)"),
    TOPCLASS("topclass", "纯视频(极速)"),
    PORTRAIT_PPT("portrait_ppt", "文档+视频(竖屏)"),
    PORTRAIT_ALONE("portrait_alone", "纯视频(竖屏)"),
    SEMINAR("seminar", "研讨会");

    LiveTemplate(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;

    public static LiveTemplate get(String code){
        for (LiveTemplate value : values()) {
            if (value.value.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
