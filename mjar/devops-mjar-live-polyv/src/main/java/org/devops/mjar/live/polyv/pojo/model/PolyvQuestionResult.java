package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.polyv.enums.QuestionaireType;
import lombok.Data;
import lombok.Getter;

import java.util.List;


@Data
public class PolyvQuestionResult extends BaseBean {

    /**
     * 题目id
     */
    private String questionId;

    /**
     * 题目的答案
     */
    private String answer;

    /**
     * 题目
     */
    private String questionTitle;

    /**
     * 第几次发送题目，用于区分相同题目重复发送的情况
     */
    private Integer times;


    /**
     * 答题人数
     */
    private Integer total;

    /**
     * 回答正确的数量
     */
    private Integer correctCount;

    /**
     * 直播场次互动操作状态
     */
    private String interactStatus;

    /**
     * 服务器时间
     */
    private Long timestamp;

    /**
     * 答对用户列表
     */
    private List<String> rightUser;

    /**
     * 答错用户列表
     */
    private List<String> faultUser;

    /**
     * 题目类型
     */
    private String type;

    public void setType(QuestionaireType type) {
        this.type = type.getValue();
    }

    /**
     * 答题类型
     */
    private Integer itemType;

    public void setItemType(ItemType itemType) {
        this.itemType = itemType.getCode();
    }

    /**
     * 题目选项信息列表
     */
    private List<PolyvOptionsBean> options;

    /**
     * 答题的用户列表
     */
    private List<PolyvRecordsBean> records;

    @Getter
    enum ItemType{

        QUESTION(1,"问答"),
        ANSWER(0,"答题卡");

        ItemType(Integer code , String value){
            this.code = code;
            this.value = value;
        }
        private Integer code; private String value;


    }
}
