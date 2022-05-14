package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "查询频道历史并发数据")
public class PolyvChannelStatisticsConcurrence extends BaseBean {
        @ApiModelProperty(value = "并发日期，格式：yyyy-MM-dd")
        private String day;
        @ApiModelProperty(value = "并发时间点，格式：HH:mm")
        private String minute;
        @ApiModelProperty(value = "并发人数")
        private String viewers;



}
