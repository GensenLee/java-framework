package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/07/23 11:55
 * @description:修改频道微信分享信息
 */
@Data
public class PolyvChannelUpdateWeChatShareParameter extends ChannelSignBean {

    /**
     * 微信分享的标题（30字符内），默认为：频道标题
     */
    @VerifyField(ignore = true)
    private String weixinShareTitle;

    /**
     * 微信分享的描述（120字符内），默认为：正在直播，非常不错喔！快来看看吧!
     */
    @VerifyField(ignore = true)
    private String weixinShareDesc;

    public static final class PolyvChannelUpdateWeChatShareParameterBuilder extends ParameterBuilder<PolyvChannelUpdateWeChatShareParameter> {
        private PolyvChannelUpdateWeChatShareParameter polyvChannelUpdateWeChatShareParameter;

        private PolyvChannelUpdateWeChatShareParameterBuilder() {
            polyvChannelUpdateWeChatShareParameter = new PolyvChannelUpdateWeChatShareParameter();
        }

        public static PolyvChannelUpdateWeChatShareParameterBuilder aPolyvChannelUpdateWeChatShareParameter() {
            return new PolyvChannelUpdateWeChatShareParameterBuilder();
        }

        public PolyvChannelUpdateWeChatShareParameterBuilder withWeixinShareTitle(String weixinShareTitle) {
            polyvChannelUpdateWeChatShareParameter.setWeixinShareTitle(weixinShareTitle);
            return this;
        }

        public PolyvChannelUpdateWeChatShareParameterBuilder withWeixinShareDesc(String weixinShareDesc) {
            polyvChannelUpdateWeChatShareParameter.setWeixinShareDesc(weixinShareDesc);
            return this;
        }

        @Override
        public PolyvChannelUpdateWeChatShareParameter build() {
            return polyvChannelUpdateWeChatShareParameter;
        }
    }
}
