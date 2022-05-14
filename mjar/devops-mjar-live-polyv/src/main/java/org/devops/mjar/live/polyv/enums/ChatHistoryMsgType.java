package org.devops.mjar.live.polyv.enums;

public enum ChatHistoryMsgType {
    REDPAPER( "pass","已审核" ),
    GETREDPAPER("censor","审核中和删除"),
    CHATIG("chatImg","图片消息"),
    CUSTOM("custom","自定义消息"),
    REWARD("reward","打赏消息"),
    RCUSTOMERMSEEAGE("customerMessage","自定义消息（通过http接口发送的自定义消息）"),
    EMPTY( "","普通聊天消息");

    ChatHistoryMsgType(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;


}
