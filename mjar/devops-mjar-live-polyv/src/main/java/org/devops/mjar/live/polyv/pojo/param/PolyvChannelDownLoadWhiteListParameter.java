package org.devops.mjar.live.polyv.pojo.param;

import lombok.Data;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;

/**
 * @author bigboss
 * @description：下载频道白名单
 */
@Data
public class PolyvChannelDownLoadWhiteListParameter extends ChannelSignBean {
    /**
     * 	1为首要条件，2为次要条件。影响导出的表格表头
     */
    private Integer rank;


    public static final class PolyvChannelDownLoadWhiteListParameterBuilder extends ParameterBuilder<PolyvChannelDownLoadWhiteListParameter> {
        private PolyvChannelDownLoadWhiteListParameter polyvChannelBatchUploadWhiteListParameter;

        private PolyvChannelDownLoadWhiteListParameterBuilder() {
            polyvChannelBatchUploadWhiteListParameter = new PolyvChannelDownLoadWhiteListParameter();
        }

        public static PolyvChannelDownLoadWhiteListParameterBuilder aPolyvChannelBatchUploadWhiteListParameter() {
            return new PolyvChannelDownLoadWhiteListParameterBuilder();
        }
        public PolyvChannelDownLoadWhiteListParameterBuilder withRank(Integer rank) {
            polyvChannelBatchUploadWhiteListParameter.setRank(rank);
            return this;
        }

        @Override
        public PolyvChannelDownLoadWhiteListParameter build() {
            return polyvChannelBatchUploadWhiteListParameter;
        }
    }
}
