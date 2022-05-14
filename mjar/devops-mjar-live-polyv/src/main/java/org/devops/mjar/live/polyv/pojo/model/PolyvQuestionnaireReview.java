package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "频道的问卷信息和统计结果")
@Data
public class PolyvQuestionnaireReview extends BaseBean {
    @ApiModelProperty("问卷状态 取值：saved(已保存)，published （已发布），forbidden （问卷已完成填写")
    private String status;

    @ApiModelProperty("提交人数")
    private Integer viewerCount;

    @ApiModelProperty("问卷题目")
    private String name;

    @ApiModelProperty("创建时间")
    private Long createdTime;

    @ApiModelProperty("最后修改时间")
    private Long lastModified;

    @ApiModelProperty("停止提交问卷时间")
    private Long endTime;

    @ApiModelProperty("频道号")
    private String channelId;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("问卷id")
    private String questionnaireId;
}