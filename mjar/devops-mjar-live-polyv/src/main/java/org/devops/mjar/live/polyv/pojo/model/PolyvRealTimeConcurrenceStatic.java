package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "实时观看人数-响应参数")
public class PolyvRealTimeConcurrenceStatic extends BaseBean {
    
    @ApiModelProperty("频道号")
    private Integer channelId;
    
    @ApiModelProperty("时间点，格式：hh:mm:ss")
    private String time;
    
    @ApiModelProperty("并发数")
    private Integer count;
}
