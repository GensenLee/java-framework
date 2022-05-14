package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：删除直播暂存中的录制视频
 */
@Data
public class PolyvChannelRecordDeleteParameter extends ChannelSignBean {

    /**
     * 录制视频的场次ID
     */
    @VerifyField(ignore = true)
    private String sessionId;

    /**
     * 录制视频的开始录制时间，格式为：yyyyMMddHHmmss，如：20210317181043
     */
    @VerifyField(ignore = true)
    private Long startTime;


    public static final class PolyvChannelRecordDeleteParameterBuilder extends ParameterBuilder<PolyvChannelRecordDeleteParameter> {
        private PolyvChannelRecordDeleteParameter polyvChannelRecordDeleteParameter;

        private PolyvChannelRecordDeleteParameterBuilder() {
            polyvChannelRecordDeleteParameter = new PolyvChannelRecordDeleteParameter();
        }

        public static PolyvChannelRecordDeleteParameterBuilder aPolyvChannelRecordDeleteParameter() {
            return new PolyvChannelRecordDeleteParameterBuilder();
        }

        public PolyvChannelRecordDeleteParameterBuilder withSessionId(String sessionId) {
            polyvChannelRecordDeleteParameter.setSessionId(sessionId);
            return this;
        }

        public PolyvChannelRecordDeleteParameterBuilder withStartTime(Long startTime) {
            polyvChannelRecordDeleteParameter.setStartTime(startTime);
            return this;
        }

        @Override
        public PolyvChannelRecordDeleteParameter build() {
            return polyvChannelRecordDeleteParameter;
        }
    }
}
