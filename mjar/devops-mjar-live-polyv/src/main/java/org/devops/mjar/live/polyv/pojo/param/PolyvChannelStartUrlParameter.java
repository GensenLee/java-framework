package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/07/13 17:54
 * @description：主持人开播链接
 */
@Data
public class PolyvChannelStartUrlParameter extends PolyvChannelParameter {

    /**
     * 开播域名
     */
    @SignIgnore
    @VerifyField(ignore = true)
    private String baseHost;


    public static final class PolyvChannelStartUrlParameterBuilder extends ParameterBuilder<PolyvChannelStartUrlParameter> {
        private PolyvChannelStartUrlParameter polyvChannelStartUrlParameter;

        private PolyvChannelStartUrlParameterBuilder() {
            polyvChannelStartUrlParameter = new PolyvChannelStartUrlParameter();
        }

        public static PolyvChannelStartUrlParameterBuilder aPolyvChannelStartUrlParameter() {
            return new PolyvChannelStartUrlParameterBuilder();
        }


        public PolyvChannelStartUrlParameterBuilder withBaseHost(String host) {
            polyvChannelStartUrlParameter.setBaseHost(host);
            return this;
        }


        @Override
        public PolyvChannelStartUrlParameter build() {
            return polyvChannelStartUrlParameter;
        }
    }
}

