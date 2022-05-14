package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.AutoConvert;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：裁剪录制文件
 */
@Data
public class PolyvChannelRecordClipParameter extends ChannelSignBean {

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 要裁剪移除的时间区间, start、end值是单位为秒的数值，格式为：[{\"start\":xx, \"end\":xx},{\"start\":xx, \"end\":xx}]
     */
    private String deleteTimeFrame;

    /**
     *  裁剪成功或失败回调的url
     */
    @VerifyField(ignore = true)
    private String callbackUrl;

    /**
     * 是否自动转存回放到点播，默认为N, Y是, N否
     */
    @VerifyField(ignore = true)
    private String autoConvert;

    /**
     * 裁剪后文件名
     */
    @VerifyField(ignore = true)
    private String fileName;


    public static final class PolyvChannelRecordClipParameterBuilder extends ParameterBuilder<PolyvChannelRecordClipParameter> {
        private PolyvChannelRecordClipParameter polyvChannelRecordClipParameter;

        private PolyvChannelRecordClipParameterBuilder() {
            polyvChannelRecordClipParameter = new PolyvChannelRecordClipParameter();
        }

        public static PolyvChannelRecordClipParameterBuilder aPolyvChannelRecordClipParameter() {
            return new PolyvChannelRecordClipParameterBuilder();
        }

        public PolyvChannelRecordClipParameterBuilder withFileId(String fileId) {
            polyvChannelRecordClipParameter.setFileId(fileId);
            return this;
        }

        public PolyvChannelRecordClipParameterBuilder withDeleteTimeFrame(String deleteTimeFrame) {
            polyvChannelRecordClipParameter.setDeleteTimeFrame(deleteTimeFrame);
            return this;
        }

        public PolyvChannelRecordClipParameterBuilder withCallbackUrl(String callbackUrl) {
            polyvChannelRecordClipParameter.setCallbackUrl(callbackUrl);
            return this;
        }

        public PolyvChannelRecordClipParameterBuilder withAutoConvert(AutoConvert autoConvert) {
            polyvChannelRecordClipParameter.setAutoConvert(autoConvert.getCode());
            return this;
        }

        public PolyvChannelRecordClipParameterBuilder withFileName(String fileName) {
            polyvChannelRecordClipParameter.setFileName(fileName);
            return this;
        }

        @Override
        public PolyvChannelRecordClipParameter build() {
            return polyvChannelRecordClipParameter;
        }
    }
}
