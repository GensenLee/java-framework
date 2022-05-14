package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：批量删除暂存列表视频
 */
@Data
public class PolyvChannelRecordBatchDeleteParameter extends ChannelSignBean {

    /**
     * 要删除的回放视频ID，不同id之间用英文逗号分开
     */
    private String fileIds;


    public static final class PolyvChannelRecordBatchDeleteParameterBuilder extends ParameterBuilder<PolyvChannelRecordBatchDeleteParameter> {
        private PolyvChannelRecordBatchDeleteParameter polyvChannelRecordBatchDeleteParameter;

        private PolyvChannelRecordBatchDeleteParameterBuilder() {
            polyvChannelRecordBatchDeleteParameter = new PolyvChannelRecordBatchDeleteParameter();
        }

        public static PolyvChannelRecordBatchDeleteParameterBuilder aPolyvChannelRecordBatchDeleteParameter() {
            return new PolyvChannelRecordBatchDeleteParameterBuilder();
        }

        public PolyvChannelRecordBatchDeleteParameterBuilder withFileIds(String fileIds) {
            polyvChannelRecordBatchDeleteParameter.setFileIds(fileIds);
            return this;
        }

        @Override
        public PolyvChannelRecordBatchDeleteParameter build() {
            return polyvChannelRecordBatchDeleteParameter;
        }
    }
}
