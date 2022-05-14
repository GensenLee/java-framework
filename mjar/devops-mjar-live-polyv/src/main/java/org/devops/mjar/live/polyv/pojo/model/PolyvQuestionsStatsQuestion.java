package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "问题统计结果")
@Data
public class PolyvQuestionsStatsQuestion extends BaseBean {
    @ApiModelProperty("问题")
    private String questionName;
    @ApiModelProperty("题目ID")
    private String questionId;
    @ApiModelProperty("题目分数")
    private Integer score;
    @ApiModelProperty("题目的总得分")
    private Integer totalScore;

    private Integer total;
    @ApiModelProperty("题目的答对人数")
    private Integer correctCount;

    @ApiModelProperty("选择项d的答题人数")
    private Integer d;
    @ApiModelProperty("选择项e的答题人数")
    private Integer e;
    @ApiModelProperty("选择项f的答题人数")
    private Integer f;
    @ApiModelProperty("选择项h的答题人数")
    private Integer h;
    @ApiModelProperty("选择项i的答题人数")
    private Integer i;
    @ApiModelProperty("选择项j的答题人数")
    private Integer j;
    @ApiModelProperty("选择项c的答题人数")
    private Integer c;
    @ApiModelProperty("选择项a的答题人数")
    private Integer a;
    @ApiModelProperty("选择项d的答题人数")
    private Integer b;
    @ApiModelProperty("选择项g的答题人数")
    private Integer g;
}