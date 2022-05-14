package org.devops.mjar.live.polyv.pojo.param;

import lombok.Data;
import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import org.devops.mjar.live.polyv.enums.CardConditionUnit;
import org.devops.mjar.live.polyv.enums.CardImageType;
import org.devops.mjar.live.polyv.enums.CardShowCondition;
import org.devops.mjar.live.polyv.enums.EnableSetting;

/**
 * @author fangzy
 * @description：修改频道卡片推送
 */
@Data
public class PolyvChannelCardPushUpdateParameter extends ChannelSignBean {

    /**
     * 卡片推送主键ID
     */
    private Long cardPushId;


    @SignIgnore
    private PolyvChannelCardPushUpdateParameterBody body = new PolyvChannelCardPushUpdateParameterBody();

    @Data
    public static class PolyvChannelCardPushUpdateParameterBody extends BaseBean {

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
        private Integer conditionValue;

        /**
         * 观看后弹出的观看时长单位SECONDS.秒、MINUTES.分钟，showCondition为WATCH时，该值生效且必填
         */
        private String conditionUnit;

        /**
         * 倒计时文案，showCondition为WATCH时生效，最多8个字符
         */
        private String countdownMsg;

        /**
         * 卡片入口开关Y.开启、N.关闭
         */
        private String enterEnabled;
    }


    public static final class PolyvChannelCardPushUpdateParameterBuilder extends ParameterBuilder<PolyvChannelCardPushUpdateParameter> {
        private PolyvChannelCardPushUpdateParameter polyvChannelCardPushUpdateParameter;

        private PolyvChannelCardPushUpdateParameterBuilder() {
            polyvChannelCardPushUpdateParameter = new PolyvChannelCardPushUpdateParameter();
        }

        public static PolyvChannelCardPushUpdateParameterBuilder aPolyvChannelCardPushUpdateParameter() {
            return new PolyvChannelCardPushUpdateParameterBuilder();
        }

        public PolyvChannelCardPushUpdateParameterBuilder withCardPushId(Long cardPushId) {
            polyvChannelCardPushUpdateParameter.setCardPushId(cardPushId);
            return this;
        }

        public PolyvChannelCardPushUpdateParameterBuilder withImageType(CardImageType imageType) {
            polyvChannelCardPushUpdateParameter.getBody().setImageType(imageType.getValue());
            return this;
        }

        public PolyvChannelCardPushUpdateParameterBuilder withTitle(String title) {
            polyvChannelCardPushUpdateParameter.getBody().setTitle(title);
            return this;
        }

        public PolyvChannelCardPushUpdateParameterBuilder withLink(String link) {
            polyvChannelCardPushUpdateParameter.getBody().setLink(link);
            return this;
        }

        public PolyvChannelCardPushUpdateParameterBuilder withDuration(Integer duration) {
            polyvChannelCardPushUpdateParameter.getBody().setDuration(duration);
            return this;
        }

        public PolyvChannelCardPushUpdateParameterBuilder withShowCondition(CardShowCondition showCondition) {
            polyvChannelCardPushUpdateParameter.getBody().setShowCondition(showCondition.getValue());
            return this;
        }

        public PolyvChannelCardPushUpdateParameterBuilder withConditionValue(Integer conditionValue) {
            polyvChannelCardPushUpdateParameter.getBody().setConditionValue(conditionValue);
            return this;
        }

        public PolyvChannelCardPushUpdateParameterBuilder withConditionUnit(CardConditionUnit conditionUnit) {
            polyvChannelCardPushUpdateParameter.getBody().setConditionUnit(conditionUnit.getValue());
            return this;
        }

        public PolyvChannelCardPushUpdateParameterBuilder withCountdownMsg(String countdownMsg) {
            polyvChannelCardPushUpdateParameter.getBody().setCountdownMsg(countdownMsg);
            return this;
        }

        public PolyvChannelCardPushUpdateParameterBuilder withEnterEnabled(EnableSetting enterEnabled) {
            polyvChannelCardPushUpdateParameter.getBody().setEnterEnabled(enterEnabled.getValue());
            return this;
        }

        @Override
        public PolyvChannelCardPushUpdateParameter build() {
            return polyvChannelCardPushUpdateParameter;
        }
    }
}
