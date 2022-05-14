package org.devops.mjar.live.polyv.pojo.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.devops.core.utils.vo.BaseBean;

/**
 * @author fangzy
 * @description：
 */
@Data
@ApiModel(description = "直播监控信息")
public class PolyvMonitorInfo extends BaseBean {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 当前是否是直播状态（Y/N）
     */
    private String status;

    /**
     * 是否是禁止推流状态（Y/N）
     */
    private String banPush;

    /**
     * 当前直播场景 （"alone" : 普通直播；"topclass" : 大班课；"ppt" : 三分屏 ）
     */
    private String scene;

    /**
     * 是否开启了调试模式（Y/N）
     */
    private String debugEnabled;

    /**
     * 跑马灯开关（Y/N）
     */
    private String marqueeEnabled;

    /**
     * 咨询提问开关（Y/N）
     */
    private String consultingMenuEnabled;

    /**
     * cdn类型
     */
    private String cdnType;

    /**
     * 频道号房间聊天室sign
     */
    private String sign;

    /**
     * 多房间ID
     */
    private String roomIds;

    /**
     * 多房间聊天室sign
     */
    private String roomSign;

    /**
     * 聊天室翻译开关（Y/N）
     */
    private String chatTranslateEnabled;

    /**
     * 是否展示自定义消息开关（Y/N）
     */
    private String showCustomMessageEnabled;

    /**
     * 聊天室token
     */
    private String chatToken;

    /**
     * 聊天室管理员信息对象
     */
    private ChatAdmin chatAdmin;

    @Data
    public static class ChatAdmin {

        /**
         * 聊天室对应频道号
         */
        private int channelId;

        /**
         * 聊天室角色类型
         */
        private String type;

        /**
         * 聊天室管理员昵称
         */
        private String nickname;

        /**
         * 聊天室管理员头衔
         */
        private String actor;

        /**
         * 聊天室管理员头像
         */
        private String avatar;

        /**
         * 聊天室管理员创建时间
         */
        private String createdTime;

        /**
         * 聊天室管理员更新时间
         */
        private String lastModified;

    }

}
