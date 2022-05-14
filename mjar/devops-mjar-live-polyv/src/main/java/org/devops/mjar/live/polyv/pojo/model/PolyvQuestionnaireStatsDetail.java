package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "问卷下各个问题的答题统计")
@Data
public class PolyvQuestionnaireStatsDetail extends BaseBean {
    @ApiModelProperty("总数")
    private Integer total;
    @ApiModelProperty("题目的答题统计信息，数组[]")
    private List<PolyvQuestionsStatsQuestion> questions;
}