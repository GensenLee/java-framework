package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/07/13 17:54
 * @description：嘉宾、助教链接请求
 */
@Data
public class PolyvChannelGuestPlayUrlParameter extends PolyvChannelParameter {
    /**
     * 嘉宾号
     */
    @SignIgnore
    private String account;

    /**
     * 开播域名
     */
    @SignIgnore
    @VerifyField(ignore = true)
    private String baseHost;


    public static final class PolyvChannelGuestPlayUrlParameterBuilder extends ParameterBuilder<PolyvChannelGuestPlayUrlParameter> {
        private PolyvChannelGuestPlayUrlParameter polyvChannelGuestPlayUrlParameter;

        private PolyvChannelGuestPlayUrlParameterBuilder() {
            polyvChannelGuestPlayUrlParameter = new PolyvChannelGuestPlayUrlParameter();
        }

        public static PolyvChannelGuestPlayUrlParameterBuilder aPolyvChannelGuestPlayUrlParameter() {
            return new PolyvChannelGuestPlayUrlParameterBuilder();
        }


        public PolyvChannelGuestPlayUrlParameterBuilder withBaseHost(String host) {
            polyvChannelGuestPlayUrlParameter.setBaseHost(host);
            return this;
        }


        public PolyvChannelGuestPlayUrlParameterBuilder withAccount(String account) {
            polyvChannelGuestPlayUrlParameter.setAccount(account);
            return this;
        }

        @Override
        public PolyvChannelGuestPlayUrlParameter build() {
            return polyvChannelGuestPlayUrlParameter;
        }
    }
}

