package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("问卷选项")
@Data
public class PolyvQuestionnaireQuestions extends BaseBean {

    @ApiModelProperty("问题id")
    private String questionId;

    @ApiModelProperty("频道id")
    private String channelId;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("题目")
    private String name;

    @ApiModelProperty("问题类型，取值：R 单选；C 多选；S 评星题；Q 问答")
    private String type;

    @ApiModelProperty("A")
    private String option1;
    @ApiModelProperty("B")
    private String option2;
    @ApiModelProperty("C")
    private String option3;
    @ApiModelProperty("D")
    private String option4;
    @ApiModelProperty("E")
    private String option5;
    @ApiModelProperty("F")
    private String option6;
    @ApiModelProperty("G")
    private String option7;
    @ApiModelProperty("H")
    private String option8;
    @ApiModelProperty("I")
    private String option9;
    @ApiModelProperty("J")
    private String option10;


    @ApiModelProperty("创建时间")
    private Long createdTime;

    @ApiModelProperty("最后修改时间")
    private Long lastModified;

    @ApiModelProperty("问题答案")
    private String answer;

    @ApiModelProperty("是否必填，取值Y、N")
    private String required;

    @ApiModelProperty("是否计分，取值Y、N")
    private String scoreEnabled;

    @ApiModelProperty("题目分值")
    private Integer score;

    private Integer itemType;

    private String note;
    private String templateId;
    private String status;
    private Integer times;
    private String tips1;
    private String tips2;
    private String tips3;
    private String tips4;
    private String tips5;
}