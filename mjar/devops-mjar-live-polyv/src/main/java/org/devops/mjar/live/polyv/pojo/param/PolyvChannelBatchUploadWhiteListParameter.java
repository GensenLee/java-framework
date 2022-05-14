package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author bigboss
 * @description：批量导入频道白名单
 */
@Data
public class PolyvChannelBatchUploadWhiteListParameter extends ChannelSignBean {

    /**
     * 白名单文件
     */
    @SignIgnore
    private MultipartFile file;

    /**
     * 	1为首要条件，2为次要条件。影响导出的表格表头
     */
    private Integer rank;


    public static final class PolyvChannelBatchUploadWhiteListParameterBuilder extends ParameterBuilder<PolyvChannelBatchUploadWhiteListParameter> {
        private PolyvChannelBatchUploadWhiteListParameter polyvChannelBatchUploadWhiteListParameter;

        private PolyvChannelBatchUploadWhiteListParameterBuilder() {
            polyvChannelBatchUploadWhiteListParameter = new PolyvChannelBatchUploadWhiteListParameter();
        }

        public static PolyvChannelBatchUploadWhiteListParameterBuilder aPolyvChannelBatchUploadWhiteListParameter() {
            return new PolyvChannelBatchUploadWhiteListParameterBuilder();
        }

        public PolyvChannelBatchUploadWhiteListParameterBuilder withFile(MultipartFile file) {
            System.out.println(file);
            polyvChannelBatchUploadWhiteListParameter.setFile(file);
            return this;
        }

        public PolyvChannelBatchUploadWhiteListParameterBuilder withRank(Integer rank) {
            polyvChannelBatchUploadWhiteListParameter.setRank(rank);
            return this;
        }

        @Override
        public PolyvChannelBatchUploadWhiteListParameter build() {
            return polyvChannelBatchUploadWhiteListParameter;
        }
    }
}
