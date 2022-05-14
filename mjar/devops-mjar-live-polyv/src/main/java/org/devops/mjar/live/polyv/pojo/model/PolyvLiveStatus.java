package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fangzy
 */
@Data
@ApiModel(description = "频道状态信息信息")
public class PolyvLiveStatus extends BaseBean {
    @ApiModelProperty("频道号")
    private String channelId;
    @ApiModelProperty("频道的直播状态: live：正在直播, end：未直播")
    private String status;
}
