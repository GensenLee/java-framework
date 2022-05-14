package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author GENSEN
 * @date 2020/12/23 15:00
 * @description：直播场次
 */
@ApiModel("直播场次")
@Data
public class PolyvChannelSession extends BaseBean {

    @ApiModelProperty("频道号")
    private String channelId;

    @ApiModelProperty("直播场次")
    private String sessionId;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

}
