package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/12/23 15:16
 * @description：直播场次列表
 */
@Data
public class PolyvChannelSessionListParameter extends ChannelSignBean {

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

    /**
     * 开始日期，格式YYYY-MM-DD
     */
    @VerifyField(ignore = true)
    private String startDate;

    /**
     * 结束日期，格式YYYY-MM-DD
     */
    @VerifyField(ignore = true)
    private String endDate;


    public static final class PolyvChannelSessionListParameterBuilder extends ParameterBuilder<PolyvChannelSessionListParameter> {
        private PolyvChannelSessionListParameter polyvChannelSessionListParameter;

        private PolyvChannelSessionListParameterBuilder() {
            polyvChannelSessionListParameter = new PolyvChannelSessionListParameter();
        }

        public static PolyvChannelSessionListParameterBuilder aPolyvChannelSessionListParameter() {
            return new PolyvChannelSessionListParameterBuilder();
        }

        public PolyvChannelSessionListParameterBuilder withPage(Integer page) {
            polyvChannelSessionListParameter.setPage(page);
            return this;
        }

        public PolyvChannelSessionListParameterBuilder withPageSize(Integer pageSize) {
            polyvChannelSessionListParameter.setPageSize(pageSize);
            return this;
        }

        public PolyvChannelSessionListParameterBuilder withStartDate(String startDate) {
            polyvChannelSessionListParameter.setStartDate(startDate);
            return this;
        }

        public PolyvChannelSessionListParameterBuilder withEndDate(String endDate) {
            polyvChannelSessionListParameter.setEndDate(endDate);
            return this;
        }

        @Override
        public PolyvChannelSessionListParameter build() {
            return polyvChannelSessionListParameter;
        }
    }
}
