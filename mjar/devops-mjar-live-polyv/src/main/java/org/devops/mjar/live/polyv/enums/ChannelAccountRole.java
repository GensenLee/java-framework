package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author GENSEN
 * @date 2021/7/13 14:32
 * @description：子账号角色
 */
@Getter
public enum ChannelAccountRole {
    ASSISTANT("Assistant", "助教"),
    GUEST("Guest", "嘉宾");

    ChannelAccountRole(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;
}
