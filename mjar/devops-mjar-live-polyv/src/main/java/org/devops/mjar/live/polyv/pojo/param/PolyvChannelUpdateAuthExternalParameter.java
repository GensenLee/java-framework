package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/27 12:44
 * @description：修改频道外部授权设置
 */

@Data
public class PolyvChannelUpdateAuthExternalParameter extends ChannelSignBean {

    /**
     * 获取用户信息接口地址
     */
    private String externalUri;

    public static final class PolyvChannelUpdateAuthExternalParameterBuilder extends ParameterBuilder<PolyvChannelUpdateAuthExternalParameter> {
        private PolyvChannelUpdateAuthExternalParameter polyvChannelUpdateAuthExternalParameter;

        private PolyvChannelUpdateAuthExternalParameterBuilder() {
            polyvChannelUpdateAuthExternalParameter = new PolyvChannelUpdateAuthExternalParameter();
        }

        public static PolyvChannelUpdateAuthExternalParameterBuilder aPolyvChannelUpdateAuthExternalParameter() {
            return new PolyvChannelUpdateAuthExternalParameterBuilder();
        }

        public PolyvChannelUpdateAuthExternalParameterBuilder withExternalUri(String externalUri) {
            polyvChannelUpdateAuthExternalParameter.setExternalUri(externalUri);
            return this;
        }

        @Override
        public PolyvChannelUpdateAuthExternalParameter build() {
            return polyvChannelUpdateAuthExternalParameter;
        }
    }
}
