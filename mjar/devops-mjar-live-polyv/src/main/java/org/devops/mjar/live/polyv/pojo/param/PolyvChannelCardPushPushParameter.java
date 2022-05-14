package org.devops.mjar.live.polyv.pojo.param;

import lombok.Data;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;

/**
 * @author fangzy
 * @description：推送卡片
 */
@Data
public class PolyvChannelCardPushPushParameter extends ChannelSignBean {

    /**
     * 卡片推送主键ID
     */
    private Long cardPushId;


    public static final class PolyvChannelCardPushPushParameterBuilder extends ParameterBuilder<PolyvChannelCardPushPushParameter> {
        private PolyvChannelCardPushPushParameter polyvChannelCardPushPushParameter;

        private PolyvChannelCardPushPushParameterBuilder() {
            polyvChannelCardPushPushParameter = new PolyvChannelCardPushPushParameter();
        }

        public static PolyvChannelCardPushPushParameterBuilder aPolyvChannelCardPushPushParameter() {
            return new PolyvChannelCardPushPushParameterBuilder();
        }

        public PolyvChannelCardPushPushParameterBuilder withCardPushId(Long cardPushId) {
            polyvChannelCardPushPushParameter.setCardPushId(cardPushId);
            return this;
        }

        @Override
        public PolyvChannelCardPushPushParameter build() {
            return polyvChannelCardPushPushParameter;
        }
    }
}
