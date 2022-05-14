package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/30 10:04
 * @description：频道删除
 */
@Data
public class PolyvChannelDeleteParameter extends AppSignBean {

    /**
     * 频道号
     */
    @SignIgnore
    private String channelId;


    public static final class PolyvChannelDeleteReqParameterBuilder extends ParameterBuilder<PolyvChannelDeleteParameter> {
        private PolyvChannelDeleteParameter polyvChannelDeleteParameter;

        private PolyvChannelDeleteReqParameterBuilder() {
            polyvChannelDeleteParameter = new PolyvChannelDeleteParameter();
        }

        public static PolyvChannelDeleteReqParameterBuilder aPolyvChannelDeleteParameter() {
            return new PolyvChannelDeleteReqParameterBuilder();
        }

        public PolyvChannelDeleteReqParameterBuilder withChannelId(String channelId) {
            polyvChannelDeleteParameter.setChannelId(channelId);
            return this;
        }

        @Override
        public  PolyvChannelDeleteParameter build() {
            return polyvChannelDeleteParameter;
        }
    }
}
