package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/21 10:32
 * @description：查询多个频道概览统计数据
 */
@Data
public class PolyvAppStatisticsChannelSummaryParameter extends AppSignBean {

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
     *  要查询的频道号，多个频道号以英文逗号“,”分开
     */
    @VerifyField(ignore = true)
    private String channelIds;


    public static final class PolyvChannelStatisticsChannelSummaryBuilder extends ParameterBuilder<PolyvAppStatisticsChannelSummaryParameter> {
        private PolyvAppStatisticsChannelSummaryParameter polyvChannelStatisticsChannelSummary;

        private PolyvChannelStatisticsChannelSummaryBuilder() {
            polyvChannelStatisticsChannelSummary = new PolyvAppStatisticsChannelSummaryParameter();
        }

        public static PolyvChannelStatisticsChannelSummaryBuilder aPolyvChannelStatisticsChannelSummary() {
            return new PolyvChannelStatisticsChannelSummaryBuilder();
        }

        public PolyvChannelStatisticsChannelSummaryBuilder withStartDate(String startDate) {
            polyvChannelStatisticsChannelSummary.setStartDate(startDate);
            return this;
        }

        public PolyvChannelStatisticsChannelSummaryBuilder withEndDate(String endDate) {
            polyvChannelStatisticsChannelSummary.setEndDate(endDate);
            return this;
        }

        public PolyvChannelStatisticsChannelSummaryBuilder withChannelIds(String channelIds) {
            polyvChannelStatisticsChannelSummary.setChannelIds(channelIds);
            return this;
        }

        @Override
        public PolyvAppStatisticsChannelSummaryParameter build() {
            return polyvChannelStatisticsChannelSummary;
        }
    }
}
