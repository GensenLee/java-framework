package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fangzy
 * @description：修改管理员身份信息
 */
@Data
public class PolyvChannelUpdateAdminInfoParameter extends ChannelSignBean {

    /**
     * 管理员昵称，长度不能超过8
     */
    private String nickname;

    /**
     * 管理员头衔，长度不能超过4
     */
    private String actor;

    /**
     * 管理员头像，支持jpg、jpeg、png三种格式，大小不能超过2Mb
     */
    @SignIgnore
    private MultipartFile avatar;


    public static final class PolyvChannelUpdateAdminInfoParameterBuilder extends ParameterBuilder<PolyvChannelUpdateAdminInfoParameter> {
        private PolyvChannelUpdateAdminInfoParameter polyvChannelUpdateAdminInfoParameter;

        private PolyvChannelUpdateAdminInfoParameterBuilder() {
            polyvChannelUpdateAdminInfoParameter = new PolyvChannelUpdateAdminInfoParameter();
        }

        public static PolyvChannelUpdateAdminInfoParameterBuilder aPolyvChannelUpdateAdminInfoParameter() {
            return new PolyvChannelUpdateAdminInfoParameterBuilder();
        }

        public PolyvChannelUpdateAdminInfoParameterBuilder withNickname(String nickname) {
            polyvChannelUpdateAdminInfoParameter.setNickname(nickname);
            return this;
        }

        public PolyvChannelUpdateAdminInfoParameterBuilder withActor(String actor) {
            polyvChannelUpdateAdminInfoParameter.setActor(actor);
            return this;
        }

        public PolyvChannelUpdateAdminInfoParameterBuilder withAvatar(MultipartFile avatar) {
            polyvChannelUpdateAdminInfoParameter.setAvatar(avatar);
            return this;
        }

        @Override
        public PolyvChannelUpdateAdminInfoParameter build() {
            return polyvChannelUpdateAdminInfoParameter;
        }
    }
}
