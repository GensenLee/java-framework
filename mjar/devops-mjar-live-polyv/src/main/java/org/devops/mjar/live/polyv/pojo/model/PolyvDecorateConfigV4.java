package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

import java.util.List;

/**
 * 页面装修
 */
@Data
public class PolyvDecorateConfigV4 extends BaseBean {

    /**
     * 普通直播观看页布局配置(普通:normal,竖屏:portrait)
     */
    private String aloneWatchLayout;

    /**
     * 模板-装修里聊天对象
     */
    private DecorateChatConfig chat;

    /**
     * 模板-装修中文直播介绍页对象
     */
    private DecorateDescConfig desc;
    /**
     * 模板-装修英文直播介绍页对象
     */
    private DecorateDescEnConfig descEn;
    /**
     * 英文菜单列表对象
     */
    private List<DecorateMenuConfig> enMenus;

    /**
     * 双语直播间开关，Y：开启，N：关闭
     */
    private String englishSettingEnabled;

    /**
     * 中文菜单列表对象
     */
    private List<DecorateMenuConfig> menus;

    /**
     * 模板-装修播放器对象
     */
    private DecoratePlayerConfig player;

    /**
     * 三分屏移动端观看布局,normal:常规直播,portrait:直播带货
     */
    private String pptMobileWatchLayout;

    /**
     * 皮肤，black：时尚黑，red：喜庆红，blue：科技蓝，white：经典白，green：薄荷绿，golden：富贵金
     */
    private String skin;

    /**
     * 模板-装修引导页对象
     */
    private DecorateSplashConfig splash;

    /**
     * 引导页开关，Y：开启，N：关闭
     */
    private String splashEnabled;

    @Data
    public static class DecorateChatConfig extends BaseBean {
        /**
         * 累计点赞人数 (点赞基数)
         */
        private Integer baseLikes;
        /**
         * 在线人数开关，Y：开启，N：关闭
         */
        private String chatOnlineNumberEnable;
        /**
         * 情绪直播间开关，Y：开启，N：关闭
         */
        private String emotionEnabled;
        /**
         * 红包开关，Y：开启，N：关闭
         */
        private String redPackEnabled;
        /**
         * 点赞开关，Y：开启，N：关闭
         */
        private String sendFlowersEnabled;
        /**
         * 发送图片开关，Y：开启，N：关闭
         */
        private String viewerSendImgEnabled;
        /**
         * 欢迎语开关，Y：开启，N：关闭
         */
        private String welcomeEnabled;
        /**
         * 提现开关，Y：开启，N：关闭
         */
        private String withdrawEnabled;
    }

    @Data
    public static class DecorateDescConfig extends BaseBean {
        /**
         * 暖场图片 -> 封面图片
         */
        private String coverImageUrl;
        /**
         * 图标URL
         */
        private String iconUrl;
        /**
         * 主持人名称，最大长度50
         */
        private String publisher;
        /**
         * 标题 -> 直播名称，最大长度100
         */
        private String title;
    }

    @Data
    public static class DecorateDescEnConfig extends BaseBean {
        /**
         * 主持人英文名称，最大长度50
         */
        private String publisher;
        /**
         * 直播英文名称，最大长度100
         */
        private String title;
    }

    @Data
    public static class DecorateMenuConfig extends BaseBean {
        /**
         * 菜单内容
         */
        private String content;
        /**
         * 菜单ID
         */
        private String menuId;
        /**
         * 菜单名称
         */
        private String name;
        /**
         * 菜单类型
         * desc：直播介绍
         * chat：互动聊天
         * quiz：提问
         * qa：问答
         * invite：邀请海报
         * text：图文菜单
         */
        private String type;

    }

    @Data
    public static class DecoratePlayerConfig extends BaseBean {
        /**
         * 实际累计观看次数 (真实次数)
         */
        private Integer actualPV;
        /**
         * PC背景图片
         */
        private String backgroundUrl;
        /**
         * 基础观看次数
         */
        private Integer basePV;
        /**
         * 封面(暖场)跳转链接
         */
        private String coverJumpUrl;
        /**
         * 水印链接
         */
        private String iconLink;
        /**
         * 图标位置 (水印位置)
         */
        private String iconPosition;
        /**
         * 水印图片URL
         */
        private String iconUrl;
        /**
         * 图标透明度
         */
        private Float logoOpacity;
        /**
         * 暖场开关，Y：开启，N：关闭
         */
        private String warmUpEnabled;
        /**
         * 暖场图片地址 (直播封面图)
         */
        private String warmUpImageUrl;
        /**
         * 水印开关,Y：开启，N：关闭
         */
        private String watermarkEnabled;
    }

    @Data
    public static class DecorateSplashConfig extends BaseBean {
        /**
         * 引导页图片
         */
        private String splashImageUrl;
    }

}


