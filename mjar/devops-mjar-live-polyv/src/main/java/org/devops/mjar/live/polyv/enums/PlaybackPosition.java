package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author fangzy
 * @description：回放列表位置类型
 */
@Getter
public enum PlaybackPosition {
    Y("Y", "回放列表中置顶"),
    N("N", "回放列表中置底");

    PlaybackPosition(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;
}
