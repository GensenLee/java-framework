package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gensen.Lee
 */
@Data
@ApiModel("消费记录")
public class PolyvBilling extends BaseBean {
    @ApiModelProperty(value = "对应月份消耗的分钟数")
    private Long durations;
    @ApiModelProperty(value = "月份，格式 yyyy-MM")
    private String month;
    @ApiModelProperty(value = "当月的最高并发数")
    private Long concurrences;
}
