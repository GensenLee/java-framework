package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：修改频道密码
 */
@Data
public class PolyvChannelUpdatePasswdParameter extends AppSignBean {
    /**
     * 修改的密码
     */
    private String passwd;

    /**
     * 频道号，请留意，如果该参数为空，会对该用户所有的频道进行修改
     */
    @VerifyField(ignore = true)
    private String channelId;


    public static final class PolyvChannelUpdatePasswdParameterBuilder extends ParameterBuilder<PolyvChannelUpdatePasswdParameter> {
        private PolyvChannelUpdatePasswdParameter polyvChannelUpdatePasswdParameter;

        private PolyvChannelUpdatePasswdParameterBuilder() {
            polyvChannelUpdatePasswdParameter = new PolyvChannelUpdatePasswdParameter();
        }

        public static PolyvChannelUpdatePasswdParameterBuilder aPolyvChannelUpdatePasswdParameter() {
            return new PolyvChannelUpdatePasswdParameterBuilder();
        }

        public PolyvChannelUpdatePasswdParameterBuilder withPasswd(String passwd) {
            polyvChannelUpdatePasswdParameter.setPasswd(passwd);
            return this;
        }

        public PolyvChannelUpdatePasswdParameterBuilder withChannelId(String channelId) {
            polyvChannelUpdatePasswdParameter.setChannelId(channelId);
            return this;
        }

        @Override
        public PolyvChannelUpdatePasswdParameter build() {
            return polyvChannelUpdatePasswdParameter;
        }
    }
}
