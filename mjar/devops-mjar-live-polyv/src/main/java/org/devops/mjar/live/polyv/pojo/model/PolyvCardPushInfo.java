package org.devops.mjar.live.polyv.pojo.model;

import lombok.Data;
import org.devops.core.utils.vo.BaseBean;

import java.time.LocalDateTime;

/**
 * @author fangzy
 * @description：频道卡片推送
 */
@Data
public class PolyvCardPushInfo extends BaseBean {

    /**
     * 卡片推送主键ID
     */
    private Long id;

    /**
     * 频道ID
     */
    private Integer channelId;

    /**
     * 卡片标题，最多16个字符
     */
    private String title;

    /**
     * 卡片样式类型，giftbox：礼物领取样式，redpack：红包样式
     */
    private String imageType;

    /**
     * 卡片倒计时时长，取值：0,5,10,20,30，单位：秒，0为不显示倒计时时长
     */
    private Integer duration;

    /**
     * 卡片跳转链接地址，带http:等协议头
     */
    private String link;

    /**
     * 推送结束时间
     */
    private LocalDateTime pushEndTime;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 修改时间
     */
    private LocalDateTime lastModified;

    /**
     * 推送状态Y.推送中、N.未推送、L.上次推送
     */
    private String pushStatus;

    /**
     * 推送时间
     */
    private LocalDateTime pushTime;

    /**
     * 卡片入口Y.开启、N.关闭
     */
    private String enterEnabled;

    /**
     * 弹出方式，PUSH.推送后立即弹出、WATCH.观看后弹出
     */
    private String showCondition;

    /**
     * 观看时长
     */
    private Integer conditionValue;

    /**
     * 观看时长单位SECONDS.秒、MINUTES.分钟
     */
    private String conditionUnit;

    /**
     * 倒计时文案，showCondition为WATCH时生效，最多8个字符
     */
    private String countdownMsg;

}
