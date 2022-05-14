package org.devops.mjar.live.polyv.enums;

public enum ChatHistoryStatus {

    PASS( "pass","已审核" ),
    CENSOR("censor","审核中和删除");

    ChatHistoryStatus(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;


    }
