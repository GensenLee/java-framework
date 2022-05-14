package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "查询频道实时并发数据")
public class PolyvChannelStatisticsRealtime extends BaseBean {
    @ApiModelProperty(value = "状态值")
    private String status;
    @ApiModelProperty(value = "相应的结果")
    private List<Result> result;

    @Data
    static class Result{
        @ApiModelProperty(value = "统计的时间，格式：HH:mm:ss")
        private String time;
        @ApiModelProperty( value = "某个时间，实时观看人数")
        private String count;
    }

}
