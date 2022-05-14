package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @description：修改提问功能开关
 */

@Data
public class PolyvChannelUpdateMenuConsultParameter extends ChannelSignBean {

    /**
     * 咨询提问开关
     */
    private String enabled;

    public static final class PolyvChannelUpdateMenuConsultParameterBuilder extends ParameterBuilder<PolyvChannelUpdateMenuConsultParameter> {
        private PolyvChannelUpdateMenuConsultParameter polyvChannelUpdateMenuConsultParameter;

        private PolyvChannelUpdateMenuConsultParameterBuilder() {
            polyvChannelUpdateMenuConsultParameter = new PolyvChannelUpdateMenuConsultParameter();
        }

        public static PolyvChannelUpdateMenuConsultParameterBuilder aPolyvChannelUpdateMenuConsultParameter() {
            return new PolyvChannelUpdateMenuConsultParameterBuilder();
        }

        public PolyvChannelUpdateMenuConsultParameterBuilder withEnabled(String enabled) {
            polyvChannelUpdateMenuConsultParameter.setEnabled(enabled);
            return this;
        }
        @Override
        public PolyvChannelUpdateMenuConsultParameter build() {
            return polyvChannelUpdateMenuConsultParameter;
        }
    }
}
