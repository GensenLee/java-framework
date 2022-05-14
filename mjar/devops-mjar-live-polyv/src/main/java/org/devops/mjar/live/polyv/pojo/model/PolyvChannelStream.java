package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "获取频道实时推流信息响应参数")
public class PolyvChannelStream extends BaseBean {

    @ApiModelProperty(value = "推送的CDN节点IP，网宿CDN推流才返回")
    private String deployAddress;
    @ApiModelProperty(value = "推流出口ip，网宿CDN推流才返回")
    private String inAddress;
    @ApiModelProperty(value = "流名")
    private String streamName;
    @ApiModelProperty(value = "推流帧率")
    private String fps;
    @ApiModelProperty(value = "推流丢帧率，网宿CDN推流才返回")
    private String lfr;
    @ApiModelProperty(value = "推流码率")
    private String inBandWidth;

}
