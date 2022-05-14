package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author GENSEN
 * @date 2021/7/12 14:25
 * @description：观看限制类型
 */
@Getter
public enum  WatchAuthType {
    PAY("pay","付费观看"),
    CODE("code","验证码观看"),
    PHONE("phone","白名单观看"),
    INFO("info","登记观看"),
    CUSTOM("custom","自定义授权观看"),
    EXTERNAL("external","外部授权观看"),
    DIRECT("direct","直接授权观看");

    private String code;
    private String name;

    WatchAuthType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
