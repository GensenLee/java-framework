package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * 删除重置课件
 */
@Data
public class PolyvChannelDeletePptRecordParameter extends ChannelSignBean {

    /**
     * 任务ID列表，多个任务ID使用","进行分隔
     */
    private String taskIds;


    public static final class PolyvChannelDeletePptRecordParameterBuilder extends ParameterBuilder<PolyvChannelDeletePptRecordParameter> {
        private PolyvChannelDeletePptRecordParameter polyvChannelDeletePptRecordParameter;

        private PolyvChannelDeletePptRecordParameterBuilder() {
            polyvChannelDeletePptRecordParameter = new PolyvChannelDeletePptRecordParameter();
        }

        public static PolyvChannelDeletePptRecordParameterBuilder aPolyvChannelDeletePptRecordParameter() {
            return new PolyvChannelDeletePptRecordParameterBuilder();
        }

        public PolyvChannelDeletePptRecordParameterBuilder withTaskIds(String taskIds) {
            polyvChannelDeletePptRecordParameter.setTaskIds(taskIds);
            return this;
        }

        @Override
        public PolyvChannelDeletePptRecordParameter build() {
            return polyvChannelDeletePptRecordParameter;
        }
    }
}
