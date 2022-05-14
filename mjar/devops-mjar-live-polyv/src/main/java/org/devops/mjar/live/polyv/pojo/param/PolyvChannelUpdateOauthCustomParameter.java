package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/27 12:44
 * @description：修改频道自定义授权设置
 */

@Data
public class PolyvChannelUpdateOauthCustomParameter extends ChannelSignBean {

    /**
     *自定义授权地址
     */
    private String externalUri;

    public static final class PolyvChannelUpdateOauthCustomParameterBuilder extends ParameterBuilder<PolyvChannelUpdateOauthCustomParameter> {
        private PolyvChannelUpdateOauthCustomParameter polyvChannelUpdateAuthExternalParameter;

        private PolyvChannelUpdateOauthCustomParameterBuilder() {
            polyvChannelUpdateAuthExternalParameter = new PolyvChannelUpdateOauthCustomParameter();
        }

        public static PolyvChannelUpdateOauthCustomParameterBuilder aPolyvChannelUpdateOauthCustomParameter() {
            return new PolyvChannelUpdateOauthCustomParameterBuilder();
        }

        public PolyvChannelUpdateOauthCustomParameterBuilder withExternalUri(String externalUri) {
            polyvChannelUpdateAuthExternalParameter.setExternalUri(externalUri);
            return this;
        }

        @Override
        public PolyvChannelUpdateOauthCustomParameter build() {
            return polyvChannelUpdateAuthExternalParameter;
        }
    }
}
