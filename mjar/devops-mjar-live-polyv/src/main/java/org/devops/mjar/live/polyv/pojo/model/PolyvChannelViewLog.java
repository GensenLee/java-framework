package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GENSEN
 * @date 2020/10/31 17:44
 * @description：频道观看日志
 */
@NoArgsConstructor
@ApiModel("频道观看日志")
@Data
public class PolyvChannelViewLog extends BaseBean {

    @ApiModelProperty("playId")
    private String playId;
    @ApiModelProperty("userId")
    private String userId;
    @ApiModelProperty("频道号")
    private String channelId;
    @ApiModelProperty("频道名称")
    private String name;
    @ApiModelProperty("播放时长，单位：秒")
    private Long playDuration;
    @ApiModelProperty("停留时长，单位：秒")
    private Long stayDuration;
    @ApiModelProperty("流量大小，单位为Byte")
    private Long flowSize;
    @ApiModelProperty("pc视频播放量")
    private String sessionId;
    @ApiModelProperty("使用POLYV观看页的观众ID")
    private String param1;
    @ApiModelProperty("使用POLYV观看页的观众昵称")
    private String param2;
    @ApiModelProperty("观看类型：取值 live(直播)、vod(回放)")
    private String param3;
    @ApiModelProperty("POLYV系统参数")
    private String param4;
    @ApiModelProperty("POLYV系统参数")
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
    @ApiModelProperty("是否为移动端 Y/N")
    private String isMobile;
    @ApiModelProperty("日志查询日期 (格式为：yyyy-MM-dd)")
    private String currentDay;
    @ApiModelProperty("日志创建日期 (13位时间戳)")
    private Long createdTime;
    @ApiModelProperty("日志更新日期 (13位时间戳)")
    private Long lastModified;
//    @ApiModelProperty("进入时间 （13位时间戳）")
//    private Long enterTime;
    @ApiModelProperty("离开直播时间 （13位时间戳）")
    private Long leftTime;
}

/**
 * channelId : 151462
 * flowSize : 10028116
 * sessionId : eyz0awxrlh
 * param1 : 1520499775580
 * param2 : 广州观众/78614
 * param3 : live
 * param4 :
 * param5 :
 * ipAddress : 59.42.41.1
 * country : 中国
 * province : 广东
 * city : 广州
 * isp : /南沙区电信
 * referer : https://live.polyv.cn/watch/151462
 * userAgent : Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36
 * operatingSystem : Mac OS X
 * browser : Chrome
 * isMobile : N
 * currentDay : 2018-03-08
 * createdTime : 1520501705000
 * lastModified : 1520504494000
 */
