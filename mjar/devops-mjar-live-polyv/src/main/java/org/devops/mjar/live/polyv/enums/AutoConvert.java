package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author fangzy
 * @description：自动转存到点播
 */
@Getter
public enum AutoConvert {
    Y("Y", "自动转存到对应点播分类下"),
    N("N", "不自动转存");

    AutoConvert(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;
}
