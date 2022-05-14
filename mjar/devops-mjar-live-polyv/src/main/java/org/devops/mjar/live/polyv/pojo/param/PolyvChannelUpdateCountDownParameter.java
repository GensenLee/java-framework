package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：修改直播倒计时设置
 */
@Data
public class PolyvChannelUpdateCountDownParameter extends ChannelSignBean {

    /**
     *  预约观看开关,默认不改变原来状态, Y开启, N关闭
     */
    @VerifyField(ignore = true)
    private String bookingEnabled;

    /**
     * 直播开始时间，如果不传该值，表示不显示直播时间和倒计时，格式：yyyy-MM-dd HH:mm:ss
     */
    @VerifyField(ignore = true)
    private Long startTime;


    public static final class PolyvChannelUpdateCountDownParameterBuilder extends ParameterBuilder<PolyvChannelUpdateCountDownParameter> {
        private PolyvChannelUpdateCountDownParameter polyvChannelUpdateCountDownParameter;

        private PolyvChannelUpdateCountDownParameterBuilder() {
            polyvChannelUpdateCountDownParameter = new PolyvChannelUpdateCountDownParameter();
        }

        public static PolyvChannelUpdateCountDownParameterBuilder aPolyvChannelUpdateCountDownParameter() {
            return new PolyvChannelUpdateCountDownParameterBuilder();
        }

        public PolyvChannelUpdateCountDownParameterBuilder withBookingEnabled(EnableSetting bookingEnabled) {
            polyvChannelUpdateCountDownParameter.setBookingEnabled(bookingEnabled.getValue());
            return this;
        }

        public PolyvChannelUpdateCountDownParameterBuilder withStartTime(Long startTime) {
            polyvChannelUpdateCountDownParameter.setStartTime(startTime);
            return this;
        }

        @Override
        public PolyvChannelUpdateCountDownParameter build() {
            return polyvChannelUpdateCountDownParameter;
        }
    }
}
