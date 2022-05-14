package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/21 10:32
 * @description：查询频道历史最高并发数据
 */

@Data
public class PolyvChannelStatisticsGetMaxHistoryParameter extends ChannelSignBean {

    /**
     *  直播结束时间，13位毫秒级时间戳，场次ID和直播开始结束时间必填一项
     */
    @VerifyField(ignore = true)
    private Long startTime;


    /**
     * 直播开始时间，13位毫秒级时间戳，开始时间和结束时间相隔不可以超过30天
     */
    @VerifyField(ignore = true)
    private Long endTime;


    public static final class PolyvChannelStatisticsGetMaxHistoryParameterBuilder extends ParameterBuilder<PolyvChannelStatisticsGetMaxHistoryParameter> {
        private PolyvChannelStatisticsGetMaxHistoryParameter polyvChannelStatisticsGetMaxHistoryParameter;

        private PolyvChannelStatisticsGetMaxHistoryParameterBuilder() {
            polyvChannelStatisticsGetMaxHistoryParameter = new PolyvChannelStatisticsGetMaxHistoryParameter();
        }

        public static PolyvChannelStatisticsGetMaxHistoryParameterBuilder aPolyvChannelStatisticsGetMaxHistoryParameter() {
            return new PolyvChannelStatisticsGetMaxHistoryParameterBuilder();
        }

        public PolyvChannelStatisticsGetMaxHistoryParameterBuilder withStartTime(Long startTime) {
            polyvChannelStatisticsGetMaxHistoryParameter.setStartTime(startTime);
            return this;
        }

        public PolyvChannelStatisticsGetMaxHistoryParameterBuilder withEndTime(Long endTime) {
            polyvChannelStatisticsGetMaxHistoryParameter.setEndTime(endTime);
            return this;
        }

        @Override
        public PolyvChannelStatisticsGetMaxHistoryParameter build() {
            return polyvChannelStatisticsGetMaxHistoryParameter;
        }
    }
}
