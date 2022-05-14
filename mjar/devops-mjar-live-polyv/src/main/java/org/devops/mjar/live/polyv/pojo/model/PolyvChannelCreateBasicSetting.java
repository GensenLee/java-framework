package org.devops.mjar.live.polyv.pojo.model;

import io.swagger.annotations.ApiModelProperty;
import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.enums.LiveScene;
import lombok.Data;

/**
 * 基础设置
 */
@Data
public class PolyvChannelCreateBasicSetting extends BaseBean {

    /**
     * 频道名称
     */
    private String name;

    /**
     * 频道密码，长度不能超过16位，必须同时包含字母和数字
     */
    private String channelPasswd;

    /**
     * 是否自动播放
     * 0：不自动播放
     * 1：自动播放，默认1
     */
    private Integer autoPlay;

    /**
     * 播放器控制栏颜色，默认：#666666
     */
    private String playerColor;

    /**
     * 直播场景
     */
    private String scene;

    public void setScene(LiveScene scene) {
        if (scene == null) {
            return;
        }
        this.scene = scene.getValue();
    }

    /**
     * 新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“查询直播分类”接口得到）
     */
    private Integer categoryId;

    /**
     * 最大同时在线人数
     */
    private Integer maxViewer;

    /**
     * 直播开始时间，13位毫秒级时间戳
     */
    private Long startTime;

    /**
     * 直播介绍的内容
     */
    private String desc;

    /**
     * 主持人
     */
    private String publisher;

    /**
     * 连麦人数，最大16人（范围大于等于-1，小于等于全局设置的连麦人数），-1：使用全局设置的连麦人数
     */
    private Integer linkMicLimit;

    /**
     * 是否为无延时直播，默认为N
     * Y：是
     * N：否
     */
    private String pureRtcEnabled;

    public void setPureRtcEnabled(EnableSetting pureRtcEnabled) {
        if (pureRtcEnabled == null) {
            return;
        }
        this.pureRtcEnabled = pureRtcEnabled.getValue();
    }

    /**
     * 是否为接收转播频道，不填或者填其他值为发起转播频道（注：需要开启频道转播功能该参数才生效）
     * Y：表示是
     * N：表示否
     */
    private String receive;

    public void setReceive(EnableSetting receive) {
        if (receive == null) {
            return;
        }
        this.receive = receive.getValue();
    }

    /**
     * 接收转播频道号，多个频道号用半角逗号，隔开，如果receive参数值为Y时，此参数无效（注：需要开启频道转播功能该参数才生效）
     */
    private String receiveChannelIds;

    /**
     * 频道是否只能直播一次，默认为N
     * Y：是
     * N：否
     */
    private String onlyOneLiveEnabled;

    public void setOnlyOneLiveEnabled(EnableSetting onlyOneLiveEnabled) {
        if (onlyOneLiveEnabled == null) {
            return;
        }
        this.onlyOneLiveEnabled = onlyOneLiveEnabled.getValue();
    }

    /**
     * 封面图片地址
     */
    private String coverImg;

    /**
     * 导页开关，默认为N
     * Y：是
     * N：否
     */
    private String splashEnabled;

    public void setSplashEnabled(EnableSetting splashEnabled) {
        if (splashEnabled == null) {
            return;
        }
        this.splashEnabled = splashEnabled.getValue();
    }

    /**
     * 引导图地址
     */
    private String splashImg;

    /**
     * 点赞数
     */
    private Long likes;

    /**
     * 累积观看数
     */
    private Long pageView;

    /**
     * 是否关闭弹幕功能的开关，
     * Y：表示关闭
     * N：表示不关闭
     */
    private String closeDanmu;

    /**
     * 是否显示弹幕信息开关，
     * Y：表示显示
     * N：表示不显示
     */
    private String showDanmuInfoEnabled;

    public void setShowDanmuInfoEnabled(EnableSetting showDanmuInfoEnabled) {
        if (showDanmuInfoEnabled == null) {
            return;
        }
        this.showDanmuInfoEnabled = showDanmuInfoEnabled.getValue();
    }

    /**
     * 延时直播，默认不延时，可选值30(延时30秒)
     */
     private Integer hlsDelayTime;

}

   