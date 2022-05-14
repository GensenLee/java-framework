package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/27 09:44
 * @description：修改设置授权认证URL
 */

@Data
public class PolyvChannelUpdateAuthUrlParameter extends ChannelSignBean {

    /**
     * 授权认证url，为空时清除设置
     */
    @VerifyField( ignore = true )
    private String url;


    public static final class PolyvChannelUpdateAuthUrlParameterBuilder extends ParameterBuilder<PolyvChannelUpdateAuthUrlParameter> {
        private PolyvChannelUpdateAuthUrlParameter polyvChannelUpdateAuthUrlParameter;

        private PolyvChannelUpdateAuthUrlParameterBuilder() {
            polyvChannelUpdateAuthUrlParameter = new PolyvChannelUpdateAuthUrlParameter();
        }
        public static PolyvChannelUpdateAuthUrlParameterBuilder aPolyvChannelUpdateAuthUrlParameter() {
            return new PolyvChannelUpdateAuthUrlParameterBuilder();
        }
        public PolyvChannelUpdateAuthUrlParameterBuilder withUrl(String url) {
            polyvChannelUpdateAuthUrlParameter.setUrl(url);
            return this;
        }
        @Override
        public PolyvChannelUpdateAuthUrlParameter build() {
            return polyvChannelUpdateAuthUrlParameter;
        }
    }
}
