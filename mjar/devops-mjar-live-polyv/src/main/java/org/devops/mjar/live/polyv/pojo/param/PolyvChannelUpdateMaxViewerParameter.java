package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：修改频道观看人数限制
 */
@Data
public class PolyvChannelUpdateMaxViewerParameter extends ChannelSignBean {

    /**
     * 最大观看在线人数，等于0时表示关闭在线人数观看限制
     */
    private Integer maxViewer;


    public static final class PolyvChannelUpdateMaxViewerParameterBuilder extends ParameterBuilder<PolyvChannelUpdateMaxViewerParameter> {
        private PolyvChannelUpdateMaxViewerParameter polyvChannelUpdateMaxViewerParameter;

        private PolyvChannelUpdateMaxViewerParameterBuilder() {
            polyvChannelUpdateMaxViewerParameter = new PolyvChannelUpdateMaxViewerParameter();
        }

        public static PolyvChannelUpdateMaxViewerParameterBuilder aPolyvChannelUpdateMaxViewerParameter() {
            return new PolyvChannelUpdateMaxViewerParameterBuilder();
        }

        public PolyvChannelUpdateMaxViewerParameterBuilder withMaxViewer(Integer maxViewer) {
            polyvChannelUpdateMaxViewerParameter.setMaxViewer(maxViewer);
            return this;
        }

        @Override
        public PolyvChannelUpdateMaxViewerParameter build() {
            return polyvChannelUpdateMaxViewerParameter;
        }
    }
}
