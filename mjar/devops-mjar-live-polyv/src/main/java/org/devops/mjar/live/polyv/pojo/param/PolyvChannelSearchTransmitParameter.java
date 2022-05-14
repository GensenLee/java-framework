package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;


public class PolyvChannelSearchTransmitParameter extends ChannelSignBean {
    public static final class PolyvChannelSearchTransmitParameterBuilder extends ParameterBuilder<PolyvChannelSearchTransmitParameter> {
        private PolyvChannelSearchTransmitParameter polyvChannelSearchTransmitParameter;

        private PolyvChannelSearchTransmitParameterBuilder() {
            polyvChannelSearchTransmitParameter = new PolyvChannelSearchTransmitParameter();
        }

        public static PolyvChannelSearchTransmitParameterBuilder aPolyvChannelSearchTransmitParameter() {
            return new PolyvChannelSearchTransmitParameterBuilder();
        }

        @Override
        public PolyvChannelSearchTransmitParameter build() {
            return polyvChannelSearchTransmitParameter;
        }
    }
}
