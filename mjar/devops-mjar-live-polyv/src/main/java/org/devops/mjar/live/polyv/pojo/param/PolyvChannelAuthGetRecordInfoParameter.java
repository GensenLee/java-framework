package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @description：查询频道登记观看记录
 */
@Data
public class PolyvChannelAuthGetRecordInfoParameter extends ChannelSignBean {

    /**
     * 页数，默认为1
     */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 每页显示的数据条数，默认每页显示20条数据
     */
    @VerifyField(ignore = true)
    private Integer pageSize;

    public static final class PolyvChannelAuthGetRecordInfoParameterBuilder extends ParameterBuilder<PolyvChannelAuthGetRecordInfoParameter> {
        private PolyvChannelAuthGetRecordInfoParameter polyvChannelAuthGetRecordInfoParameter;

        private PolyvChannelAuthGetRecordInfoParameterBuilder() {
            polyvChannelAuthGetRecordInfoParameter = new PolyvChannelAuthGetRecordInfoParameter();
        }

        public static PolyvChannelAuthGetRecordInfoParameterBuilder aPolyvChannelAuthGetRecordInfoParameter() {
            return new PolyvChannelAuthGetRecordInfoParameterBuilder();
        }

        public PolyvChannelAuthGetRecordInfoParameterBuilder withPage(Integer page) {
            polyvChannelAuthGetRecordInfoParameter.setPage(page);
            return this;
        }

        public PolyvChannelAuthGetRecordInfoParameterBuilder withPageSize(Integer pageSize) {
            polyvChannelAuthGetRecordInfoParameter.setPageSize(pageSize);
            return this;
        }

        @Override
        public PolyvChannelAuthGetRecordInfoParameter build() {
            return polyvChannelAuthGetRecordInfoParameter;
        }
    }
}
