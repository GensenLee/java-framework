package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：修改频道名称
 */
@Data
public class PolyvChannelUpdateNameParameter extends ChannelSignBean {

    /**
     * 修改后的频道名称
     */
    private String name;


    public static final class PolyvChannelUpdateNameParameterBuilder extends ParameterBuilder<PolyvChannelUpdateNameParameter> {
        private PolyvChannelUpdateNameParameter polyvChannelUpdateNameParameter;

        private PolyvChannelUpdateNameParameterBuilder() {
            polyvChannelUpdateNameParameter = new PolyvChannelUpdateNameParameter();
        }

        public static PolyvChannelUpdateNameParameterBuilder aPolyvChannelUpdateNameParameter() {
            return new PolyvChannelUpdateNameParameterBuilder();
        }

        public PolyvChannelUpdateNameParameterBuilder withName(String name) {
            polyvChannelUpdateNameParameter.setName(name);
            return this;
        }

        @Override
        public PolyvChannelUpdateNameParameter build() {
            return polyvChannelUpdateNameParameter;
        }
    }
}
