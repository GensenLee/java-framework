package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：分页获取直播暂存列表
 */
@Data
public class PolyvChannelRecordListParameter extends ChannelSignBean {

    /**
     * 页数，默认为1
     */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 每页显示的数据条数，默认每页显示20条数据
     */
    @VerifyField(ignore = true)
    private Integer limit;


    public static final class PolyvChannelRecordListParameterBuilder extends ParameterBuilder<PolyvChannelRecordListParameter> {
        private PolyvChannelRecordListParameter polyvChannelRecordListParameter;

        private PolyvChannelRecordListParameterBuilder() {
            polyvChannelRecordListParameter = new PolyvChannelRecordListParameter();
        }

        public static PolyvChannelRecordListParameterBuilder aPolyvChannelRecordListParameter() {
            return new PolyvChannelRecordListParameterBuilder();
        }

        public PolyvChannelRecordListParameterBuilder withPage(Integer page) {
            polyvChannelRecordListParameter.setPage(page);
            return this;
        }

        public PolyvChannelRecordListParameterBuilder withLimit(Integer limit) {
            polyvChannelRecordListParameter.setLimit(limit);
            return this;
        }

        @Override
        public PolyvChannelRecordListParameter build() {
            return polyvChannelRecordListParameter;
        }
    }
}
