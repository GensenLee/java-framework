package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/26 14:28
 * @description：查询频道直播截图
 */

@Data
public class PolyvChannelSearchCaptureParameter extends ChannelSignBean {
    public static final class PolyvChannelSearchCaptureParameterBuilder extends ParameterBuilder<PolyvChannelSearchCaptureParameter> {
        private PolyvChannelSearchCaptureParameter polyvChannelSearchCaptureParameter;

        private PolyvChannelSearchCaptureParameterBuilder() {
            polyvChannelSearchCaptureParameter = new PolyvChannelSearchCaptureParameter();
        }

        public static PolyvChannelSearchCaptureParameterBuilder aPolyvChannelSearchCaptureParameter() {
            return new PolyvChannelSearchCaptureParameterBuilder();
        }
        @Override
        public PolyvChannelSearchCaptureParameter build() {
            return polyvChannelSearchCaptureParameter;
        }
    }
}
