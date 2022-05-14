package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author GENSEN
 * @date 2020/10/29 17:32
 * @description：频道详细信息
 */
@Data
public class PolyvChannelDetail extends BaseBean {

    @ApiModelProperty("直播频道ID")
    private String channelId;
    @ApiModelProperty("直播频道名称")
    private String name;
    @ApiModelProperty("频道密码")
    private String channelPasswd;
    @ApiModelProperty("分类id")
    private String categoryId;
    @ApiModelProperty("场景，alone-活动直播，ppt-三分屏，topclass-大班课")
    private String scene;
    @ApiModelProperty("场景描述")
    private String sceneText;
    @ApiModelProperty("观看页状态，live-直播中，playback-回放中，end-已结束，waiting-未开始")
    private String watchStatus;
    @ApiModelProperty("观看页状态描述，直播中，回放中，已结束，未开始")
    private String watchStatusText;
    @ApiModelProperty("观看页链接")
    private String watchUrl;
    @ApiModelProperty("直播介绍")
    private String content;
    @ApiModelProperty("直播开始时间")
    private Integer startTime;
    @ApiModelProperty("authSetting")
    private List<AuthSettingDTO> authSetting;

    @Data
    public static class AuthSettingDTO {
        @ApiModelProperty("直播频道ID")
        private Integer channelId;
        @ApiModelProperty("用于实现一个频道设置两个观看条件，为1或2（1为主要条件，2为次要条件）")
        private Integer rank;
        @ApiModelProperty("用户ID")
        private String userId;
        @ApiModelProperty("是否开启全局设置（Y/N）")
        private String globalSettingEnabled;
        @ApiModelProperty("是否开启观看条件(Y/N)")
        private String enabled;
        @ApiModelProperty("观看条件类型(1. 无限制 none 2. 验证码观看 code 3. 付费观看 pay 4. 白名单观看 phone 5. 登记观看 info 6. 分享观看 wxshare 7. 自定义授权观看 custom 8. 外部授权观看 external)")
        private String authType;
        @ApiModelProperty("白名单观看提示信息")
        private String authTips;
        @ApiModelProperty("付费观看提示信息")
        private String payAuthTips;
        @ApiModelProperty("验证码观看的验证码提示")
        private String codeAuthTips;
        @ApiModelProperty("验证码观看的二维码提示")
        private String infoAuthTips;
        @ApiModelProperty("验证码观看的验证码")
        private Object authCode;
        @ApiModelProperty("验证码观看的二维码提示")
        private Object qcodeTips;
        @ApiModelProperty("验证码观看的二维码图片")
        private Object qcodeImg;
        @ApiModelProperty("付费观看的价格")
        private Double price;
        @ApiModelProperty("付费观看，截止时间，为null表示：一次付费，永久有效")
        private Object watchEndTime;
        @ApiModelProperty("付费观看的截止时长 （天）")
        private Object validTimePeriod;
        @ApiModelProperty("自定义授权观看的key")
        private String customKey;
        @ApiModelProperty("自定义授权观看的接口地址")
        private Object customUri;
        @ApiModelProperty("外部授权观看的key")
        private String externalKey;
        @ApiModelProperty("外部授权观看的接口地址")
        private Object externalUri;
        @ApiModelProperty("外部授权观看，用户直接访问观看页时的跳转地址")
        private Object externalRedirectUri;
        @ApiModelProperty("directKey")
        private Object directKey;
        @ApiModelProperty("trialWatchEnabled")
        private String trialWatchEnabled;
        @ApiModelProperty("trialWatchTime")
        private String trialWatchTime;
        @ApiModelProperty("trialWatchEndTime")
        private String trialWatchEndTime;
    }
}
