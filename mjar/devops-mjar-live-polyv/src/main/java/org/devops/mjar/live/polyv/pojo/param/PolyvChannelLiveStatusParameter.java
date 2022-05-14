package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：查询批量频道状态
 */
@Data
public class PolyvChannelLiveStatusParameter extends AppSignBean {

    /**
     * 用英文逗号隔开的频道号，如：10000,100001 最多20个
     */
    private String channelIds;


    public static final class PolyvChannelLiveStatusParameterBuilder extends ParameterBuilder<PolyvChannelLiveStatusParameter> {
        private PolyvChannelLiveStatusParameter polyvChannelLiveStatusParameter;

        private PolyvChannelLiveStatusParameterBuilder() {
            polyvChannelLiveStatusParameter = new PolyvChannelLiveStatusParameter();
        }

        public static PolyvChannelLiveStatusParameterBuilder aPolyvChannelLiveStatusParameter() {
            return new PolyvChannelLiveStatusParameterBuilder();
        }

        public PolyvChannelLiveStatusParameterBuilder withChannelIds(String channelIds) {
            polyvChannelLiveStatusParameter.setChannelIds(channelIds);
            return this;
        }

        @Override
        public PolyvChannelLiveStatusParameter build() {
            return polyvChannelLiveStatusParameter;
        }
    }
}
