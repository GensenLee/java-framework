package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/3/1 15:20
 * @description：频道列表
 */
@Data
public class PolyvAppSearchChannelParameter extends AppSignBean {

    /**
     * 页数
     */
    @VerifyField(ignore = true)
    private Integer page;
    @VerifyField(ignore = true)
    /**
     * 每页显示的数据条数，默认每页显示1000条数据
     */
    private Integer pageSize;

    /**
     * 创建日期，格式：yyyy-MM-dd
     */
    @VerifyField(ignore = true)
    private String startDate;
    /**
     * 结束日期，格式：yyyy-MM-dd
     */
    @VerifyField(ignore = true)
    private String endDate;
    /**
     * 频道号
     */
    @VerifyField(ignore = true)
    private String channelIds;
    /**
     * 频道名称
     */
    @VerifyField(ignore = true)
    private String channelName;
    /**
     * 频道状态,取值：live（直播中）、end（直播结束）、playback（回放中）、waiting（等待直播），全部状态传空值
     */
    @VerifyField(ignore = true)
    private String status;


    public static final class PolyvAppSearchChannelParameterBuilder extends ParameterBuilder<PolyvAppSearchChannelParameter> {
        private PolyvAppSearchChannelParameter polyvAppSearchChannelParameter;

        private PolyvAppSearchChannelParameterBuilder() {
            polyvAppSearchChannelParameter = new PolyvAppSearchChannelParameter();
        }

        public static PolyvAppSearchChannelParameterBuilder aPolyvAppSearchChannelParameter() {
            return new PolyvAppSearchChannelParameterBuilder();
        }

        public PolyvAppSearchChannelParameterBuilder withPage(Integer page) {
            polyvAppSearchChannelParameter.setPage(page);
            return this;
        }

        public PolyvAppSearchChannelParameterBuilder withPageSize(Integer pageSize) {
            polyvAppSearchChannelParameter.setPageSize(pageSize);
            return this;
        }

        public PolyvAppSearchChannelParameterBuilder withStartDate(String startDate) {
            polyvAppSearchChannelParameter.setStartDate(startDate);
            return this;
        }

        public PolyvAppSearchChannelParameterBuilder withEndDate(String endDate) {
            polyvAppSearchChannelParameter.setEndDate(endDate);
            return this;
        }

        public PolyvAppSearchChannelParameterBuilder withChannelIds(String channelIds) {
            polyvAppSearchChannelParameter.setChannelIds(channelIds);
            return this;
        }

        public PolyvAppSearchChannelParameterBuilder withChannelName(String channelName) {
            polyvAppSearchChannelParameter.setChannelName(channelName);
            return this;
        }

        public PolyvAppSearchChannelParameterBuilder withStatus(String status) {
            polyvAppSearchChannelParameter.setStatus(status);
            return this;
        }

        @Override
        public PolyvAppSearchChannelParameter build() {
            return polyvAppSearchChannelParameter;
        }
    }
}
