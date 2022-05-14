package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/29 9:46
 * @description：查询频道的功能开关状态
 */
@Data
public class PolyvChannelSearchSwitchParameter extends ChannelSignBean {

    public static final class PolyvChannelSearchSwitchParameterBuilder extends ParameterBuilder<PolyvChannelSearchSwitchParameter> {
        private PolyvChannelSearchSwitchParameter polyvChannelSearchSwitchParameter;

        private PolyvChannelSearchSwitchParameterBuilder() {
            polyvChannelSearchSwitchParameter = new PolyvChannelSearchSwitchParameter();
        }

        public static PolyvChannelSearchSwitchParameterBuilder aPolyvChannelSearchSwitchParameter() {
            return new PolyvChannelSearchSwitchParameterBuilder();
        }

        @Override
        public PolyvChannelSearchSwitchParameter build() {
            return polyvChannelSearchSwitchParameter;
        }
    }
}
