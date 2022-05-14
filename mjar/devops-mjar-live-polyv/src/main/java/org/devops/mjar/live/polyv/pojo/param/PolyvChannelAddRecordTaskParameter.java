package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：创建重制课件任务
 */
@Data
public class PolyvChannelAddRecordTaskParameter extends ChannelSignBean {

    /**
     * 回放视频id
     */
    private String videoId;

    /**
     * 视频类型, record暂存、 playback回放-默认
     */
    @VerifyField(ignore = true)
    private String videoType;

    public enum VideoType {
        RECORD("record","暂存"),
        PLAYBACK("playback","回放");
        private String code;
        private String name;

        VideoType(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }


    public static final class PolyvChannelAddRecordTaskParameterBuilder extends ParameterBuilder<PolyvChannelAddRecordTaskParameter> {
        private PolyvChannelAddRecordTaskParameter polyvChannelAddRecordTaskParameter;

        private PolyvChannelAddRecordTaskParameterBuilder() {
            polyvChannelAddRecordTaskParameter = new PolyvChannelAddRecordTaskParameter();
        }

        public static PolyvChannelAddRecordTaskParameterBuilder aPolyvChannelAddRecordTaskParameter() {
            return new PolyvChannelAddRecordTaskParameterBuilder();
        }

        public PolyvChannelAddRecordTaskParameterBuilder withVideoId(String videoId) {
            polyvChannelAddRecordTaskParameter.setVideoId(videoId);
            return this;
        }

        public PolyvChannelAddRecordTaskParameterBuilder withVideoType(VideoType videoType) {
            polyvChannelAddRecordTaskParameter.setVideoType(videoType.code);
            return this;
        }

        @Override
        public PolyvChannelAddRecordTaskParameter build() {
            return polyvChannelAddRecordTaskParameter;
        }
    }
}
