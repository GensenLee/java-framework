package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author GENSEN
 * @date 2020/12/12 18:14
 * @description：问卷统计结果
 */
@ApiModel("问卷统计结果")
@Data
public class PolyvQuestionnaireStats extends BaseBean {

    @ApiModelProperty("问卷统计结果")
    private PolyvQuestionnaireStatsDetail statistics;

    @ApiModelProperty("原问题列表")
    private List<PolyvQuestionnaireQuestions> questions;

}
