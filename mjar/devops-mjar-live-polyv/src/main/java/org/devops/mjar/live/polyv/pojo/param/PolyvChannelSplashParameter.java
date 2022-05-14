package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：查询引导图
 */
@Data
public class PolyvChannelSplashParameter extends ChannelSignBean {

    public static final class PolyvChannelSplashParameterBuilder extends ParameterBuilder<PolyvChannelSplashParameter> {
        private PolyvChannelSplashParameter polyvChannelSplashParameter;

        private PolyvChannelSplashParameterBuilder() {
            polyvChannelSplashParameter = new PolyvChannelSplashParameter();
        }

        public static PolyvChannelSplashParameterBuilder aPolyvChannelSplashParameter() {
            return new PolyvChannelSplashParameterBuilder();
        }

        @Override
        public PolyvChannelSplashParameter build() {
            return polyvChannelSplashParameter;
        }
    }
}