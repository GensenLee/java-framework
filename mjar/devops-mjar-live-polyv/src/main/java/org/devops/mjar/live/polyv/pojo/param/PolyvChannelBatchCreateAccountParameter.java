package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.polyv.pojo.model.PolyvChannelBatchCreateAccountItem;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author GENSEN
 * @date 2020/11/3 15:36
 * @description：批量创建子频道
 */
@Data
public class PolyvChannelBatchCreateAccountParameter extends ChannelSignBean {

    /**
     * 请求体
     */
    @SignIgnore
    List<PolyvChannelBatchCreateAccountItem> list;

    public static final class PolyvChannelBatchCreateAccountParameterBuilder extends ParameterBuilder<PolyvChannelBatchCreateAccountParameter> {
        private PolyvChannelBatchCreateAccountParameter polyvChannelBatchCreateAccountParameter;

        private PolyvChannelBatchCreateAccountParameterBuilder() {
            polyvChannelBatchCreateAccountParameter = new PolyvChannelBatchCreateAccountParameter();
        }
        public static PolyvChannelBatchCreateAccountParameter.PolyvChannelBatchCreateAccountParameterBuilder aPolyvChannelBatchCreateAccountParameterBuilder() {
            return new PolyvChannelBatchCreateAccountParameter.PolyvChannelBatchCreateAccountParameterBuilder();
        }

        public PolyvChannelBatchCreateAccountParameter.PolyvChannelBatchCreateAccountParameterBuilder withList(List<PolyvChannelBatchCreateAccountItem> channels) {
            polyvChannelBatchCreateAccountParameter.setList(channels);
            return this;
        }

        @Override
        public PolyvChannelBatchCreateAccountParameter build() {
            return polyvChannelBatchCreateAccountParameter;
        }
    }
}
