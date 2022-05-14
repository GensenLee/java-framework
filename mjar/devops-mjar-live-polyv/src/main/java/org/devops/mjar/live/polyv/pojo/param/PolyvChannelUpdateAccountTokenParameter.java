package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/3/5 14:08
 * @description：设置子频道单点登陆token
 */

@Data
public class PolyvChannelUpdateAccountTokenParameter extends ChannelSignBean {

    /**
     *  "唯一的字符串"
     */
    private String token;

    /**
     * 子频道号
     */
    private String accountId;

    public static final class PolyvChannelUpdateAccountTokenParameterBuilder extends ParameterBuilder<PolyvChannelUpdateAccountTokenParameter> {
        private PolyvChannelUpdateAccountTokenParameter polyvChannelUpdateAccountTokenParameter;

        private PolyvChannelUpdateAccountTokenParameterBuilder() {
            polyvChannelUpdateAccountTokenParameter = new PolyvChannelUpdateAccountTokenParameter();
        }

        public static PolyvChannelUpdateAccountTokenParameterBuilder aPolyvChannelAccountTokenSetParameter() {
            return new PolyvChannelUpdateAccountTokenParameterBuilder();
        }

        public PolyvChannelUpdateAccountTokenParameterBuilder withToken(String token) {
            polyvChannelUpdateAccountTokenParameter.setToken(token);
            return this;
        }

        public PolyvChannelUpdateAccountTokenParameterBuilder withAccountId(String accountId) {
            polyvChannelUpdateAccountTokenParameter.setAccountId(accountId);
            return this;
        }

        @Override
        public PolyvChannelUpdateAccountTokenParameter build() {
            return polyvChannelUpdateAccountTokenParameter;
        }
    }
}
