package org.devops.mjar.live.polyv.pojo.param;

import lombok.Data;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;

/**
 * @author GENSEN
 * @date 2020/11/3 15:08
 * @description：子频道信息
 */
@Data
public class PolyvChannelAccountDetailParameter extends ChannelSignBean {

    /**
     * 子频道号
     */
    private String account;


    public static final class PolyvChannelAccountDetailParameterBuilder extends ParameterBuilder<PolyvChannelAccountDetailParameter> {
        private PolyvChannelAccountDetailParameter polyvChannelAccountDetailParameter;

        private PolyvChannelAccountDetailParameterBuilder() {
            polyvChannelAccountDetailParameter = new PolyvChannelAccountDetailParameter();
        }

        public static PolyvChannelAccountDetailParameterBuilder aPolyvChannelAccountDetailParameter() {
            return new PolyvChannelAccountDetailParameterBuilder();
        }

        public PolyvChannelAccountDetailParameterBuilder withAccount(String account) {
            polyvChannelAccountDetailParameter.setAccount(account);
            return this;
        }

        @Override
        public PolyvChannelAccountDetailParameter build() {
            return polyvChannelAccountDetailParameter;
        }
    }
}
