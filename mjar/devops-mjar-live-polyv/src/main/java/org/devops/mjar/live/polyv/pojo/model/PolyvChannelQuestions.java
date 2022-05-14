package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.polyv.enums.QuestionaireType;
import lombok.Data;

import java.util.List;

@Data
public class PolyvChannelQuestions extends BaseBean {

    /**
     * 题目id，修改问卷时需要传
     */
    private String questionId;

    /**
     * 题目
     */
    private String name;

    /**
     * 题目类型   R：单选   C：多选   Q：问答
     */
    private String type;

    public void setType(QuestionaireType type) {
        if (type == null) {
            return;
        }
        this.type = type.getValue();
    }

    /**
     * 题目是否需要评分   Y：需要   N：不需要
     */
    private String scoreEnabled;

    public void setScoreEnabled(ScoreEnabled scoreEnabled) {
        if (scoreEnabled == null) {
            return;
        }
        this.scoreEnabled = scoreEnabled.value;
    }

    /**
     * 需要评分的选择题才有答案，填入对应选项序号，如：A或AB
     */
    private String answer;

    /**
     * 题目是否为必答   Y：必答   N：非必答
     */
    private String required;

    public void setRequired(Required required) {
        if (required == null) {
            return;
        }
        this.required = required.value;
    }


    /**
     * 题目为单选题或多选题为必填，选项数组下标0-9对应答案A-J
     */
    private List<String> options;




    public enum Required {
        Y("Y", "必答"),
        N("N", "非必答");

        Required(String value, String name) {
            this.value = value;
            this.name = name;
        }

        private String value;
        private String name;
    }

    public enum ScoreEnabled {
        Y("Y", "需要"),
        N("N", "不需要");

        ScoreEnabled(String value, String name) {
            this.value = value;
            this.name = name;
        }

        private String value;
        private String name;
    }
}
