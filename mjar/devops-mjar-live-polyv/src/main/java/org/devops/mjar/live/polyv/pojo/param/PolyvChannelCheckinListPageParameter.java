package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/22 10:44
 * @description：查询频道签到结果
 */
@Data
public class PolyvChannelCheckinListPageParameter extends ChannelSignBean {


    /*
        日期，格式：yyyy-MM-dd
     */
    @VerifyField(ignore = true)
    private String date;

    /*
        页数，默认为1
     */
    @VerifyField(ignore = true)
    private Integer page;

    /*
        每页显示的数据条数，默认10
     */
    @VerifyField(ignore = true)
    private Integer pageSize;

    /*
        场次号
     */
    @VerifyField(ignore = true)
    private String sessionId;


    public static final class PolyvChannelCheckinListPageParameterBuilder extends ParameterBuilder<PolyvChannelCheckinListPageParameter> {
        private PolyvChannelCheckinListPageParameter polyvChannelCheckinListPageParameter;

        private PolyvChannelCheckinListPageParameterBuilder() {
            polyvChannelCheckinListPageParameter = new PolyvChannelCheckinListPageParameter();
        }

        public static PolyvChannelCheckinListPageParameterBuilder aPolyvChannelCheckinListPageParameter() {
            return new PolyvChannelCheckinListPageParameterBuilder();
        }

        public PolyvChannelCheckinListPageParameterBuilder withDate(String date) {
            polyvChannelCheckinListPageParameter.setDate(date);
            return this;
        }

        public PolyvChannelCheckinListPageParameterBuilder withPage(Integer page) {
            polyvChannelCheckinListPageParameter.setPage(page);
            return this;
        }

        public PolyvChannelCheckinListPageParameterBuilder withPageSize(Integer pageSize) {
            polyvChannelCheckinListPageParameter.setPageSize(pageSize);
            return this;
        }

        public PolyvChannelCheckinListPageParameterBuilder withSessionId(String sessionId) {
            polyvChannelCheckinListPageParameter.setSessionId(sessionId);
            return this;
        }

        @Override
        public PolyvChannelCheckinListPageParameter build() {
            return polyvChannelCheckinListPageParameter;
        }
    }
}
