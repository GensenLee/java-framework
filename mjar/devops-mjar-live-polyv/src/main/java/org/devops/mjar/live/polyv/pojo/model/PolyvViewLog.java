package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author zhaozx
 */
@ApiModel("按天同步平台下的观看日志")
@Data
public class PolyvViewLog extends BaseBean {
    @ApiModelProperty("表示此次播放动作的ID，唯一主键")
    private String playId;
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("频道号")
    private String channelId;
    @ApiModelProperty("播放时长")
    private String playDuration;
    @ApiModelProperty("缓存时长")
    private String stayDuration;
    @ApiModelProperty("流量大小")
    private String flowSize;
    @ApiModelProperty("直播的场次ID")
    private String sessionId;
    @ApiModelProperty("POLYV系统参数1")
    private String param1;
    @ApiModelProperty("POLYV系统参数2")
    private String param2;
    @ApiModelProperty("POLYV系统参数3")
    private String param3;
    @ApiModelProperty("POLYV系统参数4")
    private String param4;
    @ApiModelProperty("POLYV系统参数5")
    private String param5;
    @ApiModelProperty("IP地址")
    private String ipAddress;
    @ApiModelProperty("国家")
    private String country;
    @ApiModelProperty("省份")
    private String province;
    @ApiModelProperty("城市")
    private String city;
    @ApiModelProperty("ISP运营商")
    private String isp;
    @ApiModelProperty("播放视频页面地址")
    private String referer;
    @ApiModelProperty("用户设备")
    private String userAgent;
    @ApiModelProperty("操作系统")
    private String operatingSystem;
    @ApiModelProperty("浏览器")
    private String browser;
    @ApiModelProperty("是否为移动端")
    private String isMobile;
    @ApiModelProperty("日志查询日期")
    private String currentDay;
    @ApiModelProperty("日志创建日期")
    private Date createdTime;
    @ApiModelProperty("日志更新日期")
    private Date lastModified;
}
