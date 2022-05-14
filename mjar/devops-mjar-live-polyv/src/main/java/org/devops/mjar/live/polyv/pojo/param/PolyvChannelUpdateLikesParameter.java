package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：修改频道点赞数和观看次数
 */
@Data
public class PolyvChannelUpdateLikesParameter extends ChannelSignBean {
    /**
     * 点赞数
     */
    @VerifyField(ignore = true)
    private Integer likes;

    /**
     * 观看热度（对应观看次数）
     */
    @VerifyField(ignore = true)
    private Integer viewers;


    public static final class PolyvChannelUpdateLikesParameterBuilder extends ParameterBuilder<PolyvChannelUpdateLikesParameter> {
        private PolyvChannelUpdateLikesParameter polyvChannelUpdateLikesParameter;

        private PolyvChannelUpdateLikesParameterBuilder() {
            polyvChannelUpdateLikesParameter = new PolyvChannelUpdateLikesParameter();
        }

        public static PolyvChannelUpdateLikesParameterBuilder aPolyvChannelUpdateLikesParameter() {
            return new PolyvChannelUpdateLikesParameterBuilder();
        }

        public PolyvChannelUpdateLikesParameterBuilder withLikes(Integer likes) {
            polyvChannelUpdateLikesParameter.setLikes(likes);
            return this;
        }

        public PolyvChannelUpdateLikesParameterBuilder withViewers(Integer viewers) {
            polyvChannelUpdateLikesParameter.setViewers(viewers);
            return this;
        }

        @Override
        public PolyvChannelUpdateLikesParameter build() {
            return polyvChannelUpdateLikesParameter;
        }
    }
}
