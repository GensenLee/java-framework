package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Gensen.Lee
 */
@NoArgsConstructor
@Data
@ApiModel(description = "频道信息")
public class PolyvChannelInfo extends BaseBean {
    @ApiModelProperty("频道号")
    private String channelId;
    @ApiModelProperty("频道名称")
    private String name;
    @ApiModelProperty("观看页标题")
    private String watchTitle;
    @ApiModelProperty("主持人姓名")
    private String publisher;
    @ApiModelProperty("开始直播时间的时间戳，未设置过返回0")
    private Long startTime;
    @ApiModelProperty("频道密码")
    private String channelPasswd;
    @ApiModelProperty("直播场景，alone(活动直播),topclass(大班)，ppt（三分屏）")
    private String scene;
    @ApiModelProperty("频道最大观看人数")
    private Integer maxViewer;
    @ApiModelProperty("直播方式:client 普通直播、audio 音频直播")
    private String streamType;
    @ApiModelProperty("频道管理员邮箱")
    private String adminEmail;
    @ApiModelProperty("直播url")
    private String url;
    @ApiModelProperty("讲师链接")
    private String teacherUrl;
    @ApiModelProperty("推流地址")
    private String pushUrl;
    @ApiModelProperty("m3u8推流地址")
    private String m3u8Url;
    @ApiModelProperty("m3u8推流地址")
    private String m3u8Url1;
    @ApiModelProperty("m3u8推流地址")
    private String m3u8Url2;
    @ApiModelProperty("m3u8推流地址")
    private String m3u8Url3;
    @ApiModelProperty("二维码")
    private String qrCode;
    @ApiModelProperty("自定义管理后台的图标地址")
    private String webappDomain;


    /**
     * channelId : 195770
     * userId : b0f7041324
     * description :
     * stream : b0f704132420180514181136151
     * logoImage :
     * logoOpacity : 1
     * logoPosition : tr
     * logoHref :
     * coverImage : http://livestatic.videocc.net/uploaded/images/2019/01/f8evlourwv.jpg
     * coverHref :
     * waitImage :
     * waitHref :
     * cutoffImage :
     * cutoffHref :
     * advertType : NONE
     * advertDuration : 0
     * advertWidth : 0
     * advertHeight : 0
     * advertImage :
     * advertHref :
     * advertFlvVid :
     * advertFlvUrl :
     * playerColor : #666666
     * autoPlay : true
     * warmUpFlv :
     * passwdRestrict : false
     * passwdEncrypted :
     * isOnlyAudio : N
     * isLowLatency : N
     * channelLogoImage : http://livestatic.videocc.net/assets/wimages/pc_images/logo.png
     * categoryId : 133874
     * categoryName : 测试分类
     * currentTimeMillis : 1548343851650
     */

    @ApiModelProperty("直播用户ID")
    private String userId;
    @ApiModelProperty("直播频道描述")
    private String description;
    @ApiModelProperty("直播流名称")
    private String stream;
    @ApiModelProperty("播放器logo")
    private String logoImage;
    @ApiModelProperty("Logo不透明度，1表示完全不透明")
    private Integer logoOpacity;
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
    @ApiModelProperty("频道的图标")
    private String channelLogoImage;
    @ApiModelProperty("所属分类Id")
    private Integer categoryId;
    @ApiModelProperty("所属分类名称")
    private String categoryName;
    @ApiModelProperty("服务器返回的时间戳（毫秒")
    private Long currentTimeMillis;
}
