package org.devops.mjar.live.polyv.enums;


import lombok.Getter;

@Getter
public enum QuestionaireType {
    R("R", "单选"),
    C("C", "多选"),
    Q("Q", "问答");

    QuestionaireType(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;
}