package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：查询频道录制视频信息
 */
@Data
public class PolyvChannelRecordFilesParameter extends ChannelSignBean {

    /**
     * 开始日期，格式：yyyy-MM-dd
     */
    @VerifyField(ignore = true)
    private String startDate;

    /**
     * 结束日期，格式：yyyy-MM-dd
     */
    @VerifyField(ignore = true)
    private String endDate;

    /**
     * 直播的场次ID
     */
    @VerifyField(ignore = true)
    private String sessionId;


    public static final class PolyvChannelRecordFilesParameterBuilder extends ParameterBuilder<PolyvChannelRecordFilesParameter>{
        private PolyvChannelRecordFilesParameter polyvChannelRecordFilesParameter;

        private PolyvChannelRecordFilesParameterBuilder() {
            polyvChannelRecordFilesParameter = new PolyvChannelRecordFilesParameter();
        }

        public static PolyvChannelRecordFilesParameterBuilder aPolyvChannelRecordFilesParameter() {
            return new PolyvChannelRecordFilesParameterBuilder();
        }

        public PolyvChannelRecordFilesParameterBuilder withStartDate(String startDate) {
            polyvChannelRecordFilesParameter.setStartDate(startDate);
            return this;
        }

        public PolyvChannelRecordFilesParameterBuilder withEndDate(String endDate) {
            polyvChannelRecordFilesParameter.setEndDate(endDate);
            return this;
        }

        public PolyvChannelRecordFilesParameterBuilder withSessionId(String sessionId) {
            polyvChannelRecordFilesParameter.setSessionId(sessionId);
            return this;
        }

        @Override
        public PolyvChannelRecordFilesParameter build() {
            return polyvChannelRecordFilesParameter;
        }
    }
}
