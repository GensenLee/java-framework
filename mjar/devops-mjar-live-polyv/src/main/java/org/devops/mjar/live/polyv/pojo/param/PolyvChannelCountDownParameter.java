package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：查询开始直播时间
 */

@Data
public class PolyvChannelCountDownParameter extends ChannelSignBean {

    public static final class PolyvChannelCountDownParameterBuilder extends ParameterBuilder<PolyvChannelCountDownParameter> {
        private PolyvChannelCountDownParameter polyvChannelCountDownParameter;

        private PolyvChannelCountDownParameterBuilder() {
            polyvChannelCountDownParameter = new PolyvChannelCountDownParameter();
        }

        public static PolyvChannelCountDownParameterBuilder aPolyvChannelCountDownParameter() {
            return new PolyvChannelCountDownParameterBuilder();
        }

        @Override
        public PolyvChannelCountDownParameter build() {
            return polyvChannelCountDownParameter;
        }
    }
}
