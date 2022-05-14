package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("问卷基本信息")
@Data
public class PolyvQuestionnaireRecord extends BaseBean {


    @ApiModelProperty("问卷ID")
    private String questionnaireId;

    @ApiModelProperty("用户自定义问卷ID")
    private String customQuestionnaireId;

    @ApiModelProperty("用户自定义问卷ID")
    private String questionnaireTitle;

    @ApiModelProperty("更新时间，13位毫秒级时间戳")
    private Long lastModified;

    @ApiModelProperty("问卷结束时间，13位毫秒级时间戳")
    private Long endTime;

    @ApiModelProperty("问卷下各个问题的答题统计")
    PolyvQuestionnaireStatsDetail questionStats;

    @ApiModelProperty("观看端提交答题的信息")
    List<PolyvQuestionUsers> users;

}
