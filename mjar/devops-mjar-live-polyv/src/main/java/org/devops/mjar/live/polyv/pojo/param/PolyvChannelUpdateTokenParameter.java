package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/11/12 19:26
 * @description：频道token设置
 */
@Data
public class PolyvChannelUpdateTokenParameter extends ChannelSignBean {

    /**
     * 通过频道token 或子频道token设置的token串，10秒内且一次验证有效
     */
    private String token;


    public static final class PolyvChannelUpdateTokenParameterBuilder extends ParameterBuilder<PolyvChannelUpdateTokenParameter> {
        private PolyvChannelUpdateTokenParameter polyvChannelUpdateTokenParameter;

        private PolyvChannelUpdateTokenParameterBuilder() {
            polyvChannelUpdateTokenParameter = new PolyvChannelUpdateTokenParameter();
        }

        public static PolyvChannelUpdateTokenParameterBuilder aPolyvChannelUpdateTokenParameter() {
            return new PolyvChannelUpdateTokenParameterBuilder();
        }

        public PolyvChannelUpdateTokenParameterBuilder withToken(String token) {
            polyvChannelUpdateTokenParameter.setToken(token);
            return this;
        }

        @Override
        public PolyvChannelUpdateTokenParameter build() {
            return polyvChannelUpdateTokenParameter;
        }
    }
}
