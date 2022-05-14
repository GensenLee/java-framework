package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "获取频道实时在线人数响应")
public class PolyvChannelViewers extends BaseBean {

    @ApiModelProperty(value = "频道号")
    private String channelId;
    @ApiModelProperty(value = "时间")
    private String time;
    @ApiModelProperty(value = "人数")
    private Long count;

}
