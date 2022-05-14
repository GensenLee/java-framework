package org.devops.mjar.live.polyv.pojo.param;

import lombok.Data;
import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.polyv.enums.CardConditionUnit;
import org.devops.mjar.live.polyv.enums.CardImageType;
import org.devops.mjar.live.polyv.enums.CardShowCondition;
import org.devops.mjar.live.polyv.enums.EnableSetting;

/**
 * @author fangzy
 * @description：创建频道卡片推送
 */
@Data
public class PolyvChannelCardPushCreateParameter extends ChannelSignBean {

    /**
     * 卡片样式类型，giftbox：礼物领取样式，redpack：红包样式
     */
    private String imageType;

    /**
     * 卡片标题，最多16个字符
     */
    private String title;

    /**
     * 卡片跳转链接地址，需要带http:等协议头
     */
    private String link;

    /**
     * 卡片倒计时时长，取值：0,5,10,20,30，单位：秒，0为不显示倒计时时长
     */
    private Integer duration;

    /**
     * 弹出方式，PUSH：推送后立即弹出、WATCH：观看后弹出
     */
    private String showCondition;

    /**
     * 观看后弹出的观看时长，showCondition为WATCH时生效
     */
    @VerifyField(ignore = true)
    private Integer conditionValue;

    /**
     * 观看后弹出的观看时长单位SECONDS.秒、MINUTES.分钟，showCondition为WATCH时，该值生效且必填
     */
    @VerifyField(ignore = true)
    private String conditionUnit;

    /**
     * 倒计时文案，showCondition为WATCH时生效，最多8个字符
     */
    @VerifyField(ignore = true)
    private String countdownMsg;

    /**
     * 卡片入口开关Y.开启、N.关闭
     */
    private String enterEnabled;


    public static final class PolyvChannelCardPushCreateParameterBuilder extends ParameterBuilder<PolyvChannelCardPushCreateParameter> {
        private PolyvChannelCardPushCreateParameter polyvChannelCardPushCreateParameter;

        private PolyvChannelCardPushCreateParameterBuilder() {
            polyvChannelCardPushCreateParameter = new PolyvChannelCardPushCreateParameter();
        }

        public static PolyvChannelCardPushCreateParameterBuilder aPolyvChannelCardPushCreateParameter() {
            return new PolyvChannelCardPushCreateParameterBuilder();
        }

        public PolyvChannelCardPushCreateParameterBuilder withImageType(CardImageType imageType) {
            polyvChannelCardPushCreateParameter.setImageType(imageType.getValue());
            return this;
        }

        public PolyvChannelCardPushCreateParameterBuilder withTitle(String title) {
            polyvChannelCardPushCreateParameter.setTitle(title);
            return this;
        }

        public PolyvChannelCardPushCreateParameterBuilder withLink(String link) {
            polyvChannelCardPushCreateParameter.setLink(link);
            return this;
        }

        public PolyvChannelCardPushCreateParameterBuilder withDuration(Integer duration) {
            polyvChannelCardPushCreateParameter.setDuration(duration);
            return this;
        }

        public PolyvChannelCardPushCreateParameterBuilder withShowCondition(CardShowCondition showCondition) {
            polyvChannelCardPushCreateParameter.setShowCondition(showCondition.getValue());
            return this;
        }

        public PolyvChannelCardPushCreateParameterBuilder withConditionValue(Integer conditionValue) {
            polyvChannelCardPushCreateParameter.setConditionValue(conditionValue);
            return this;
        }

        public PolyvChannelCardPushCreateParameterBuilder withConditionUnit(CardConditionUnit conditionUnit) {
            polyvChannelCardPushCreateParameter.setConditionUnit(conditionUnit.getValue());
            return this;
        }

        public PolyvChannelCardPushCreateParameterBuilder withCountdownMsg(String countdownMsg) {
            polyvChannelCardPushCreateParameter.setCountdownMsg(countdownMsg);
            return this;
        }

        public PolyvChannelCardPushCreateParameterBuilder withEnterEnabled(EnableSetting enterEnabled) {
            polyvChannelCardPushCreateParameter.setEnterEnabled(enterEnabled.getValue());
            return this;
        }

        @Override
        public PolyvChannelCardPushCreateParameter build() {
            return polyvChannelCardPushCreateParameter;
        }
    }
}
