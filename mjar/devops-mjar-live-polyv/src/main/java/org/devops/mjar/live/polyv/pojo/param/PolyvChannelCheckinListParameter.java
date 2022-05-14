package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/07/12 18:04
 * @description：查询频道发起的签到记录
 */
@Data
public class PolyvChannelCheckinListParameter extends ChannelSignBean {

    /**
     * 场次号
     */
    @VerifyField(ignore = true)
    private String sessionId;

    public static final class PolyvChannelCheckinListParameterBuilder extends ParameterBuilder<PolyvChannelCheckinListParameter>{
        private PolyvChannelCheckinListParameter polyvChannelCheckinListParameter;

        private PolyvChannelCheckinListParameterBuilder() {
            polyvChannelCheckinListParameter = new PolyvChannelCheckinListParameter();
        }

        public static PolyvChannelCheckinListParameterBuilder aPolyvChannelCheckinListParameter() {
            return new PolyvChannelCheckinListParameterBuilder();
        }

        public PolyvChannelCheckinListParameterBuilder withSessionId(String sessionId) {
            polyvChannelCheckinListParameter.setSessionId(sessionId);
            return this;
        }

        @Override
        public PolyvChannelCheckinListParameter build() {
            return polyvChannelCheckinListParameter;
        }
    }
}
