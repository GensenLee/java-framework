package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/3/2 17:30
 * @description：子频道删除
 */
@Data
public class PolyvChannelDeleteAccountParameter extends ChannelSignBean {

    /**
     * 子频道号
     */
    private String account;


    public static final class PolyvChannelDeleteAccountParameterBuilder extends ParameterBuilder<PolyvChannelDeleteAccountParameter> {
        private PolyvChannelDeleteAccountParameter polyvChannelDeleteAccountParameter;

        private PolyvChannelDeleteAccountParameterBuilder() {
            polyvChannelDeleteAccountParameter = new PolyvChannelDeleteAccountParameter();
        }

        public static PolyvChannelDeleteAccountParameterBuilder aPolyvChannelDeleteAccountParameter() {
            return new PolyvChannelDeleteAccountParameterBuilder();
        }

        public PolyvChannelDeleteAccountParameterBuilder withAccount(String account) {
            polyvChannelDeleteAccountParameter.setAccount(account);
            return this;
        }

        @Override
        public PolyvChannelDeleteAccountParameter build() {
            return polyvChannelDeleteAccountParameter;
        }
    }
}
