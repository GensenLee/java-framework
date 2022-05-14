package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/**
 * @author fangzy
 * @description：文件类型
 */
@Getter
public enum DocumentType {

    OLD( "old","旧版" ),
    NEW("new","新版");

    DocumentType(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;
}
