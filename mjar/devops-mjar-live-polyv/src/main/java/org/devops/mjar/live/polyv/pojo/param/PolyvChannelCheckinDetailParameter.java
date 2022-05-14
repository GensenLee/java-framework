package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;


/**
 * @author fangzy
 * @description：根据发起签到记录查询签到结果
 */
@Data
public class PolyvChannelCheckinDetailParameter extends ChannelSignBean {

    /**
     * 签到Id
     */
    private String checkinId;

    public static final class PolyvChannelCheckinDetailParameterBuilder extends ParameterBuilder<PolyvChannelCheckinDetailParameter> {
        private PolyvChannelCheckinDetailParameter polyvChannelCheckinDetailParameter;

        private PolyvChannelCheckinDetailParameterBuilder() {
            polyvChannelCheckinDetailParameter = new PolyvChannelCheckinDetailParameter();
        }

        public static PolyvChannelCheckinDetailParameterBuilder aPolyvChannelCheckinDetailParameter() {
            return new PolyvChannelCheckinDetailParameterBuilder();
        }

        public PolyvChannelCheckinDetailParameterBuilder withCheckinId(String checkinId) {
            polyvChannelCheckinDetailParameter.setCheckinId(checkinId);
            return this;
        }

        @Override
        public PolyvChannelCheckinDetailParameter build() {
            return polyvChannelCheckinDetailParameter;
        }
    }
}
