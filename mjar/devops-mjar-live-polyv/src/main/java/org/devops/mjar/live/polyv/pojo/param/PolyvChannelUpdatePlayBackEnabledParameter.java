package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.enums.PlaybackOrigin;
import org.devops.mjar.live.polyv.enums.PlaybackType;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/29 18:45
 * @description：频道回放设置
 */
@ApiModel("频道回放设置")
@Data
public class PolyvChannelUpdatePlayBackEnabledParameter extends ChannelSignBean {

    /**
     * 回放开关是开/关的状态，值为Y/N，必填
     */
    private String playbackEnabled;

    /**
     * 回放方式，single-单个回放，list-列表回放
     */
    @VerifyField(ignore = true)
    private String type;

    /**
     * 回放来源，record-暂存，playback-回放列表，vod-点播列表
     */
    @VerifyField(ignore = true)
    private String origin;

    /**
     * 单个回放的视频id
     */
    @VerifyField(ignore = true)
    private String videoId;

    @VerifyField(ignore = true)
    @SignIgnore
    private String userId;

    public static final class PolyvChannelUpdatePlayBackEnabledParameterBuilder extends ParameterBuilder<PolyvChannelUpdatePlayBackEnabledParameter>{
        private PolyvChannelUpdatePlayBackEnabledParameter polyvChannelUpdatePlayBackEnabledParameter;

        private PolyvChannelUpdatePlayBackEnabledParameterBuilder() {
            polyvChannelUpdatePlayBackEnabledParameter = new PolyvChannelUpdatePlayBackEnabledParameter();
        }

        public static PolyvChannelUpdatePlayBackEnabledParameterBuilder aPolyvChannelUpdatePlayBackEnabledParameter() {
            return new PolyvChannelUpdatePlayBackEnabledParameterBuilder();
        }

        public PolyvChannelUpdatePlayBackEnabledParameterBuilder withPlayBackEnabled(EnableSetting setting) {
            polyvChannelUpdatePlayBackEnabledParameter.setPlaybackEnabled(setting.getValue());
            return this;
        }

        public PolyvChannelUpdatePlayBackEnabledParameterBuilder withType(PlaybackType type) {
            polyvChannelUpdatePlayBackEnabledParameter.setType(type.getCode());
            return this;
        }

        public PolyvChannelUpdatePlayBackEnabledParameterBuilder withOrigin(PlaybackOrigin origin) {
            polyvChannelUpdatePlayBackEnabledParameter.setOrigin(origin.getCode());
            return this;
        }

        public PolyvChannelUpdatePlayBackEnabledParameterBuilder withVideoId(String videoId) {
            polyvChannelUpdatePlayBackEnabledParameter.setVideoId(videoId);
            return this;
        }

        @Override
        public PolyvChannelUpdatePlayBackEnabledParameter build() {
            return polyvChannelUpdatePlayBackEnabledParameter;
        }
    }
}
