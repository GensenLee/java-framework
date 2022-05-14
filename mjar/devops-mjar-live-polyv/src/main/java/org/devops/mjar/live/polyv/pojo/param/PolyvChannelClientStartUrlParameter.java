package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/7/14 15:56
 * @description：主持人客户端链接
 */
@Data
public class PolyvChannelClientStartUrlParameter extends ChannelSignBean {

    /**
     * 密码
     */
    private String password;

    /**
     * 账号
     */
    @VerifyField(ignore = true)
    private String account;


    /**
     * 主持人生成链接用
     */
    public static final class PolyvChannelClientHostStartUrlParameterBuilder extends ParameterBuilder<PolyvChannelClientStartUrlParameter>{
        private PolyvChannelClientStartUrlParameter polyvChannelClientStartUrlParameter;

        private PolyvChannelClientHostStartUrlParameterBuilder() {
            polyvChannelClientStartUrlParameter = new PolyvChannelClientStartUrlParameter();
        }

        public static PolyvChannelClientHostStartUrlParameterBuilder aPolyvChannelClientStartUrlParameter() {
            return new PolyvChannelClientHostStartUrlParameterBuilder();
        }

        public PolyvChannelClientHostStartUrlParameterBuilder withPassword(String password) {
            polyvChannelClientStartUrlParameter.setPassword(password);
            return this;
        }

        @Override
        public PolyvChannelClientStartUrlParameter build() {
            return polyvChannelClientStartUrlParameter;
        }
    }

    /**
     * 嘉宾生成链接用
     */
    public static final class PolyvChannelClientGuestStartUrlParameterBuilder extends ParameterBuilder<PolyvChannelClientStartUrlParameter>{
        private PolyvChannelClientStartUrlParameter polyvChannelClientStartUrlParameter;

        private PolyvChannelClientGuestStartUrlParameterBuilder() {
            polyvChannelClientStartUrlParameter = new PolyvChannelClientStartUrlParameter();
        }

        public static PolyvChannelClientGuestStartUrlParameterBuilder aPolyvChannelClientStartUrlParameter() {
            return new PolyvChannelClientGuestStartUrlParameterBuilder();
        }

        public PolyvChannelClientGuestStartUrlParameterBuilder withPassword(String password) {
            polyvChannelClientStartUrlParameter.setPassword(password);
            return this;
        }

        public PolyvChannelClientGuestStartUrlParameterBuilder withAccount(String account) {
            polyvChannelClientStartUrlParameter.setAccount(account);
            return this;
        }

        @Override
        public PolyvChannelClientStartUrlParameter build() {
            if (StringUtil.isEmpty(polyvChannelClientStartUrlParameter.getAccount())) {
                throw new CommonRuntimeException(403, "account 不能为空");
            }
            return polyvChannelClientStartUrlParameter;
        }
    }

}
