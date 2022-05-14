package org.devops.mjar.live.polyv.pojo.param;

import lombok.Data;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;

/**
 * @author fangzy
 * @description：取消推送卡片
 */
@Data
public class PolyvChannelCardPushCancelParameter extends ChannelSignBean {

    /**
     * 卡片推送主键ID
     */
    private Long cardPushId;


    public static final class PolyvChannelCardPushCancelParameterBuilder extends ParameterBuilder<PolyvChannelCardPushCancelParameter> {
        private PolyvChannelCardPushCancelParameter polyvChannelCardPushCancelParameter;

        private PolyvChannelCardPushCancelParameterBuilder() {
            polyvChannelCardPushCancelParameter = new PolyvChannelCardPushCancelParameter();
        }

        public static PolyvChannelCardPushCancelParameterBuilder aPolyvChannelCardPushCancelParameter() {
            return new PolyvChannelCardPushCancelParameterBuilder();
        }

        public PolyvChannelCardPushCancelParameterBuilder withCardPushId(Long cardPushId) {
            polyvChannelCardPushCancelParameter.setCardPushId(cardPushId);
            return this;
        }

        @Override
        public PolyvChannelCardPushCancelParameter build() {
            return polyvChannelCardPushCancelParameter;
        }
    }
}
