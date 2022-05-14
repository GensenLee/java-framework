package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/28 11:54
 * @description：查询频道微信分享信息
 */

@Data
public class PolyvChannelSearchWeChatShareParameter extends ChannelSignBean {


    public static final class PolyvChannelSearchWeChatShareParameterBuilder extends ParameterBuilder<PolyvChannelSearchWeChatShareParameter> {
        private PolyvChannelSearchWeChatShareParameter polyvChannelSearchWeChatShareParameter;

        private PolyvChannelSearchWeChatShareParameterBuilder() {
            polyvChannelSearchWeChatShareParameter = new PolyvChannelSearchWeChatShareParameter();
        }

        public static PolyvChannelSearchWeChatShareParameterBuilder aPolyvChannelSearchWeChatShareParameter() {
            return new PolyvChannelSearchWeChatShareParameterBuilder();
        }

        public PolyvChannelSearchWeChatShareParameter build() {
            return polyvChannelSearchWeChatShareParameter;
        }
    }
}
