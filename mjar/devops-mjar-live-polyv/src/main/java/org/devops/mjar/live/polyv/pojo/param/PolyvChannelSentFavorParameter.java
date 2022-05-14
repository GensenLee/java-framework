package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：发送点赞
 */
@Data
public class PolyvChannelSentFavorParameter extends ChannelSignBean {

    /**
     * 点赞观众的ID
     */
    private String viewerId;

    /**
     * 点赞的数目，不能超过30，提交后在times-1秒后才能再点赞 。默认为1
     */
    @VerifyField(ignore = true)
    private Integer times;


    public static final class PolyvChannelSentFavorParameterBuilder extends ParameterBuilder<PolyvChannelSentFavorParameter> {
        private PolyvChannelSentFavorParameter polyvChannelSentFavorParameter;

        private PolyvChannelSentFavorParameterBuilder() {
            polyvChannelSentFavorParameter = new PolyvChannelSentFavorParameter();
        }

        public static PolyvChannelSentFavorParameterBuilder aPolyvChannelSentFavorParameter() {
            return new PolyvChannelSentFavorParameterBuilder();
        }

        public PolyvChannelSentFavorParameterBuilder withViewerId(String viewerId) {
            polyvChannelSentFavorParameter.setViewerId(viewerId);
            return this;
        }

        public PolyvChannelSentFavorParameterBuilder withTimes(Integer times) {
            polyvChannelSentFavorParameter.setTimes(times);
            return this;
        }

        @Override
        public PolyvChannelSentFavorParameter build() {
            return polyvChannelSentFavorParameter;
        }
    }
}
