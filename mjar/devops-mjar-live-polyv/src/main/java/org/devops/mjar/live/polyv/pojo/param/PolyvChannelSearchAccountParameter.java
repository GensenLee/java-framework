package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/23 10:20
 * @description：查询频道号下所有子频道信息
 */

@Data
public class PolyvChannelSearchAccountParameter extends ChannelSignBean {


    public static final class PolyvChannelSearchAccountParameterBuilder extends ParameterBuilder<PolyvChannelSearchAccountParameter> {
        private PolyvChannelSearchAccountParameter polyvChannelSearchAccountParameter;

        private PolyvChannelSearchAccountParameterBuilder() {
            polyvChannelSearchAccountParameter = new PolyvChannelSearchAccountParameter();
        }

        public static PolyvChannelSearchAccountParameterBuilder aPolyvChannelSearchAccountParameter() {
            return new PolyvChannelSearchAccountParameterBuilder();
        }

        @Override
        public PolyvChannelSearchAccountParameter build() {
            return polyvChannelSearchAccountParameter;
        }
    }
}
