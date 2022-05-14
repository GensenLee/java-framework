package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：查询频道文档转码状态
 */
@Data
public class PolyvChannelDocumentStatusParameter extends ChannelSignBean {

    /**
     * 文件ID，(如果有多个，可以用英文逗号隔开拼接成字符串)
     */
    private String fileId;


    public static final class PolyvChannelDocumentStatusParameterBuilder extends ParameterBuilder<PolyvChannelDocumentStatusParameter> {
        private PolyvChannelDocumentStatusParameter polyvChannelDocumentStatusParameter;

        private PolyvChannelDocumentStatusParameterBuilder() {
            polyvChannelDocumentStatusParameter = new PolyvChannelDocumentStatusParameter();
        }

        public static PolyvChannelDocumentStatusParameterBuilder aPolyvChannelDocumentStatusParameter() {
            return new PolyvChannelDocumentStatusParameterBuilder();
        }

        public PolyvChannelDocumentStatusParameterBuilder withFileId(String fileId) {
            polyvChannelDocumentStatusParameter.setFileId(fileId);
            return this;
        }

        @Override
        public PolyvChannelDocumentStatusParameter build() {
            return polyvChannelDocumentStatusParameter;
        }
    }
}
