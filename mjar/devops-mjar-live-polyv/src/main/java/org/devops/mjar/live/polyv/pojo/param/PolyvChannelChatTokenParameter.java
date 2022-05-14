package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/3/2 17:48
 * @description：查询授权和连麦的token
 */
@Data
public class PolyvChannelChatTokenParameter extends ChannelSignBean {

    /**
     *  观看者用户id,接口文档中的userId
     */
    private String userId;

    /**
     *  角色，值有：teacher、admin、guest、assistant、viewer
     */
    private String role;

    /**
     * 观看来源
     */
    @VerifyField(ignore = true)
    private String origin;


    public static final class PolyvChannelChatTokenParameterBuilder extends ParameterBuilder<PolyvChannelChatTokenParameter> {
        private PolyvChannelChatTokenParameter polyvChannelChatTokenParameter;

        private PolyvChannelChatTokenParameterBuilder() {
            polyvChannelChatTokenParameter = new PolyvChannelChatTokenParameter();
        }

        public static PolyvChannelChatTokenParameterBuilder aPolyvChannelChatTokenParameter() {
            return new PolyvChannelChatTokenParameterBuilder();
        }

        public PolyvChannelChatTokenParameterBuilder withUserId(String userId) {
            polyvChannelChatTokenParameter.setUserId(userId);
            return this;
        }

        public PolyvChannelChatTokenParameterBuilder withRole(String role) {
            polyvChannelChatTokenParameter.setRole(role);
            return this;
        }

        public PolyvChannelChatTokenParameterBuilder withOrigin(String origin) {
            polyvChannelChatTokenParameter.setOrigin(origin);
            return this;
        }

        @Override
        public PolyvChannelChatTokenParameter build() {
            return polyvChannelChatTokenParameter;
        }
    }
}
