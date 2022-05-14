package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：查询频道点赞数和观看次数
 */
@Data
public class PolyvChannelLiveLikesParameter extends AppSignBean {

    private String channelIds;


    public static final class PolyvChannelLiveLikesParameterBuilder extends ParameterBuilder<PolyvChannelLiveLikesParameter> {
        private PolyvChannelLiveLikesParameter polyvChannelLiveLikesParameter;

        private PolyvChannelLiveLikesParameterBuilder() {
            polyvChannelLiveLikesParameter = new PolyvChannelLiveLikesParameter();
        }

        public static PolyvChannelLiveLikesParameterBuilder aPolyvChannelLiveLikesParameter() {
            return new PolyvChannelLiveLikesParameterBuilder();
        }

        public PolyvChannelLiveLikesParameterBuilder withChannelIds(String channelIds) {
            polyvChannelLiveLikesParameter.setChannelIds(channelIds);
            return this;
        }

        @Override
        public PolyvChannelLiveLikesParameter build() {
            return polyvChannelLiveLikesParameter;
        }
    }
}
