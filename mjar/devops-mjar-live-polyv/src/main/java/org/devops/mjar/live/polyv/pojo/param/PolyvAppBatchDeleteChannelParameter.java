package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author GENSEN
 * @date 2021/3/3 11:35
 * @description：批量删除频道
 */
@Data
public class PolyvAppBatchDeleteChannelParameter extends AppSignBean {

    /**
     * 频道号列表
     */
    @SignIgnore
    private List<String> channelIds;

    @SignIgnore
    @VerifyField(ignore = true)
    private String userId;

    public static final class PolyvAppBatchDeleteChannelParameterBuilder extends ParameterBuilder<PolyvAppBatchDeleteChannelParameter> {
        private PolyvAppBatchDeleteChannelParameter polyvAppBatchDeleteChannelParameter;

        private PolyvAppBatchDeleteChannelParameterBuilder() {
            polyvAppBatchDeleteChannelParameter = new PolyvAppBatchDeleteChannelParameter();
        }

        public static PolyvAppBatchDeleteChannelParameterBuilder aPolyvAppBatchDeleteChannelParameter() {
            return new PolyvAppBatchDeleteChannelParameterBuilder();
        }

        public PolyvAppBatchDeleteChannelParameterBuilder withChannelIds(List<String> channelIds) {
            polyvAppBatchDeleteChannelParameter.setChannelIds(channelIds);
            return this;
        }

        @Override
        public PolyvAppBatchDeleteChannelParameter build() {
            return polyvAppBatchDeleteChannelParameter;
        }
    }
}
