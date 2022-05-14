package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：批量添加点播视频到回放列表
 */

@Data
public class PolyvChannelPlayBackBatchAddParameter extends ChannelSignBean {

    /**
     * 点播视频vid，多个视频添加则多个视频ID通过英文逗号,隔开拼接成字符串
     */
    private String vids;


    public static final class PolyvChannelPlayBackBatchAddParameterBuilder extends ParameterBuilder<PolyvChannelPlayBackBatchAddParameter> {
        private PolyvChannelPlayBackBatchAddParameter polyvChannelPlayBackBatchAddParameter;

        private PolyvChannelPlayBackBatchAddParameterBuilder() {
            polyvChannelPlayBackBatchAddParameter = new PolyvChannelPlayBackBatchAddParameter();
        }

        public static PolyvChannelPlayBackBatchAddParameterBuilder aPolyvChannelPlayBackBatchAddParameter() {
            return new PolyvChannelPlayBackBatchAddParameterBuilder();
        }

        public PolyvChannelPlayBackBatchAddParameterBuilder withVids(String vids) {
            polyvChannelPlayBackBatchAddParameter.setVids(vids);
            return this;
        }

        @Override
        public PolyvChannelPlayBackBatchAddParameter build() {
            return polyvChannelPlayBackBatchAddParameter;
        }
    }
}
