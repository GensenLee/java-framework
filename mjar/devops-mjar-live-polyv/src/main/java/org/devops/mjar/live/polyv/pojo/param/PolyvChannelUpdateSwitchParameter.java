package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;
import lombok.Getter;

/**
 * @author fangzy
 * @description：修改频道的功能开关状态
 */
@Data
public class PolyvChannelUpdateSwitchParameter extends ChannelSignBean {

    /**
     * 开关类型
     */
    private String type;

    /**
     * 开关值，取值Y/N
     */
    private String enabled;

    @Getter
    public enum Type {
        IS_CLOSE_PREVIEW("isClosePreview","是否关闭系统观看页"),
        MOBILE_WATCH("mobileWatch","是否开启移动端系统观看页"),
        MOBILE_AUDIO("mobileAudio","是否开启移动端音视频切换"),
        AUTO_PLAY("autoPlay","是否开启播放器自动播放功能"),
        BOOKING("booking","是否开启预约功能"),
        RED_PACK("redPack","是否开启红包功能"),
        SHARE_BTN_ENABLED("shareBtnEnabled","是否开启分享功能"),
        CHAT("chat","是否开启聊天室"),
        CLOSE_CHATER_LIST("closeChaterList","是否关闭在线列表"),
        CONSULTING_MENU("consultingMenu","是否开启咨询提问"),
        CLOSE_DANMU("closeDanmu","是否关闭弹幕功能"),
        PRAISE("praise","是否开启点赞语功能"),
        WELCOME("welcome","是否开启欢迎语功能"),
        VIEWER_SEND_IMG_ENABLED("viewerSendImgEnabled","是否开启观众发送图片"),
        QA_MENU_ENABLED("qaMenuEnabled","是否开启问答功能"),
        FILTER_MANAGER_MSG_ENABLED("filterManagerMsgEnabled","过滤聊天室管理员的聊天消息开关，即观看页聊天室只看主持人开关"),
        SHOW_CUSTOM_MESSAGE_ENABLED("showCustomMessageEnabled","显示自定义消息开关"),
        CHAT_ONLINE_NUMBER_ENABLE("chatOnlineNumberEnable","在线人数开关");

        Type(String code, String description) {
            this.code = code;
            this.description = description;
        }

        private String code;
        private String description;
    }


    public static final class PolyvChannelUpdateSwitchParameterBuilder extends ParameterBuilder<PolyvChannelUpdateSwitchParameter> {
        private PolyvChannelUpdateSwitchParameter polyvChannelUpdateSwitchParameter;

        private PolyvChannelUpdateSwitchParameterBuilder() {
            polyvChannelUpdateSwitchParameter = new PolyvChannelUpdateSwitchParameter();
        }

        public static PolyvChannelUpdateSwitchParameterBuilder aPolyvChannelUpdateSwitchParameter() {
            return new PolyvChannelUpdateSwitchParameterBuilder();
        }

        public PolyvChannelUpdateSwitchParameterBuilder withType(Type type) {
            polyvChannelUpdateSwitchParameter.setType(type.getCode());
            return this;
        }

        public PolyvChannelUpdateSwitchParameterBuilder withEnabled(EnableSetting enabled) {
            polyvChannelUpdateSwitchParameter.setEnabled(enabled.getValue());
            return this;
        }

        @Override
        public PolyvChannelUpdateSwitchParameter build() {
            return polyvChannelUpdateSwitchParameter;
        }
    }
}
