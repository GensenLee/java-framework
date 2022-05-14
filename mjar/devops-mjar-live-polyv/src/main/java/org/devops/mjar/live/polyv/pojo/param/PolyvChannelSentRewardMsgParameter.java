package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：发送打赏消息
 */
@Data
public class PolyvChannelSentRewardMsgParameter extends ChannelSignBean {

    /**
     * 打赏者昵称
     */
    private String nickname;

    /**
     * 打赏者头像
     */
    private String avatar;

    /**
     * 打赏者ID
     */
    private String viewerId;

    /**
     * 打赏类型   cash：现金打赏   good：道具打赏
     */
    private String donateType;

    /**
     * 打赏内容：礼物打赏为礼物名称，现金打赏为金额
     */
    private String content;

    /**
     * 礼物打赏时为礼物图片，现金打赏时为空
     */
    @VerifyField(ignore = true)
    private String goodImage;

    /**
     * 直播场次ID
     */
    @VerifyField(ignore = true)
    private String sessionId;

    /**
     * 打赏数量，不传默认为1
     */
    @VerifyField(ignore = true)
    private String goodNum;

    /**
     * 是否socket消息需要用户图片，默认为N（是：Y，否：N）
     */
    @VerifyField(ignore = true)
    private String needUserImage;

    public enum DonateType {
        CASH("cash","现金打赏"),
        GOOD("good", "道具打赏");

        DonateType(String value, String name) {
            this.value = value;
            this.name = name;
        }

        private String value;
        private String name;
    }

    public enum NeedUserImage {
        Y("Y","是"),
        N("N", "否");

        NeedUserImage(String value, String name) {
            this.value = value;
            this.name = name;
        }

        private String value;
        private String name;
    }


    public static final class PolyvChannelSentRewardMsgParameterBuilder extends ParameterBuilder<PolyvChannelSentRewardMsgParameter> {
        private PolyvChannelSentRewardMsgParameter polyvChannelSentRewardMsgParameter;

        private PolyvChannelSentRewardMsgParameterBuilder() {
            polyvChannelSentRewardMsgParameter = new PolyvChannelSentRewardMsgParameter();
        }

        public static PolyvChannelSentRewardMsgParameterBuilder aPolyvChannelSentRewardMsgParameter() {
            return new PolyvChannelSentRewardMsgParameterBuilder();
        }

        public PolyvChannelSentRewardMsgParameterBuilder withNickname(String nickname) {
            polyvChannelSentRewardMsgParameter.setNickname(nickname);
            return this;
        }

        public PolyvChannelSentRewardMsgParameterBuilder withAvatar(String avatar) {
            polyvChannelSentRewardMsgParameter.setAvatar(avatar);
            return this;
        }

        public PolyvChannelSentRewardMsgParameterBuilder withViewerId(String viewerId) {
            polyvChannelSentRewardMsgParameter.setViewerId(viewerId);
            return this;
        }

        public PolyvChannelSentRewardMsgParameterBuilder withDonateType(DonateType donateType) {
            polyvChannelSentRewardMsgParameter.setDonateType(donateType.value);
            return this;
        }

        public PolyvChannelSentRewardMsgParameterBuilder withContent(String content) {
            polyvChannelSentRewardMsgParameter.setContent(content);
            return this;
        }

        public PolyvChannelSentRewardMsgParameterBuilder withGoodImage(String goodImage) {
            polyvChannelSentRewardMsgParameter.setGoodImage(goodImage);
            return this;
        }

        public PolyvChannelSentRewardMsgParameterBuilder withSessionId(String sessionId) {
            polyvChannelSentRewardMsgParameter.setSessionId(sessionId);
            return this;
        }

        public PolyvChannelSentRewardMsgParameterBuilder withGoodNum(String goodNum) {
            polyvChannelSentRewardMsgParameter.setGoodNum(goodNum);
            return this;
        }

        public PolyvChannelSentRewardMsgParameterBuilder withNeedUserImage(NeedUserImage needUserImage) {
            polyvChannelSentRewardMsgParameter.setNeedUserImage(needUserImage.value);
            return this;
        }

        public PolyvChannelSentRewardMsgParameter build() {
            return polyvChannelSentRewardMsgParameter;
        }
    }
}
