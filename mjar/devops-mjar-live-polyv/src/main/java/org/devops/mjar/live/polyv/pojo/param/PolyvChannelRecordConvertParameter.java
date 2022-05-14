package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：批量转存录制视频
 */
@Data
public class PolyvChannelRecordConvertParameter extends ChannelSignBean {

    /**
     * 要转存的录制视频文件ID，多个ID用英文逗号,分隔
     */
    private String fileIds;

    /**
     *  转存后的文件名，目前暂不支持传多个文件名
     */
    @VerifyField(ignore = true)
    private String fileName;

    /**
     * 转存到点播的目录ID，默认为点播的根目录ID
     */
    @VerifyField(ignore = true)
    private String cataId;

    /**
     * 转存成功时候回调通知的url
     */
    @VerifyField(ignore = true)
    private String callbackUrl;


    public static final class PolyvChannelRecordConvertParameterBuilder extends ParameterBuilder<PolyvChannelRecordConvertParameter> {
        private PolyvChannelRecordConvertParameter polyvChannelRecordConvertParameter;

        private PolyvChannelRecordConvertParameterBuilder() {
            polyvChannelRecordConvertParameter = new PolyvChannelRecordConvertParameter();
        }

        public static PolyvChannelRecordConvertParameterBuilder aPolyvChannelRecordConvertParameter() {
            return new PolyvChannelRecordConvertParameterBuilder();
        }

        public PolyvChannelRecordConvertParameterBuilder withFileIds(String fileIds) {
            polyvChannelRecordConvertParameter.setFileIds(fileIds);
            return this;
        }

        public PolyvChannelRecordConvertParameterBuilder withFileName(String fileName) {
            polyvChannelRecordConvertParameter.setFileName(fileName);
            return this;
        }

        public PolyvChannelRecordConvertParameterBuilder withCataId(String cataId) {
            polyvChannelRecordConvertParameter.setCataId(cataId);
            return this;
        }

        public PolyvChannelRecordConvertParameterBuilder withCallbackUrl(String callbackUrl) {
            polyvChannelRecordConvertParameter.setCallbackUrl(callbackUrl);
            return this;
        }

        @Override
        public PolyvChannelRecordConvertParameter build() {
            return polyvChannelRecordConvertParameter;
        }
    }
}
