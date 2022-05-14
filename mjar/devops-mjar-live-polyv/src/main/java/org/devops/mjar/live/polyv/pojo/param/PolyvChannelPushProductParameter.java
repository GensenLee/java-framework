package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @description：推送频道商品库商品
 */
@Data
public class PolyvChannelPushProductParameter extends ChannelSignBean {

    private Integer productId;


    public static final class PolyvChannelPushProductParameterBuilder extends ParameterBuilder<PolyvChannelPushProductParameter> {
        private PolyvChannelPushProductParameter polyvChannelPushProductParameter;

        private PolyvChannelPushProductParameterBuilder() {
            polyvChannelPushProductParameter = new PolyvChannelPushProductParameter();
        }

        public static PolyvChannelPushProductParameterBuilder aPolyvChannelPushProductParameter() {
            return new PolyvChannelPushProductParameterBuilder();
        }

        public PolyvChannelPushProductParameterBuilder withProductId(Integer productId) {
            polyvChannelPushProductParameter.setProductId(productId);
            return this;
        }

        @Override
        public PolyvChannelPushProductParameter build() {
            return polyvChannelPushProductParameter;
        }
    }
}
