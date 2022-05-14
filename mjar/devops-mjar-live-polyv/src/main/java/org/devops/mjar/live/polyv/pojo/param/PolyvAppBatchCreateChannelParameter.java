package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.verify.VerifyUtil;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelBatchCreateItem;
import org.devops.mjar.live.core.sign.AppSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author GENSEN
 * @date 2021/3/3 13:56
 * @description：频道批量创建
 */
@Data
public class PolyvAppBatchCreateChannelParameter extends AppSignBean {

    /**
     * 频道列表
     */
    @SignIgnore
    private List<PolyvChannelBatchCreateItem> channels;

    @SignIgnore
    @VerifyField(ignore = true)
    private String userId;

    public static final class PolyvAppBatchCreateChannelParameterBuilder extends ParameterBuilder<PolyvAppBatchCreateChannelParameter> {
        private PolyvAppBatchCreateChannelParameter polyvAppBatchCreateChannelParameter;

        private PolyvAppBatchCreateChannelParameterBuilder() {
            polyvAppBatchCreateChannelParameter = new PolyvAppBatchCreateChannelParameter();
        }

        public static PolyvAppBatchCreateChannelParameterBuilder aPolyvAppBatchCreateChannelParameter() {
            return new PolyvAppBatchCreateChannelParameterBuilder();
        }

        public PolyvAppBatchCreateChannelParameterBuilder withChannels(List<PolyvChannelBatchCreateItem> channels) {
            polyvAppBatchCreateChannelParameter.setChannels(channels);
            return this;
        }

        @Override
        public PolyvAppBatchCreateChannelParameter build() {
            if (polyvAppBatchCreateChannelParameter.getChannels() != null) {
                for (PolyvChannelBatchCreateItem channel : polyvAppBatchCreateChannelParameter.getChannels()) {
                    VerifyUtil.verify(channel);
                }
            }
            return polyvAppBatchCreateChannelParameter;
        }
    }
}
