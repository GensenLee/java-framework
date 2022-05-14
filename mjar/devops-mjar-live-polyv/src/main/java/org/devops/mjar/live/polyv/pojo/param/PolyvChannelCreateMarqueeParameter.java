package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/26 15:37
 * @description：跑马灯设置
 */

@Data
public class PolyvChannelCreateMarqueeParameter extends ChannelSignBean {

    /**
     * 填入"Y"或"N"，自定义url防录屏跑马灯开关
     */
    private String marqueeRestrict;

    /**
     * 自定义url， 在开关为关时可为空，开启开关时为必填
     */
    private String url;

    public static final class PolyvChannelCreateMarqueeBuilder extends ParameterBuilder<PolyvChannelCreateMarqueeParameter> {
        private PolyvChannelCreateMarqueeParameter polyvChannelCreateMarqueeParameter;

        private PolyvChannelCreateMarqueeBuilder() {
            polyvChannelCreateMarqueeParameter = new PolyvChannelCreateMarqueeParameter();
        }

        public static PolyvChannelCreateMarqueeBuilder aPolyvChannelCreateMarquee() {
            return new PolyvChannelCreateMarqueeBuilder();
        }

        public PolyvChannelCreateMarqueeBuilder withMarqueeRestrict(String marqueeRestrict) {
            polyvChannelCreateMarqueeParameter.setMarqueeRestrict(marqueeRestrict);
            return this;
        }

        public PolyvChannelCreateMarqueeBuilder withUrl(String url) {
            polyvChannelCreateMarqueeParameter.setUrl(url);
            return this;
        }

        @Override
        public PolyvChannelCreateMarqueeParameter build() {
            return polyvChannelCreateMarqueeParameter;
        }
    }
}
