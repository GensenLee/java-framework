package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.ChannelAccountRole;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/11/3 15:36
 * @description：子频道创建
 */

@Data
public class PolyvChannelCreateAccountParameter extends ChannelSignBean {

    /**
     * 默认不传为助教
     * assistant：助教
     * guest：嘉宾（只支持三分屏场景的频道）
     */
    @VerifyField(ignore = true)
    private String role;

    /**
     *  创建的助教或嘉宾昵称
     */
    @VerifyField(ignore = true)
    private String nickname;

    /**
     *创建的助教或嘉宾头衔
     */
    @VerifyField(ignore = true)
    private String actor;

    /**
     *   创建的助教或嘉宾头像
     */
    @VerifyField(ignore = true)
    private String avatar;


    public static final class PolyvChannelCreateAccountParameterBuilder extends ParameterBuilder<PolyvChannelCreateAccountParameter> {
        private PolyvChannelCreateAccountParameter polyvChannelCreateAccountParameter;

        private PolyvChannelCreateAccountParameterBuilder() {
            polyvChannelCreateAccountParameter = new PolyvChannelCreateAccountParameter();
        }

        public static PolyvChannelCreateAccountParameterBuilder aPolyvChannelCreateAccountParameter() {
            return new PolyvChannelCreateAccountParameterBuilder();
        }

        public PolyvChannelCreateAccountParameterBuilder withRole(ChannelAccountRole role) {
            if (role == null) {
                return this;
            }
            polyvChannelCreateAccountParameter.setRole(role.getCode());
            return this;
        }

        public PolyvChannelCreateAccountParameterBuilder withNickname(String nickname) {
            polyvChannelCreateAccountParameter.setNickname(nickname);
            return this;
        }

        public PolyvChannelCreateAccountParameterBuilder withActor(String actor) {
            polyvChannelCreateAccountParameter.setActor(actor);
            return this;
        }

        public PolyvChannelCreateAccountParameterBuilder withAvatar(String avatar) {
            polyvChannelCreateAccountParameter.setAvatar(avatar);
            return this;
        }

        @Override
        public PolyvChannelCreateAccountParameter build() {
            return polyvChannelCreateAccountParameter;
        }
    }
}
