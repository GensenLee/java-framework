package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：删除频道单条聊天记录
 */
@Data
public class PolyvChannelChatDeleteParameter extends ChannelSignBean {

    /**
     * 	聊天记录对应的id
     */
    private String id;


    public static final class PolyvChannelChatDeleteParameterBuilder extends ParameterBuilder<PolyvChannelChatDeleteParameter> {
        private PolyvChannelChatDeleteParameter polyvChannelChatDeleteParameter;

        private PolyvChannelChatDeleteParameterBuilder() {
            polyvChannelChatDeleteParameter = new PolyvChannelChatDeleteParameter();
        }

        public static PolyvChannelChatDeleteParameterBuilder aPolyvChannelChatDeleteParameter() {
            return new PolyvChannelChatDeleteParameterBuilder();
        }

        public PolyvChannelChatDeleteParameterBuilder withId(String id) {
            polyvChannelChatDeleteParameter.setId(id);
            return this;
        }

        @Override
        public PolyvChannelChatDeleteParameter build() {
            return polyvChannelChatDeleteParameter;
        }
    }
}
