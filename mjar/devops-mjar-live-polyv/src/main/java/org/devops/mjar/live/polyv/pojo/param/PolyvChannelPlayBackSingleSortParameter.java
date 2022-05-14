package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：回放列表视频单个排序
 */
@Data
public class PolyvChannelPlayBackSingleSortParameter extends ChannelSignBean {

    /**
     * 要排序的回放视频ID
     */
    private String videoId;

    /**
     * 排序的操作类型，取值：up 上移, down 下移
     */
    private String type;


    public static final class PolyvChannelPlayBackSingleSortParameterBuilder extends ParameterBuilder<PolyvChannelPlayBackSingleSortParameter>{
        private PolyvChannelPlayBackSingleSortParameter polyvChannelPlayBackSingleSortParameter;

        private PolyvChannelPlayBackSingleSortParameterBuilder() {
            polyvChannelPlayBackSingleSortParameter = new PolyvChannelPlayBackSingleSortParameter();
        }

        public static PolyvChannelPlayBackSingleSortParameterBuilder aPolyvChannelPlayBackSingleSortParameter() {
            return new PolyvChannelPlayBackSingleSortParameterBuilder();
        }

        public PolyvChannelPlayBackSingleSortParameterBuilder withVideoId(String videoId) {
            polyvChannelPlayBackSingleSortParameter.setVideoId(videoId);
            return this;
        }

        public PolyvChannelPlayBackSingleSortParameterBuilder withType(String type) {
            polyvChannelPlayBackSingleSortParameter.setType(type);
            return this;
        }

        @Override
        public PolyvChannelPlayBackSingleSortParameter build() {
            return polyvChannelPlayBackSingleSortParameter;
        }
    }
}
