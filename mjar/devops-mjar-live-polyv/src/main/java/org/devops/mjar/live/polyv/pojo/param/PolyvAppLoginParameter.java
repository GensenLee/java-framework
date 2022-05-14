package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：
 */
@Data
public class PolyvAppLoginParameter extends AppSignBean {

    /**
     * 通过账号token设置的token串，10秒内且一次验证有效
     */
    private String token;

    /**
     * 完成授权后重定向地址，使用url编码
     */
    @VerifyField(ignore = true)
    private String redirect;


    public static final class PolyvAppLoginParameterBuilder extends ParameterBuilder<PolyvAppLoginParameter> {
        private PolyvAppLoginParameter polyvAppLoginParameter;

        private PolyvAppLoginParameterBuilder() {
            polyvAppLoginParameter = new PolyvAppLoginParameter();
        }

        public static PolyvAppLoginParameterBuilder aPolyvAppLoginParameter() {
            return new PolyvAppLoginParameterBuilder();
        }

        public PolyvAppLoginParameterBuilder withToken(String token) {
            polyvAppLoginParameter.setToken(token);
            return this;
        }

        public PolyvAppLoginParameterBuilder withRedirect(String redirect) {
            polyvAppLoginParameter.setRedirect(redirect);
            return this;
        }

        @Override
        public PolyvAppLoginParameter build() {
            return polyvAppLoginParameter;
        }
    }
}
