package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/3/1 14:44
 * @description：子频道更新
 */
@Data
public class PolyvChannelUpdateAccountParameter extends ChannelSignBean {

    /**
     * 子频道号
     */
    private String account;

    /**
     * 昵称
     */
    @VerifyField(ignore = true)
    private String nickname;

    /**
     * 头像
     */
    @VerifyField(ignore = true)
    private String avatar;

    /**
     * 头衔
     */
    @VerifyField(ignore = true)
    private String actor;

    /**
     *  子频道密码
     */
    @VerifyField(ignore = true)
    private String password;

    @VerifyField(ignore = true)
    private String pageTurnEnabled;

    @VerifyField(ignore = true)
    private String notifyEnabled;

    public static final class PolyvChannelUpdateAccountParameterBuilder extends ParameterBuilder<PolyvChannelUpdateAccountParameter> {
        private PolyvChannelUpdateAccountParameter polyvChannelUpdateAccountParameter;

        private PolyvChannelUpdateAccountParameterBuilder() {
            polyvChannelUpdateAccountParameter = new PolyvChannelUpdateAccountParameter();
        }

        public static PolyvChannelUpdateAccountParameterBuilder aPolyvChannelUpdateAccountParameter() {
            return new PolyvChannelUpdateAccountParameterBuilder();
        }

        public PolyvChannelUpdateAccountParameterBuilder withAccount(String account) {
            polyvChannelUpdateAccountParameter.setAccount(account);
            return this;
        }

        public PolyvChannelUpdateAccountParameterBuilder withNickname(String nickname) {
            polyvChannelUpdateAccountParameter.setNickname(nickname);
            return this;
        }

        public PolyvChannelUpdateAccountParameterBuilder withAvatar(String avatar) {
            polyvChannelUpdateAccountParameter.setAvatar(avatar);
            return this;
        }

        public PolyvChannelUpdateAccountParameterBuilder withPageTurnEnabled(EnableSetting enabled) {
            polyvChannelUpdateAccountParameter.setPageTurnEnabled(enabled.getValue());
            return this;
        }

        public PolyvChannelUpdateAccountParameterBuilder withNotyfyEnabled(EnableSetting enabled) {
            polyvChannelUpdateAccountParameter.setNotifyEnabled(enabled.getValue());
            return this;
        }

        public PolyvChannelUpdateAccountParameterBuilder withActor(String actor) {
            polyvChannelUpdateAccountParameter.setActor(actor);
            return this;
        }

        public PolyvChannelUpdateAccountParameterBuilder withPassword(String password) {
            polyvChannelUpdateAccountParameter.setPassword(password);
            return this;
        }

        @Override
        public PolyvChannelUpdateAccountParameter build() {
            return polyvChannelUpdateAccountParameter;
        }
    }
}
