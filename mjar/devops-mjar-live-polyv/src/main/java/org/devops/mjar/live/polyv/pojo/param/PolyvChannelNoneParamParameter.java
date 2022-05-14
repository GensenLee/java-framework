package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/28 18:04
 * @description：频道无参请求
 */
@Data
public class PolyvChannelNoneParamParameter extends ChannelSignBean {

    public static final class PolyvChannelNoneParamReqParameterBuilder extends ParameterBuilder<PolyvChannelNoneParamParameter> {
        private PolyvChannelNoneParamParameter polyvAppNoneParam;

        private PolyvChannelNoneParamReqParameterBuilder() {
            polyvAppNoneParam = new PolyvChannelNoneParamParameter();
        }

        public static PolyvChannelNoneParamReqParameterBuilder aPolyvChannelNoneParamParameter() {
            return new PolyvChannelNoneParamReqParameterBuilder();
        }

        @Override
        public PolyvChannelNoneParamParameter build() {
            return polyvAppNoneParam;
        }
    }
}
