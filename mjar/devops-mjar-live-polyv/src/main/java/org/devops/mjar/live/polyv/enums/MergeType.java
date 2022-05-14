package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author fangzy
 * @description：合并后文件类型
 */
@Getter
public enum MergeType {
    Y("Y", "合并为MP4文件"),
    N("N", "合并为m3u8文件");

    MergeType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;
}
