package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 **/
@Data
@ApiModel(description = "获取签到统计列表信息-列表内容")
public class PolyvCheckinStats extends BaseBean {
    @ApiModelProperty("签到ID")
    private String checkinId;
    @ApiModelProperty("签到用户id")
    private String userid;
    @ApiModelProperty("签到时间")
    private String indate;
    @ApiModelProperty("签到时间戳")
    private Long time;
    @ApiModelProperty("频道ID")
    private String roomId;
    @ApiModelProperty("用户昵称")
    private String nickname;
    @ApiModelProperty("是否签到,Y-已签到 N-未签到")
    private String checked;
    @ApiModelProperty("签到记录主键")
    private String id;
    @ApiModelProperty("场次号")
    private String sessionId;
    @ApiModelProperty("在外部授权、直接（独立）授权情况下传过来的自定义参数")
    private String param4;
    @ApiModelProperty("在外部授权、直接（独立）授权情况下传过来的自定义参数")
    private String param5;
}
