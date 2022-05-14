package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/21 18:15
 * @description：分页查询频道问卷结果
 */
@Data
public class PolyvChannelQuestionnaireRecordListParameter extends ChannelSignBean {


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
     * 页数，默认为1
     */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 每页显示的数据条数，默认10
     */
    @VerifyField(ignore = true)
    private Integer pageSize;


    public static final class PolyvChannelQuestionnaireRecordParameterListBuilder extends ParameterBuilder<PolyvChannelQuestionnaireRecordListParameter> {
        private PolyvChannelQuestionnaireRecordListParameter polyvChannelQuestionnaireRecordParameter;

        private PolyvChannelQuestionnaireRecordParameterListBuilder() {
            polyvChannelQuestionnaireRecordParameter = new PolyvChannelQuestionnaireRecordListParameter();
        }

        public static PolyvChannelQuestionnaireRecordParameterListBuilder aPolyvChannelQuestionnaireRecordParameter() {
            return new PolyvChannelQuestionnaireRecordParameterListBuilder();
        }


        public PolyvChannelQuestionnaireRecordParameterListBuilder withStartDate(String startDay) {
            polyvChannelQuestionnaireRecordParameter.setStartDate(startDay);
            return this;
        }

        public PolyvChannelQuestionnaireRecordParameterListBuilder withEndDate(String endDay) {
            polyvChannelQuestionnaireRecordParameter.setEndDate(endDay);
            return this;
        }
        public PolyvChannelQuestionnaireRecordParameterListBuilder withPage(Integer page) {
            polyvChannelQuestionnaireRecordParameter.setPage(page);
            return this;
        }
        public PolyvChannelQuestionnaireRecordParameterListBuilder withPageSize(Integer pageSize) {
            polyvChannelQuestionnaireRecordParameter.setPageSize(pageSize);
            return this;
        }


        @Override
        public PolyvChannelQuestionnaireRecordListParameter build() {
            return polyvChannelQuestionnaireRecordParameter;
        }
    }
}
