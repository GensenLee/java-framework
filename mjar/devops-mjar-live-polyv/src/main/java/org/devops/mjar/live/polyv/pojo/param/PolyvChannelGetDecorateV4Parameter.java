package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * 查询频道页面装修(新版)
 */
@Data
public class PolyvChannelGetDecorateV4Parameter extends ChannelSignBean {


    public static final class PolyvChannelGetDecorateV4ParameterBuilder extends ParameterBuilder<PolyvChannelGetDecorateV4Parameter> {
        private PolyvChannelGetDecorateV4Parameter polyvChannelGetDecorateV4Parameter;

        private PolyvChannelGetDecorateV4ParameterBuilder() {
            polyvChannelGetDecorateV4Parameter = new PolyvChannelGetDecorateV4Parameter();
        }

        public static PolyvChannelGetDecorateV4ParameterBuilder aPolyvChannelGetDecorateV4Parameter() {
            return new PolyvChannelGetDecorateV4ParameterBuilder();
        }


        @Override
        public PolyvChannelGetDecorateV4Parameter build() {
            return polyvChannelGetDecorateV4Parameter;
        }
    }
}
