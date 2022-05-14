package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/3/2 18:15
 * @description：查询多个频道的实时在线人数
 */
@Data
public class PolyvChannelsRealtimeViewerListParameter extends AppSignBean {

    private String channelIds;

    public static final class PolyvChannelsRealtimeViewerListParameterBuilder extends ParameterBuilder<PolyvChannelsRealtimeViewerListParameter> {
        private PolyvChannelsRealtimeViewerListParameter polyvChannelsRealtimeViewerListParameter;

        private PolyvChannelsRealtimeViewerListParameterBuilder() {
            polyvChannelsRealtimeViewerListParameter = new PolyvChannelsRealtimeViewerListParameter();
        }

        public static PolyvChannelsRealtimeViewerListParameterBuilder aPolyvChannelsRealtimeViewerListParameter() {
            return new PolyvChannelsRealtimeViewerListParameterBuilder();
        }

        public PolyvChannelsRealtimeViewerListParameterBuilder withChannelIds(String channelIds) {
            polyvChannelsRealtimeViewerListParameter.setChannelIds(channelIds);
            return this;
        }

        @Override
        public PolyvChannelsRealtimeViewerListParameter build() {
            return polyvChannelsRealtimeViewerListParameter;
        }
    }
}
