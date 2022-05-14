package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：修改回放视频名称
 */
@Data
public class PolyvChannelPlayBackUpdateTitleParameter extends ChannelSignBean {

    /**
     * 回放视频ID
     */
    private String videoId;

    /**
     * 回放视频名称
     */
    private String title;


    public static final class PolyvChannelPlayBackUpdateTitleParameterBuilder extends ParameterBuilder<PolyvChannelPlayBackUpdateTitleParameter> {
        private PolyvChannelPlayBackUpdateTitleParameter polyvChannelPlayBackUpdateTitleParameter;

        private PolyvChannelPlayBackUpdateTitleParameterBuilder() {
            polyvChannelPlayBackUpdateTitleParameter = new PolyvChannelPlayBackUpdateTitleParameter();
        }

        public static PolyvChannelPlayBackUpdateTitleParameterBuilder aPolyvChannelPlayBackUpdateTitleParameter() {
            return new PolyvChannelPlayBackUpdateTitleParameterBuilder();
        }

        public PolyvChannelPlayBackUpdateTitleParameterBuilder withVideoId(String videoId) {
            polyvChannelPlayBackUpdateTitleParameter.setVideoId(videoId);
            return this;
        }

        public PolyvChannelPlayBackUpdateTitleParameterBuilder withTitle(String title) {
            polyvChannelPlayBackUpdateTitleParameter.setTitle(title);
            return this;
        }

        @Override
        public PolyvChannelPlayBackUpdateTitleParameter build() {
            return polyvChannelPlayBackUpdateTitleParameter;
        }
    }
}
