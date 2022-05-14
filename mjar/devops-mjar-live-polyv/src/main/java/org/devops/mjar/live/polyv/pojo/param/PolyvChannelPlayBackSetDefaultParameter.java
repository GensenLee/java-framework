package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.VideoListType;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：修改视频库的默认视频
 */
@Data
public class PolyvChannelPlayBackSetDefaultParameter extends ChannelSignBean {

    /**
     * 直播系统中视频id
     */
    private String videoId;

    /**
     *  playback-回放列表，vod-点播列表; 默认普通直播场景为vod，三分屏为playback
     */
    @VerifyField(ignore = true)
    private String listType;


    public static final class PolyvChannelPlayBackSetDefaultParameterBuilder extends ParameterBuilder<PolyvChannelPlayBackSetDefaultParameter> {
        private PolyvChannelPlayBackSetDefaultParameter polyvChannelPlayBackSetDefaultParameter;

        private PolyvChannelPlayBackSetDefaultParameterBuilder() {
            polyvChannelPlayBackSetDefaultParameter = new PolyvChannelPlayBackSetDefaultParameter();
        }

        public static PolyvChannelPlayBackSetDefaultParameterBuilder aPolyvChannelPlayBackSetDefaultParameter() {
            return new PolyvChannelPlayBackSetDefaultParameterBuilder();
        }

        public PolyvChannelPlayBackSetDefaultParameterBuilder withVideoId(String videoId) {
            polyvChannelPlayBackSetDefaultParameter.setVideoId(videoId);
            return this;
        }

        public PolyvChannelPlayBackSetDefaultParameterBuilder withListType(VideoListType listType) {
            polyvChannelPlayBackSetDefaultParameter.setListType(listType.getCode());
            return this;
        }

        @Override
        public PolyvChannelPlayBackSetDefaultParameter build() {
            return polyvChannelPlayBackSetDefaultParameter;
        }
    }
}
