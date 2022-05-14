package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：批量删除回放列表的回放视频
 */
@Data
public class PolyvChannelPlayBackBatchDeleteParameter extends ChannelSignBean {

    /**
     * 要删除的回放视频ID，不同id之间用英文逗号分开
     */
    private String videoIds;


    public static final class PolyvChannelPlayBackBatchDeleteParameterBuilder extends ParameterBuilder<PolyvChannelPlayBackBatchDeleteParameter>{
        private PolyvChannelPlayBackBatchDeleteParameter polyvChannelPlayBackBatchDeleteParameter;

        private PolyvChannelPlayBackBatchDeleteParameterBuilder() {
            polyvChannelPlayBackBatchDeleteParameter = new PolyvChannelPlayBackBatchDeleteParameter();
        }

        public static PolyvChannelPlayBackBatchDeleteParameterBuilder aPolyvChannelPlayBackBatchDeleteParameter() {
            return new PolyvChannelPlayBackBatchDeleteParameterBuilder();
        }

        public PolyvChannelPlayBackBatchDeleteParameterBuilder withVideoIds(String videoIds) {
            polyvChannelPlayBackBatchDeleteParameter.setVideoIds(videoIds);
            return this;
        }

        @Override
        public PolyvChannelPlayBackBatchDeleteParameter build() {
            return polyvChannelPlayBackBatchDeleteParameter;
        }
    }
}
