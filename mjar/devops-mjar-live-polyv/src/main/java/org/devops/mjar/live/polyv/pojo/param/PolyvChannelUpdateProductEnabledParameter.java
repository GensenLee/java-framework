package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/29 18:45
 * @description：修改频道商品库开关状态
 */
@Data
public class PolyvChannelUpdateProductEnabledParameter extends ChannelSignBean {

    /**
     * 开关状态
     * Y：开启
     * N：关闭
     */
    private String enabled;


    public static final class PolyvChannelUpdateProductEnabledParameterBuilder extends ParameterBuilder<PolyvChannelUpdateProductEnabledParameter> {
        private PolyvChannelUpdateProductEnabledParameter polyvChannelUpdateProductEnabledParameter;

        private PolyvChannelUpdateProductEnabledParameterBuilder() {
            polyvChannelUpdateProductEnabledParameter = new PolyvChannelUpdateProductEnabledParameter();
        }

        public static PolyvChannelUpdateProductEnabledParameterBuilder aPolyvChannelUpdateProductEnabledParameter() {
            return new PolyvChannelUpdateProductEnabledParameterBuilder();
        }

        public PolyvChannelUpdateProductEnabledParameterBuilder withEnabled(EnableSetting enabled) {
            polyvChannelUpdateProductEnabledParameter.setEnabled(enabled.getValue());
            return this;
        }

        @Override
        public PolyvChannelUpdateProductEnabledParameter build() {
            return polyvChannelUpdateProductEnabledParameter;
        }
    }
}
