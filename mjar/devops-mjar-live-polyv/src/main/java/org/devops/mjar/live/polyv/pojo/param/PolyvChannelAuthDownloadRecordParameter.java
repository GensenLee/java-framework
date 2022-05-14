package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @description：下载频道登记观看记录
 */
@Data
public class PolyvChannelAuthDownloadRecordParameter extends ChannelSignBean {

    /**
     * 1为首要条件，2为次要条件。影响导出的表格表头
     */
    @VerifyField(ignore = true)
    private Integer rank;

    public static final class PolyvChannelAuthDownloadRecordParameterBuilder extends ParameterBuilder<PolyvChannelAuthDownloadRecordParameter> {
        private PolyvChannelAuthDownloadRecordParameter polyvChannelAuthDownloadRecordParameter;

        private PolyvChannelAuthDownloadRecordParameterBuilder() {
            polyvChannelAuthDownloadRecordParameter = new PolyvChannelAuthDownloadRecordParameter();
        }

        public static PolyvChannelAuthDownloadRecordParameterBuilder aPolyvChannelAuthDownloadRecordParameter() {
            return new PolyvChannelAuthDownloadRecordParameterBuilder();
        }

        public PolyvChannelAuthDownloadRecordParameterBuilder withRank(Integer rank) {
            polyvChannelAuthDownloadRecordParameter.setRank(rank);
            return this;
        }

        @Override
        public PolyvChannelAuthDownloadRecordParameter build() {
            return polyvChannelAuthDownloadRecordParameter;
        }
    }
}
