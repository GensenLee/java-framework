package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

@Data
public class PolyvChannelSearchParameter extends ChannelSignBean {

    public static final class PolyvChannelSearchParameterBuilder extends ParameterBuilder<PolyvChannelSearchParameter> {
        private PolyvChannelSearchParameter polyvChannelSearchParameter;

        private PolyvChannelSearchParameterBuilder() {
            polyvChannelSearchParameter = new PolyvChannelSearchParameter();
        }

        public static PolyvChannelSearchParameterBuilder aPolyvChannelSearchParameter() {
            return new PolyvChannelSearchParameterBuilder();
        }

        @Override
        public PolyvChannelSearchParameter build() {
            return polyvChannelSearchParameter;
        }
    }
}
