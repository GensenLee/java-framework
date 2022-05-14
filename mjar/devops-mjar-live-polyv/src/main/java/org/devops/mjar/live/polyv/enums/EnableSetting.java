package org.devops.mjar.live.polyv.enums;

import org.devops.core.utils.util.IntUtil;
import lombok.Getter;

/**
 * @author GENSEN
 * @date 2020/10/29 18:46
 * @description：开关设置
 */
@Getter
public enum EnableSetting {
    Y(1, "Y", "打开"),
    N(0, "N", "关闭");

    EnableSetting(int code, String value, String name) {
        this.code = code;
        this.value = value;
        this.name = name;
    }

    private int code;
    private String value;
    private String name;

    public static EnableSetting getInstance(Integer code){
        if (IntUtil.toInt(code) == 1) {
            return Y;
        }
        return N;
    }
}
