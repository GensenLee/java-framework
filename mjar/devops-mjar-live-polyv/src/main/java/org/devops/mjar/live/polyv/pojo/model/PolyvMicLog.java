
package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("连麦详单")
@Data
public class PolyvMicLog extends BaseBean {

    @ApiModelProperty("频道号")
    private String channelId;
    @ApiModelProperty("身份，讲师：teacher、嘉宾：guest、学员：student")
    private String identity;
    @ApiModelProperty("进入 rtc 的时间, 13位毫秒时间戳")
    private Date joinTime;
    @ApiModelProperty("离开 rtc 的时间, 13位毫秒时间戳")
    private Date leaveTime;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("场次号")
    private String sessionId;
    @ApiModelProperty("观众ID")
    private String viewerId;

}
