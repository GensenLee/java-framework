package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：禁言IP
 */
@Data
public class PolyvChannelAddBannedIpParameter extends ChannelSignBean {

    /**
     * 禁言ip
     */
    private String ip;


    public static final class PolyvChannelAddBannedIpParameterBuilder extends ParameterBuilder<PolyvChannelAddBannedIpParameter> {
        private PolyvChannelAddBannedIpParameter polyvChannelAddBannedIpParameter;

        private PolyvChannelAddBannedIpParameterBuilder() {
            polyvChannelAddBannedIpParameter = new PolyvChannelAddBannedIpParameter();
        }

        public static PolyvChannelAddBannedIpParameterBuilder aPolyvChannelAddBannedIpParameter() {
            return new PolyvChannelAddBannedIpParameterBuilder();
        }

        public PolyvChannelAddBannedIpParameterBuilder withIp(String ip) {
            polyvChannelAddBannedIpParameter.setIp(ip);
            return this;
        }

        @Override
        public PolyvChannelAddBannedIpParameter build() {
            return polyvChannelAddBannedIpParameter;
        }
    }
}
