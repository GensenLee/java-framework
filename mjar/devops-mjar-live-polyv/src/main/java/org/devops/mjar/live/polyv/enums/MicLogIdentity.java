package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author GENSEN
 * @date 2020/11/10 17:46
 * @description：连麦身份
 */
@Getter
public enum MicLogIdentity {
    TEACHER("teacher" ,"讲师"),
    GUEST("guest" ,"嘉宾"),
    STUDENT("student" ,"学员");

    MicLogIdentity(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;
}
