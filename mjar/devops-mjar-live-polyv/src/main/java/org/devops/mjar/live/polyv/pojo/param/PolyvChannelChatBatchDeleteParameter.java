package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：批量删除频道多条聊天记录
 */
@Data
public class PolyvChannelChatBatchDeleteParameter extends ChannelSignBean {

    /**
     * 	聊天记录对应的id(多个id使用英文逗号隔开)
     */
    private String ids;


    public static final class PolyvChannelChatBatchDeleteParameterBuilder extends ParameterBuilder<PolyvChannelChatBatchDeleteParameter> {
        private PolyvChannelChatBatchDeleteParameter polyvChannelChatBatchDeleteParameter;

        private PolyvChannelChatBatchDeleteParameterBuilder() {
            polyvChannelChatBatchDeleteParameter = new PolyvChannelChatBatchDeleteParameter();
        }

        public static PolyvChannelChatBatchDeleteParameterBuilder aPolyvChannelChatBatchDeleteParameter() {
            return new PolyvChannelChatBatchDeleteParameterBuilder();
        }

        public PolyvChannelChatBatchDeleteParameterBuilder withIds(String ids) {
            polyvChannelChatBatchDeleteParameter.setIds(ids);
            return this;
        }

        @Override
        public PolyvChannelChatBatchDeleteParameter build() {
            return polyvChannelChatBatchDeleteParameter;
        }
    }
}
