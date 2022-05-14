package org.devops.mjar.live.polyv.pojo.param;

import lombok.Data;
import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;

/**
 * @author GENSEN
 * @date 2020/11/10 18:31
 * @description：连麦日志请求
 */
@Data
public class PolyvChannelMicLogListParameter extends ChannelSignBean {

    /**
     * 页数，默认为1
    */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 每页大小，默认500条，最大5000条，超过5000条可以分多批拉取，每次page加1，直到返回列表contents为空为止
     */
    @VerifyField(ignore = true)
    private Integer pageSize;

    /**
     * 开始日期, 日期格式：yyyy-MM-dd，如2020-10-01，时间范围不能超过30天
     */
    private String startDate;

    /**
     * 结束日期, 日期格式：yyyy-MM-dd，如2020-10-01，时间范围不能超过30天
     */
    private String endDate;


    public static final class PolyvChannelMicLogListParameterBuilder extends ParameterBuilder<PolyvChannelMicLogListParameter> {
        private PolyvChannelMicLogListParameter polyvChannelMicLogListParameter;

        private PolyvChannelMicLogListParameterBuilder() {
            polyvChannelMicLogListParameter = new PolyvChannelMicLogListParameter();
        }

        public static PolyvChannelMicLogListParameterBuilder aPolyvChannelMicLogListParameter() {
            return new PolyvChannelMicLogListParameterBuilder();
        }

        public PolyvChannelMicLogListParameterBuilder withPage(Integer page) {
            polyvChannelMicLogListParameter.setPage(page);
            return this;
        }

        public PolyvChannelMicLogListParameterBuilder withPageSize(Integer pageSize) {
            polyvChannelMicLogListParameter.setPageSize(pageSize);
            return this;
        }

        public PolyvChannelMicLogListParameterBuilder withStartDate(String startDate) {
            polyvChannelMicLogListParameter.setStartDate(startDate);
            return this;
        }

        public PolyvChannelMicLogListParameterBuilder withEndDate(String endDate) {
            polyvChannelMicLogListParameter.setEndDate(endDate);
            return this;
        }

        @Override
        public PolyvChannelMicLogListParameter build() {
            return polyvChannelMicLogListParameter;
        }
    }
}
