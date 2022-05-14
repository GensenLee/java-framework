package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/26 16:20
 * @description：查询频道登记观看设置的字段信息
 */
@Data
public class PolyvChannelSearchRecordFieldParameter extends ChannelSignBean {

    /**
     * 1为首要条件，2为次要条件
     */
    private Integer rank;

    public static final class PolyvChannelSearchRecordFieldParameterBuilder extends ParameterBuilder<PolyvChannelSearchRecordFieldParameter> {
        private PolyvChannelSearchRecordFieldParameter polyvChannelSearchRecordFieldParameter;

        private PolyvChannelSearchRecordFieldParameterBuilder() {
            polyvChannelSearchRecordFieldParameter = new PolyvChannelSearchRecordFieldParameter();
        }

        public static PolyvChannelSearchRecordFieldParameterBuilder aPolyvChannelSearchRecordFieldParameter() {
            return new PolyvChannelSearchRecordFieldParameterBuilder();
        }

        public PolyvChannelSearchRecordFieldParameterBuilder withRank(Integer rank) {
            polyvChannelSearchRecordFieldParameter.setRank(rank);
            return this;
        }
        @Override
        public PolyvChannelSearchRecordFieldParameter build() {
            return polyvChannelSearchRecordFieldParameter;
        }
    }
}
