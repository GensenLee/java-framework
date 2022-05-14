package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/21 17:54
 * @description：授权直播请求
 */
@Data
public class PolyvChannelDirectPlayUrlParameter extends PolyvChannelParameter {

    /**
     * 独立授权key
     */
    @SignIgnore
    private String secretKey;

    /**
     * 观众ID，需要保证唯一性
     */
    @SignIgnore
    private String viewerid;

    /**
     * 观众昵称
     */
    @SignIgnore
    private String nickname;

    /**
     * 观众头像的url地址
     */
    @SignIgnore
    private String avatar;

    /**
     * 观看域名
     */
    @SignIgnore
    @VerifyField(ignore = true)
    private String baseHost;


    public static final class PolyvChannelDirectPlayUrlParameterBuilder extends ParameterBuilder<PolyvChannelDirectPlayUrlParameter> {
        private PolyvChannelDirectPlayUrlParameter polyvChannelDirectPlayUrlParameter;

        private PolyvChannelDirectPlayUrlParameterBuilder() {
            polyvChannelDirectPlayUrlParameter = new PolyvChannelDirectPlayUrlParameter();
        }

        public static PolyvChannelDirectPlayUrlParameterBuilder aPolyvChannelDirectPlayUrlParameter() {
            return new PolyvChannelDirectPlayUrlParameterBuilder();
        }

        public PolyvChannelDirectPlayUrlParameterBuilder withSecretKey(String secretKey) {
            polyvChannelDirectPlayUrlParameter.setSecretKey(secretKey);
            return this;
        }

        public PolyvChannelDirectPlayUrlParameterBuilder withBaseHost(String host) {
            polyvChannelDirectPlayUrlParameter.setBaseHost(host);
            return this;
        }

        public PolyvChannelDirectPlayUrlParameterBuilder withUserid(String userid) {
            polyvChannelDirectPlayUrlParameter.setViewerid(userid);
            return this;
        }

        public PolyvChannelDirectPlayUrlParameterBuilder withNickname(String nickname) {
            polyvChannelDirectPlayUrlParameter.setNickname(nickname);
            return this;
        }

        public PolyvChannelDirectPlayUrlParameterBuilder withAvatar(String avatar) {
            polyvChannelDirectPlayUrlParameter.setAvatar(avatar);
            return this;
        }

        public PolyvChannelDirectPlayUrlParameterBuilder withChannelId(String channelId) {
            polyvChannelDirectPlayUrlParameter.setChannelId(channelId);
            return this;
        }

        @Override
        public PolyvChannelDirectPlayUrlParameter build() {
            return polyvChannelDirectPlayUrlParameter;
        }
    }
}

