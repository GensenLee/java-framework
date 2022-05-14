package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：删除视频库中的视频
 */
@Data
public class PolyvChannelPlayBackDeleteParameter extends ChannelSignBean {

    /**
     * 直播系统中视频id
     */
    private String videoId;

    @VerifyField(ignore = true)
    private String listType;

    public enum ListType{
        PLAYBACK("playback","回放列表"),
        VOD("vod","点播列表");
        private String code;
        private String name;

        ListType(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    public static final class PolyvChannelPlayBackDeleteParameterBuilder extends ParameterBuilder<PolyvChannelPlayBackDeleteParameter> {
        private PolyvChannelPlayBackDeleteParameter polyvChannelPlayBackDeleteParameter;

        private PolyvChannelPlayBackDeleteParameterBuilder() {
            polyvChannelPlayBackDeleteParameter = new PolyvChannelPlayBackDeleteParameter();
        }

        public static PolyvChannelPlayBackDeleteParameterBuilder aPolyvChannelPlayBackDeleteParameter() {
            return new PolyvChannelPlayBackDeleteParameterBuilder();
        }

        public PolyvChannelPlayBackDeleteParameterBuilder withVideoId(String videoId) {
            polyvChannelPlayBackDeleteParameter.setVideoId(videoId);
            return this;
        }

        public PolyvChannelPlayBackDeleteParameterBuilder withListType(ListType listType) {
            polyvChannelPlayBackDeleteParameter.setListType(listType.code);
            return this;
        }

        @Override
        public PolyvChannelPlayBackDeleteParameter build() {
            return polyvChannelPlayBackDeleteParameter;
        }
    }
}
