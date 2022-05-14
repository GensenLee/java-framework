package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.PlaybackPosition;
import org.devops.mjar.live.polyv.enums.VideoListType;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：添加点播视频请求
 */
@Data
public class PolyvChannelPlayBackAddVideosParameter extends ChannelSignBean {

    /**
     * 点播视频vid
     */
    private String vid;

    /**
     * 添加到视频库列表中的位置，默认为N, Y：回放列表中置顶, N：回放列表中置底
     */
    @VerifyField(ignore = true)
    private String setAsDefault;

    /**
     *  playback-回放列表，vod-点播列表; 默认普通直播场景为vod，三分屏为playback
     */
    @VerifyField(ignore = true)
    private String listType;


    public static final class PolyvChannelPlayBackAddVideosParameterBuilder extends ParameterBuilder<PolyvChannelPlayBackAddVideosParameter> {
        private PolyvChannelPlayBackAddVideosParameter polyvChannelPlayBackAddVideosParameter;

        private PolyvChannelPlayBackAddVideosParameterBuilder() {
            polyvChannelPlayBackAddVideosParameter = new PolyvChannelPlayBackAddVideosParameter();
        }

        public static PolyvChannelPlayBackAddVideosParameterBuilder aPolyvChannelPlayBackAddVideosParameter() {
            return new PolyvChannelPlayBackAddVideosParameterBuilder();
        }

        public PolyvChannelPlayBackAddVideosParameterBuilder withVid(String vid) {
            polyvChannelPlayBackAddVideosParameter.setVid(vid);
            return this;
        }

        public PolyvChannelPlayBackAddVideosParameterBuilder withSetAsDefault(PlaybackPosition setAsDefault) {
            polyvChannelPlayBackAddVideosParameter.setSetAsDefault(setAsDefault.getCode());
            return this;
        }

        public PolyvChannelPlayBackAddVideosParameterBuilder withListType(VideoListType listType) {
            polyvChannelPlayBackAddVideosParameter.setListType(listType.getCode());
            return this;
        }

        @Override
        public PolyvChannelPlayBackAddVideosParameter build() {
            return polyvChannelPlayBackAddVideosParameter;
        }
    }
}
