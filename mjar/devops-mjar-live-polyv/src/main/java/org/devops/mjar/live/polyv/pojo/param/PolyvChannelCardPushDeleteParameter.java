package org.devops.mjar.live.polyv.pojo.param;

import lombok.Data;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;

/**
 * @author fangzy
 * @description：删除频道卡片推送
 */
@Data
public class PolyvChannelCardPushDeleteParameter extends ChannelSignBean {

    /**
     * 卡片推送主键ID
     */
    private Long cardPushId;


    public static final class PolyvChannelCardPushDeleteParameterBuilder extends ParameterBuilder<PolyvChannelCardPushDeleteParameter> {
        private PolyvChannelCardPushDeleteParameter polyvChannelCardPushDeleteParameter;

        private PolyvChannelCardPushDeleteParameterBuilder() {
            polyvChannelCardPushDeleteParameter = new PolyvChannelCardPushDeleteParameter();
        }

        public static PolyvChannelCardPushDeleteParameterBuilder aPolyvChannelCardPushDeleteParameter() {
            return new PolyvChannelCardPushDeleteParameterBuilder();
        }

        public PolyvChannelCardPushDeleteParameterBuilder withCardPushId(Long cardPushId) {
            polyvChannelCardPushDeleteParameter.setCardPushId(cardPushId);
            return this;
        }

        @Override
        public PolyvChannelCardPushDeleteParameter build() {
            return polyvChannelCardPushDeleteParameter;
        }
    }
}
