package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.polyv.enums.StreamType;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：修改频道推流方式
 */
@Data
public class PolyvChannelUpdateStreamParameter extends ChannelSignBean {

    /**
     * 直播推流方式
     */
    private String streamType;


    public static final class PolyvChannelUpdateStreamParameterBuilder extends ParameterBuilder<PolyvChannelUpdateStreamParameter> {
        private PolyvChannelUpdateStreamParameter polyvChannelUpdateStreamParameter;

        private PolyvChannelUpdateStreamParameterBuilder() {
            polyvChannelUpdateStreamParameter = new PolyvChannelUpdateStreamParameter();
        }

        public static PolyvChannelUpdateStreamParameterBuilder aPolyvChannelUpdateStreamParameter() {
            return new PolyvChannelUpdateStreamParameterBuilder();
        }

        public PolyvChannelUpdateStreamParameterBuilder withStreamType(StreamType streamType) {
            polyvChannelUpdateStreamParameter.setStreamType(streamType.getValue());
            return this;
        }

        @Override
        public PolyvChannelUpdateStreamParameter build() {
            return polyvChannelUpdateStreamParameter;
        }
    }
}
