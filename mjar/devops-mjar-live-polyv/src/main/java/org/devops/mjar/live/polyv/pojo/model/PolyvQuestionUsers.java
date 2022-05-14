package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "观看端提交答题的信息")
@Data
public class PolyvQuestionUsers extends BaseBean {

    @ApiModelProperty("自定义参数，用于标志唯一观众")
    private String param4;
    @ApiModelProperty("自定义参数，用于标志唯一观众")
    private String param5;

    @ApiModelProperty("提交问卷的日期，13位的时间戳")
    private Long submitTime;

    @ApiModelProperty("提交问卷的用户的总得分")
    private Integer totalScore;

    @ApiModelProperty("用户每道题目的答题情况 ")
    private List<answers> answers;

    @ApiModelProperty("提交问卷的用户ID")
    private String viewerId;
    @ApiModelProperty("提交问卷的用户昵称")
    private String nickname;


    @Data
    public static class answers extends BaseBean{
        @ApiModelProperty("用户答题的得分")
        private Integer score;
        @ApiModelProperty("题目ID")
        private String questionId;
        @ApiModelProperty("题目名称")
        private String questionName;
        @ApiModelProperty("提交的题目答案")
        private String answer;
        @ApiModelProperty("题目的类型 R：单选题 C：多项题 Q：问答题")
        private String type;
    }
}
