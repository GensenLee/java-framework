package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/21 10:32
 * @description：查询频道某段时间的直播观看详情数据
 */
@Data
public class PolyvChannelStatisticsSummaryParameter extends ChannelSignBean {

    /**
     * 开始日期，格式：yyyy-MM-dd
     */
    @VerifyField(ignore = true)
    private String startDay;

    /**
     *  结束日期，格式：yyyy-MM-dd
     */
    @VerifyField(ignore = true)
    private String endDay;

    public static final class PolyvChannelStatisticsSummaryParamterBuilder extends ParameterBuilder<PolyvChannelStatisticsSummaryParameter> {
        private PolyvChannelStatisticsSummaryParameter polyvChannelStatisticsSummaryParameter;

        private PolyvChannelStatisticsSummaryParamterBuilder() {
            polyvChannelStatisticsSummaryParameter = new PolyvChannelStatisticsSummaryParameter();
        }

        public static PolyvChannelStatisticsSummaryParamterBuilder aPolyvChannelStatisticsSummaryParamter() {
            return new PolyvChannelStatisticsSummaryParamterBuilder();
        }

        public PolyvChannelStatisticsSummaryParamterBuilder withStartDay(String startDate) {
            polyvChannelStatisticsSummaryParameter.setStartDay(startDate);
            return this;
        }

        public PolyvChannelStatisticsSummaryParamterBuilder withEndDay(String endDate) {
            polyvChannelStatisticsSummaryParameter.setEndDay(endDate);
            return this;
        }

        @Override
        public PolyvChannelStatisticsSummaryParameter build() {
            return polyvChannelStatisticsSummaryParameter;
        }
    }
}
