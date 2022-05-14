package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

@Getter
public enum InfoFieldsType {

    NAME("name", "姓名"),
    TEXT("text", "文本"),
    MOBILE("mobile", "手机号码"),
    NUMBER("number", "数字"),
    OPTION("option", "下拉选项");

    InfoFieldsType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;

    private String name;

}