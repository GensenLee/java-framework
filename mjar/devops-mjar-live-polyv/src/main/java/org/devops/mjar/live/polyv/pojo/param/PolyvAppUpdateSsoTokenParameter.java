package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：账号token设置
 */
@Data
public class PolyvAppUpdateSsoTokenParameter extends AppSignBean {

    /**
     * 通过频道token 或子频道token设置的token串，10秒内且一次验证有效
     */
    private String token;


    public static final class PolyvAppUpdateSsoTokenParameterBuilder extends ParameterBuilder<PolyvAppUpdateSsoTokenParameter> {
        private PolyvAppUpdateSsoTokenParameter polyvAppUpdateSsoTokenParameter;

        private PolyvAppUpdateSsoTokenParameterBuilder() {
            polyvAppUpdateSsoTokenParameter = new PolyvAppUpdateSsoTokenParameter();
        }

        public static PolyvAppUpdateSsoTokenParameterBuilder aPolyvAppUpdateSsoTokenParameter() {
            return new PolyvAppUpdateSsoTokenParameterBuilder();
        }

        public PolyvAppUpdateSsoTokenParameterBuilder withToken(String token) {
            polyvAppUpdateSsoTokenParameter.setToken(token);
            return this;
        }

        @Override
        public PolyvAppUpdateSsoTokenParameter build() {
            return polyvAppUpdateSsoTokenParameter;
        }
    }
}
