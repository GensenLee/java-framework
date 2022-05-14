package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/07/26 17:04
 * @description：查询频道商品库开关状态
 */
@Data
public class PolyvChannelCheckProductEnableParameter extends ChannelSignBean {


    public static final class PolyvChannelCheckProductEnableParameterBuilder extends ParameterBuilder<PolyvChannelCheckProductEnableParameter> {
        private PolyvChannelCheckProductEnableParameter polyvChannelCheckProductEnableParameter;

        private PolyvChannelCheckProductEnableParameterBuilder() {
            polyvChannelCheckProductEnableParameter = new PolyvChannelCheckProductEnableParameter();
        }

        public static PolyvChannelCheckProductEnableParameterBuilder aPolyvChannelCheckProductEnableParameter() {
            return new PolyvChannelCheckProductEnableParameterBuilder();
        }

        @Override
        public PolyvChannelCheckProductEnableParameter build() {
            return polyvChannelCheckProductEnableParameter;
        }
    }
}
