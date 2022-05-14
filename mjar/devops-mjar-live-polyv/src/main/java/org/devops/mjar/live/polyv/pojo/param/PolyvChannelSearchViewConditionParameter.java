package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/27 9:58
 * @description：查询频道观看条件
 */

@Data
public class PolyvChannelSearchViewConditionParameter extends ChannelSignBean {


    public static final class PolyvChannelSearchViewConditionParameterBuilder extends ParameterBuilder<PolyvChannelSearchViewConditionParameter> {
        private PolyvChannelSearchViewConditionParameter polyvChannelSearchViewConditionParameter;

        private PolyvChannelSearchViewConditionParameterBuilder() {
            polyvChannelSearchViewConditionParameter = new PolyvChannelSearchViewConditionParameter();
        }

        public static PolyvChannelSearchViewConditionParameterBuilder aPolyvChannelSearchViewConditionParameter() {
            return new PolyvChannelSearchViewConditionParameterBuilder();
        }

        @Override
        public PolyvChannelSearchViewConditionParameter build() {
            return polyvChannelSearchViewConditionParameter;
        }
    }
}
