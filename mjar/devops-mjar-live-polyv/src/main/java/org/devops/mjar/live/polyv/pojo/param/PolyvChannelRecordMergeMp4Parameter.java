package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：合并直播录制文件并回调mp4下载地址
 */
@Data
public class PolyvChannelRecordMergeMp4Parameter extends ChannelSignBean {

    /**
     * 录制文件开始时间，与endTime最大不能超过8小时，为13位毫秒级时间戳
     */
    private Long startTime;

    /**
     * 录制文件结束时间，与startTime最大不能超过8小时，为13位毫秒级时间戳
     */
    private Long endTime;

    /**
     * 合并成功或失败回调的url
     */
    @VerifyField(ignore = true)
    private String callbackUrl;

    /**
     * 合并后的视频的文件名
     */
    @VerifyField(ignore = true)
    private String fileName;


    public static final class PolyvChannelRecordMergeMp4ParameterBuilder extends ParameterBuilder<PolyvChannelRecordMergeMp4Parameter>{
        private PolyvChannelRecordMergeMp4Parameter polyvChannelRecordMergeMp4Parameter;

        private PolyvChannelRecordMergeMp4ParameterBuilder() {
            polyvChannelRecordMergeMp4Parameter = new PolyvChannelRecordMergeMp4Parameter();
        }

        public static PolyvChannelRecordMergeMp4ParameterBuilder aPolyvChannelRecordMergeMp4Parameter() {
            return new PolyvChannelRecordMergeMp4ParameterBuilder();
        }

        public PolyvChannelRecordMergeMp4ParameterBuilder withStartTime(Long startTime) {
            polyvChannelRecordMergeMp4Parameter.setStartTime(startTime);
            return this;
        }

        public PolyvChannelRecordMergeMp4ParameterBuilder withEndTime(Long endTime) {
            polyvChannelRecordMergeMp4Parameter.setEndTime(endTime);
            return this;
        }

        public PolyvChannelRecordMergeMp4ParameterBuilder withCallbackUrl(String callbackUrl) {
            polyvChannelRecordMergeMp4Parameter.setCallbackUrl(callbackUrl);
            return this;
        }

        public PolyvChannelRecordMergeMp4ParameterBuilder withFileName(String fileName) {
            polyvChannelRecordMergeMp4Parameter.setFileName(fileName);
            return this;
        }

        @Override
        public PolyvChannelRecordMergeMp4Parameter build() {
            return polyvChannelRecordMergeMp4Parameter;
        }
    }
}
