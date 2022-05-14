package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/31 17:56
 * @description：查询多个频道统计数据
 */
@Data
public class PolyvAppStatisticsSummaryParameter extends AppSignBean {

    /**
     * 查询的开始日期 格式为yyyy-MM-dd
     */
    private String startDate;
    /**
     * 查询的结束日期 格式为yyyy-MM-dd
     */
    private String endDate;
    /**
     * 要查询的频道号，不提交默认为查询所有频道，多个频道号以英文逗号“,”分开，如：105420,104400
     */
    @VerifyField(ignore = true)
    private String channelIds;

    @SignIgnore
    @VerifyField(ignore = true)
    private String userId;

    public static final class PolypAppStatisticsSummaryParameterBuilder extends ParameterBuilder<PolyvAppStatisticsSummaryParameter> {
        private PolyvAppStatisticsSummaryParameter polyvAppStatisticsSummaryParameter;

        private PolypAppStatisticsSummaryParameterBuilder() {
            polyvAppStatisticsSummaryParameter = new PolyvAppStatisticsSummaryParameter();
        }

        public static PolypAppStatisticsSummaryParameterBuilder aPolypAppStatisticsSummaryParameter() {
            return new PolypAppStatisticsSummaryParameterBuilder();
        }

        public PolypAppStatisticsSummaryParameterBuilder withStartDate(String startDate) {
            polyvAppStatisticsSummaryParameter.setStartDate(startDate);
            return this;
        }

        public PolypAppStatisticsSummaryParameterBuilder withEndDate(String endDate) {
            polyvAppStatisticsSummaryParameter.setEndDate(endDate);
            return this;
        }

        public PolypAppStatisticsSummaryParameterBuilder withChannelIds(String channelIds) {
            polyvAppStatisticsSummaryParameter.setChannelIds(channelIds);
            return this;
        }

        @Override
        public PolyvAppStatisticsSummaryParameter build() {
            return polyvAppStatisticsSummaryParameter;
        }
    }
}
