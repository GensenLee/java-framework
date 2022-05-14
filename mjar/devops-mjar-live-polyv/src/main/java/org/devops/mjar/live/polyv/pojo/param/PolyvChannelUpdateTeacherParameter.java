package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/11/3 15:36
 * @description：讲师更新
 */
@Data
public class PolyvChannelUpdateTeacherParameter extends ChannelSignBean {

    /**
     * 讲师昵称
     */
    private String nickname;

    /**
     *  讲师头衔
     */
    private String actor;

    /**
     * 讲师头像
     */
    @VerifyField(ignore = true)
    private String avatar;

    /*
    频道密码
     */
    private String passwd;


    public static final class PolyvChannelUpdateTeacherParameterBuilder extends ParameterBuilder<PolyvChannelUpdateTeacherParameter> {
        private PolyvChannelUpdateTeacherParameter polyvChannelUpdateTeacherParameter;

        private PolyvChannelUpdateTeacherParameterBuilder() {
            polyvChannelUpdateTeacherParameter = new PolyvChannelUpdateTeacherParameter();
        }

        public static PolyvChannelUpdateTeacherParameterBuilder aPolyvChannelUpdateTeacherParameter() {
            return new PolyvChannelUpdateTeacherParameterBuilder();
        }

        public PolyvChannelUpdateTeacherParameterBuilder withNickname(String nickname) {
            polyvChannelUpdateTeacherParameter.setNickname(nickname);
            return this;
        }

        public PolyvChannelUpdateTeacherParameterBuilder withActor(String actor) {
            polyvChannelUpdateTeacherParameter.setActor(actor);
            return this;
        }

        public PolyvChannelUpdateTeacherParameterBuilder withAvatar(String avatar) {
            polyvChannelUpdateTeacherParameter.setAvatar(avatar);
            return this;
        }

        public PolyvChannelUpdateTeacherParameterBuilder withPasswd(String password) {
            polyvChannelUpdateTeacherParameter.setPasswd(password);
            return this;
        }



        @Override
        public PolyvChannelUpdateTeacherParameter build() {
            return polyvChannelUpdateTeacherParameter;
        }
    }
}
