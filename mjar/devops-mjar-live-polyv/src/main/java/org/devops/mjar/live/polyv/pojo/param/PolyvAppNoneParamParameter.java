package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/28 18:04
 * @description：应用无参请求
 */
@Data
public class PolyvAppNoneParamParameter extends AppSignBean {


    public static final class PolyvAppNoneParamParameterBuilder extends ParameterBuilder<PolyvAppNoneParamParameter> {
        private PolyvAppNoneParamParameter polyvAppNoneParamParameter;

        private PolyvAppNoneParamParameterBuilder() {
            polyvAppNoneParamParameter = new PolyvAppNoneParamParameter();
        }

        public static PolyvAppNoneParamParameterBuilder aPolyvAppNoneParamParameter() {
            return new PolyvAppNoneParamParameterBuilder();
        }

        @Override
        public PolyvAppNoneParamParameter build() {
            return polyvAppNoneParamParameter;
        }
    }
}
