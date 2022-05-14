package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

/**
 * @author fangzy
 * @description：修改视频排序
 */
@Data
public class PolyvChannelPlayBackSortParameter extends ChannelSignBean {

    /**
     * Playback-回放列表，vod-点播列表; 默认普通直播场景为vod，三分屏为playback
     */
    @VerifyField(ignore = true)
    private String listType;

    @SignIgnore
    private PolyvChannelPlayBackSortParameter.PolyvChannelPlayBackSortParameterBody body = new PolyvChannelPlayBackSortParameter.PolyvChannelPlayBackSortParameterBody();

    @Data
    public static class PolyvChannelPlayBackSortParameterBody extends BaseBean {

        /**
         * 完整视频ID列表，请求视频ID数量必须和回放列表视频数量一致，以json格式通过请求体方式提交
         * 格式如：{"videoIds": ["40b5fc215e","2ce845def0", "73801f70c8"]}
         */
        private String videoIds;
    }


    public static final class PolyvChannelPlayBackSortParameterBuilder extends ParameterBuilder<PolyvChannelPlayBackSortParameter> {
        private PolyvChannelPlayBackSortParameter polyvChannelPlayBackSortParameter;

        private PolyvChannelPlayBackSortParameterBuilder() {
            polyvChannelPlayBackSortParameter = new PolyvChannelPlayBackSortParameter();
        }

        public static PolyvChannelPlayBackSortParameterBuilder aPolyvChannelPlayBackSortParameter() {
            return new PolyvChannelPlayBackSortParameterBuilder();
        }

        public PolyvChannelPlayBackSortParameterBuilder withListType(String listType) {
            polyvChannelPlayBackSortParameter.setListType(listType);
            return this;
        }

        public PolyvChannelPlayBackSortParameterBuilder withVideoIds(String videoIds) {
            polyvChannelPlayBackSortParameter.getBody().setVideoIds(videoIds);
            return this;
        }

        @Override
        public PolyvChannelPlayBackSortParameter build() {
            return polyvChannelPlayBackSortParameter;
        }
    }
}
