package org.devops.mjar.live.polyv.pojo.param;


import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.AutoConvert;
import org.devops.mjar.live.polyv.enums.MergeType;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：合并直播录制
 */
@Data
public class PolyvChannelRecordMergeParameter extends ChannelSignBean {

    /**
     * 要合并的录制视频文件ID，多个id用英文逗号, 分隔
     */
    private String fileIds;

    /**
     * 合并后的视频的文件名
     */
    @VerifyField(ignore = true)
    private String fileName;

    /**
     * 合并成功或失败回调的url，可以带上自定义参数
     */
    @VerifyField(ignore = true)
    private String callbackUrl;

    /**
     * 是否自动转存到点播，默认为N, Y：自动转存到对应点播分类下（点播保存路径：直播回放-频道号-场次）, N：不自动转存
     */
    @VerifyField(ignore = true)
    private String autoConvert;

    /**
     * 合并后文件类型，默认为N, Y：合并为MP4文件, N：合并为m3u8文件
     */
    @VerifyField(ignore = true)
    private String mergeMp4;


    public static final class PolyvChannelRecordMergeParameterBuilder extends ParameterBuilder<PolyvChannelRecordMergeParameter> {
        private PolyvChannelRecordMergeParameter polyvChannelRecordMergeParameter;

        private PolyvChannelRecordMergeParameterBuilder() {
            polyvChannelRecordMergeParameter = new PolyvChannelRecordMergeParameter();
        }

        public static PolyvChannelRecordMergeParameterBuilder aPolyvChannelRecordMergeParameter() {
            return new PolyvChannelRecordMergeParameterBuilder();
        }

        public PolyvChannelRecordMergeParameterBuilder withFileIds(String fileIds) {
            polyvChannelRecordMergeParameter.setFileIds(fileIds);
            return this;
        }

        public PolyvChannelRecordMergeParameterBuilder withFileName(String fileName) {
            polyvChannelRecordMergeParameter.setFileName(fileName);
            return this;
        }

        public PolyvChannelRecordMergeParameterBuilder withCallbackUrl(String callbackUrl) {
            polyvChannelRecordMergeParameter.setCallbackUrl(callbackUrl);
            return this;
        }

        public PolyvChannelRecordMergeParameterBuilder withAutoConvert(AutoConvert autoConvert) {
            polyvChannelRecordMergeParameter.setAutoConvert(autoConvert.getCode());
            return this;
        }

        public PolyvChannelRecordMergeParameterBuilder withMergeMp4(MergeType mergeMp4) {
            polyvChannelRecordMergeParameter.setMergeMp4(mergeMp4.getCode());
            return this;
        }

        @Override
        public PolyvChannelRecordMergeParameter build() {
            return polyvChannelRecordMergeParameter;
        }
    }
}
