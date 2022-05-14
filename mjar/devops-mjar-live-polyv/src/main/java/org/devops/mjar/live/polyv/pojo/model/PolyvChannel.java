package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 */
@Data
@ApiModel(description = "频道信息")
public class PolyvChannel extends BaseBean {

    @ApiModelProperty("直播频道ID")
    private String channelId;
    @ApiModelProperty("直播频道名称")
    private String name;
    @ApiModelProperty("主持人姓名")
    private Object publisher;
    @ApiModelProperty("直播频道描述")
    private String description;
    @ApiModelProperty("直播推流地址")
    private String url;
    @ApiModelProperty("直播流名称")
    private String stream;
    @ApiModelProperty("播发器")
    private String logoImage;
    @ApiModelProperty("Logo不透明度，1表示完全不透明")
    private Double logoOpacity;
    @ApiModelProperty("Logo位置")
    private String logoPosition;
    @ApiModelProperty("Logo的跳转链接")
    private String logoHref;
    @ApiModelProperty("播放前显示的封面图")
    private String coverImage;
    @ApiModelProperty("封面图的跳转链接")
    private String coverHref;
    @ApiModelProperty("等待推流时的显示图片")
    private String waitImage;
    @ApiModelProperty("等待推流时显示图片的跳转链接")
    private String waitHref;
    @ApiModelProperty("切断流时的显示图片")
    private String cutoffImage;
    @ApiModelProperty("切断流时显示图片的跳转链接")
    private String cutoffHref;
    @ApiModelProperty("广告类型")
    private String advertType;
    @ApiModelProperty("广告时长")
    private Integer advertDuration;
    @ApiModelProperty("广告区域宽度")
    private Integer advertWidth;
    @ApiModelProperty("广告区域高度")
    private Integer advertHeight;
    @ApiModelProperty("图片广告")
    private String advertImage;
    @ApiModelProperty("广告的跳转链接")
    private String advertHref;
    @ApiModelProperty("视频广告ID")
    private String advertFlvVid;
    @ApiModelProperty("视频广告链接")
    private String advertFlvUrl;
    @ApiModelProperty("播放器控制栏颜色")
    private String playerColor;
    @ApiModelProperty("自动播放")
    private Boolean autoPlay;
    @ApiModelProperty("一开始的暖场视频")
    private String warmUpFlv;
    @ApiModelProperty("观看密码限制，需要输入观看密码才能播放流")
    private Boolean passwdRestrict;
    @ApiModelProperty("观看密码加密后的密文")
    private String passwdEncrypted;
    @ApiModelProperty("仅推音频流")
    private String isOnlyAudio;
    @ApiModelProperty("低延迟")
    private String isLowLatency;
    @ApiModelProperty("直播拉流（播放）m3u8地址")
    private String m3u8Url;
    @ApiModelProperty("直播拉流（播放）m3u8地址1")
    private String m3u8Url1;
    @ApiModelProperty("直播拉流（播放）m3u8地址2")
    private String m3u8Url2;
    @ApiModelProperty("直播拉流（播放）m3u8地址3")
    private String m3u8Url3;
    @ApiModelProperty("服务器返回的时间戳（毫秒）")
    private Long currentTimeMillis;
    @ApiModelProperty("频道的图标")
    private String channelLogoImage;
    @ApiModelProperty("直播场景：alone 活动直播, topclass 大班课, ppt 三分屏")
    private String scene;
    @ApiModelProperty("所属分类ID")
    private Integer categoryId;
    @ApiModelProperty("所属分类名称")
    private String categoryName;

}