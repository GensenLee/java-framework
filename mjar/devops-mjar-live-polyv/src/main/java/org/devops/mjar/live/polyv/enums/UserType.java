package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

@Getter
public enum UserType {

    ASSISTANT("assistant", "助教"),
    GUEST("guest", "嘉宾"),
    SILCE("silce","云课堂学员"),
    TEACHER("teacher","讲师"),
    MANAGER("manager","管理员"),
    VIEWER("viewer","特邀观众"),
    MONITOR("monitor","场监"),
    ATTENDEE("attendee","研讨会参与者"),
    STUDENT("student","普通直播观众");

    UserType(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;

}
