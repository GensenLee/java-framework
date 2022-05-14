package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gensen.Lee
 */
@Data
@ApiModel(description = "直播监控列表对象")
public class PolyvMonitor extends BaseBean {
    @ApiModelProperty("应用appId")
    private String appId;
    @ApiModelProperty("推流日志ID")
    private String publishId;
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("频道ID")
    private String channelId;
    @ApiModelProperty("推流IP")
    private String ipAddress;
    @ApiModelProperty("流名")
    private String stream;
    @ApiModelProperty("直播状态 (\"live\": 直播中, \"end\": 暂无直播, \"waiting\": 等待直播, \"pull\": 拉流直播中，\"disk\"：硬盘直播中）")
    private String status;
    @ApiModelProperty("直播开始时间")
    private String startTime;
    @ApiModelProperty("直播结束时间")
    private String endTime;
    @ApiModelProperty("更新时间")
    private String currentDay;
    @ApiModelProperty("创建时间")
    private String createdTime;
    @ApiModelProperty("更新时间")
    private String lastModified;
    @ApiModelProperty("更新时间")
    private String isNgbEnabled;
    @ApiModelProperty("更新时间")
    private String isUrlProtected;
    @ApiModelProperty("频道名称")
    private String channelName;
    @ApiModelProperty("直播场景 (\"alone\": 活动直播，\"ppt\": 三分屏， “topclass”：大班课）")
    private String scene;
    @ApiModelProperty("直播截图地址")
    private String captureImg;

}
