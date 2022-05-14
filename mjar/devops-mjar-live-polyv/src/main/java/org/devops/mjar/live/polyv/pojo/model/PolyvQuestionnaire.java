package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author GENSEN
 * @date 2020/12/12 14:37
 * @description：问卷
 */
@Data
@ApiModel("问卷")
public class PolyvQuestionnaire extends BaseBean {

    @ApiModelProperty("问卷ID")
    private String questionnaireId;

    @ApiModelProperty("用户自定义问卷ID")
    private String customQuestionnaireId;

    @ApiModelProperty("频道id")
    private String channelId;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("问卷名称")
    private String name;

    @ApiModelProperty("问卷状态，draft：草稿，send：已发送，delete：已删除")
    private String status;

    @ApiModelProperty("问卷创建时间")
    private Long createdTime;

    @ApiModelProperty("问卷最后修改时间")
    private Long lastModified;

    @ApiModelProperty("停止问卷时间")
    private Long endTime;

    @ApiModelProperty("问卷问题列表")
    private List<PolyvQuestionnaireQuestions> questions;

}
