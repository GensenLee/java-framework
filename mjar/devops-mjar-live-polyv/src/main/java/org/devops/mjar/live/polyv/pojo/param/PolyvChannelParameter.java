package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import lombok.Data;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;

/**
 * @author GENSEN
 * @date 2020/10/27 18:10
 * @description：频道号请求
 */
@Data
public class PolyvChannelParameter extends ChannelSignBean {

    @SignIgnore
    @VerifyField(ignore = true)
    private String userId;

    public static final class PolyvChannelReqParameterBuilder extends ParameterBuilder<PolyvChannelParameter> {
        private PolyvChannelParameter polyvChannelParameter;

        private PolyvChannelReqParameterBuilder() {
            polyvChannelParameter = new PolyvChannelParameter();
        }

        public static PolyvChannelReqParameterBuilder aPolyvChannelParameter() {
            return new PolyvChannelReqParameterBuilder();
        }

        @Override
        public PolyvChannelParameter build() {
            return polyvChannelParameter;
        }
    }
}
