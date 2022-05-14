package org.devops.mjar.live.polyv.pojo.param;

import lombok.Data;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;

/**
 * @author fangzy
 * @description：批量查询频道单个回放信息
 */
@Data
public class PolyvChannelPlayBackGetParameter extends AppSignBean {

    /**
     * 频道号列表，多个使用,分隔
     */
    private String channelIds;


    public static final class PolyvChannelPlayBackGetParameterBuilder extends ParameterBuilder<PolyvChannelPlayBackGetParameter> {
        private PolyvChannelPlayBackGetParameter polyvChannelPlayBackGetParameter;

        private PolyvChannelPlayBackGetParameterBuilder() {
            polyvChannelPlayBackGetParameter = new PolyvChannelPlayBackGetParameter();
        }

        public static PolyvChannelPlayBackGetParameterBuilder aPolyvChannelPlayBackGetParameter() {
            return new PolyvChannelPlayBackGetParameterBuilder();
        }

        public PolyvChannelPlayBackGetParameterBuilder withChannelIds(String channelIds) {
            polyvChannelPlayBackGetParameter.setChannelIds(channelIds);
            return this;
        }

        @Override
        public PolyvChannelPlayBackGetParameter build() {
            return polyvChannelPlayBackGetParameter;
        }
    }
}
