package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：
 */
@Data
public class PolyvChannelLoginParameter extends ChannelSignBean {

    /**
     * 通过账号token设置的token串，10秒内且一次验证有效
     */
    private String token;

    /**
     * 完成授权后重定向地址，使用url编码
     */
    @VerifyField(ignore = true)
    private String redirect;


    public static final class PolyvChannelLoginParameterBuilder extends ParameterBuilder<PolyvChannelLoginParameter> {
        private PolyvChannelLoginParameter polyvChannelLoginParameter;

        private PolyvChannelLoginParameterBuilder() {
            polyvChannelLoginParameter = new PolyvChannelLoginParameter();
        }

        public static PolyvChannelLoginParameterBuilder aPolyvChannelLoginParameter() {
            return new PolyvChannelLoginParameterBuilder();
        }

        public PolyvChannelLoginParameterBuilder withToken(String token) {
            polyvChannelLoginParameter.setToken(token);
            return this;
        }

        public PolyvChannelLoginParameterBuilder withRedirect(String redirect) {
            polyvChannelLoginParameter.setRedirect(redirect);
            return this;
        }

        @Override
        public PolyvChannelLoginParameter build() {
            return polyvChannelLoginParameter;
        }
    }
}
