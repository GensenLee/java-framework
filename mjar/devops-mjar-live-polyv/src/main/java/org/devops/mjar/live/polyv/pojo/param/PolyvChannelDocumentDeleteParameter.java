package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.polyv.enums.DocumentType;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：删除文档
 */
@Data
public class PolyvChannelDocumentDeleteParameter extends ChannelSignBean {

    /**
     * 文件ID，(如果有多个，可以用英文逗号隔开拼接成字符串)
     */
    private String fileId;

    /**
     * 新旧版文件类型: old旧版, new新版
     */
    private String type;


    public static final class PolyvChannelDocumentDeleteParameterBuilder extends ParameterBuilder<PolyvChannelDocumentDeleteParameter> {
        private PolyvChannelDocumentDeleteParameter polyvChannelDocumentDeleteParameter;

        private PolyvChannelDocumentDeleteParameterBuilder() {
            polyvChannelDocumentDeleteParameter = new PolyvChannelDocumentDeleteParameter();
        }

        public static PolyvChannelDocumentDeleteParameterBuilder aPolyvChannelDocumentDeleteParameter() {
            return new PolyvChannelDocumentDeleteParameterBuilder();
        }

        public PolyvChannelDocumentDeleteParameterBuilder withFileId(String fileId) {
            polyvChannelDocumentDeleteParameter.setFileId(fileId);
            return this;
        }

        public PolyvChannelDocumentDeleteParameterBuilder withType(DocumentType type) {
            polyvChannelDocumentDeleteParameter.setType(type.getValue());
            return this;
        }

        @Override
        public PolyvChannelDocumentDeleteParameter build() {
            return polyvChannelDocumentDeleteParameter;
        }
    }
}
