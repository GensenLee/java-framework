package org.devops.iweb.operationlog.enums;

/**
 * @author GENSEN
 * @date 2021/9/26 18:02
 * @description：操作类型
 */
public enum OperationType {
    delete("删除"),
    update("修改"),
    create("创建"),
    query("查询"),
    other("其他");

    private String text;

    OperationType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
