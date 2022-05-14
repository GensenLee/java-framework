package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：修改频道主持人姓名
 */
@Data
public class PolyvChannelUpdatePublisherParameter extends AppSignBean {

    /**
     * 主持人姓名，不超过20个字符
     */
    private String publisher;

    /**
     * 频道号，不提交默认为修改该用户的所有频道号的主持人姓名
     */
    @VerifyField(ignore = true)
    private String channelId;


    public static final class PolyvChannelUpdatePublisherParameterBuilder extends ParameterBuilder<PolyvChannelUpdatePublisherParameter> {
        private PolyvChannelUpdatePublisherParameter polyvChannelUpdatePublisherParameter;

        private PolyvChannelUpdatePublisherParameterBuilder() {
            polyvChannelUpdatePublisherParameter = new PolyvChannelUpdatePublisherParameter();
        }

        public static PolyvChannelUpdatePublisherParameterBuilder aPolyvChannelUpdatePublisherParameter() {
            return new PolyvChannelUpdatePublisherParameterBuilder();
        }

        public PolyvChannelUpdatePublisherParameterBuilder withPublisher(String publisher) {
            polyvChannelUpdatePublisherParameter.setPublisher(publisher);
            return this;
        }

        public PolyvChannelUpdatePublisherParameterBuilder withChannelId(String channelId) {
            polyvChannelUpdatePublisherParameter.setChannelId(channelId);
            return this;
        }

        @Override
        public PolyvChannelUpdatePublisherParameter build() {
            return polyvChannelUpdatePublisherParameter;
        }
    }
}
