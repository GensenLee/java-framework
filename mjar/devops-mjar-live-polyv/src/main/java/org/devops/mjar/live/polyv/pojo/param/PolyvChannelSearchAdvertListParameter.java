package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/28 10:20
 * @description：查询频道广告列表
 */

@Data
public class PolyvChannelSearchAdvertListParameter extends ChannelSignBean {


    public static final class PolyvChannelSearchAdvertListParameterBuilder extends ParameterBuilder<PolyvChannelSearchAdvertListParameter> {
        private PolyvChannelSearchAdvertListParameter polyvChannelSearchAdvertListParameter;

        private PolyvChannelSearchAdvertListParameterBuilder() {
            polyvChannelSearchAdvertListParameter = new PolyvChannelSearchAdvertListParameter();
        }

        public static PolyvChannelSearchAdvertListParameterBuilder aPolyvChannelSearchAdvertListParameter() {
            return new PolyvChannelSearchAdvertListParameterBuilder();
        }

        public PolyvChannelSearchAdvertListParameterBuilder withChannelId(String channelId) {
            polyvChannelSearchAdvertListParameter.setChannelId(channelId);
            return this;
        }

        @Override
        public PolyvChannelSearchAdvertListParameter build() {
            return polyvChannelSearchAdvertListParameter;
        }
    }
}
