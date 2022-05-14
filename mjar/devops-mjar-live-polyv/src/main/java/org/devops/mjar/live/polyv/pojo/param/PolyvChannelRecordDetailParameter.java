package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：根据fileId查询录制视频信息
 */
@Data
public class PolyvChannelRecordDetailParameter extends ChannelSignBean {

    /**
     * 文件ID
     */
    private String fileId;


    public static final class PolyvChannelRecordDetailParameterBuilder extends ParameterBuilder<PolyvChannelRecordDetailParameter> {
        private PolyvChannelRecordDetailParameter polyvChannelRecordDetailParameter;

        private PolyvChannelRecordDetailParameterBuilder() {
            polyvChannelRecordDetailParameter = new PolyvChannelRecordDetailParameter();
        }

        public static PolyvChannelRecordDetailParameterBuilder aPolyvChannelRecordDetailParameter() {
            return new PolyvChannelRecordDetailParameterBuilder();
        }

        public PolyvChannelRecordDetailParameterBuilder withFileId(String fileId) {
            polyvChannelRecordDetailParameter.setFileId(fileId);
            return this;
        }

        @Override
        public PolyvChannelRecordDetailParameter build() {
            return polyvChannelRecordDetailParameter;
        }
    }
}
