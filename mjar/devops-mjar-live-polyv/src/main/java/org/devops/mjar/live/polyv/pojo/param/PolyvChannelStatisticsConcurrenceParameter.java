package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/21 14:00
 * @description：查询频道历史并发数据
 */
@Data
public class PolyvChannelStatisticsConcurrenceParameter extends ChannelSignBean {

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




    public static final class PolyvChannelStatisticsConcurrenceParameterBuilder extends ParameterBuilder<PolyvChannelStatisticsConcurrenceParameter> {
        private PolyvChannelStatisticsConcurrenceParameter polyvChannelStatisticsConcurrenceParameter;

        private PolyvChannelStatisticsConcurrenceParameterBuilder() {
            polyvChannelStatisticsConcurrenceParameter = new PolyvChannelStatisticsConcurrenceParameter();
        }

        public static PolyvChannelStatisticsConcurrenceParameterBuilder aPolyvChannelStatisticsConcurrenceParameter() {
            return new PolyvChannelStatisticsConcurrenceParameterBuilder();
        }

        public PolyvChannelStatisticsConcurrenceParameterBuilder withStartDate(String startDate) {
            polyvChannelStatisticsConcurrenceParameter.setStartDate(startDate);
            return this;
        }

        public PolyvChannelStatisticsConcurrenceParameterBuilder withEndDate(String endDate) {
            polyvChannelStatisticsConcurrenceParameter.setEndDate(endDate);
            return this;
        }

        @Override
        public PolyvChannelStatisticsConcurrenceParameter build() {
            return polyvChannelStatisticsConcurrenceParameter;
        }
    }
}
