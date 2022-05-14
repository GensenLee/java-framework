package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/21 18:15
 * @description：查询频道问卷结果
 */

@Data
public class PolyvChannelQuestionnaireRecordParameter extends ChannelSignBean {

    /**
     * 问卷id
     */
    @VerifyField(ignore = true)
    private String questionnaireId;

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


    public static final class PolyvChannelQuestionnaireRecordParameterBuilder extends ParameterBuilder<PolyvChannelQuestionnaireRecordParameter> {
        private PolyvChannelQuestionnaireRecordParameter polyvChannelQuestionnaireRecordParameter;

        private PolyvChannelQuestionnaireRecordParameterBuilder() {
            polyvChannelQuestionnaireRecordParameter = new PolyvChannelQuestionnaireRecordParameter();
        }

        public static PolyvChannelQuestionnaireRecordParameterBuilder aPolyvChannelQuestionnaireRecordParameter() {
            return new PolyvChannelQuestionnaireRecordParameterBuilder();
        }

        public PolyvChannelQuestionnaireRecordParameterBuilder withQuestionnaireId(String questionnaireId) {
            polyvChannelQuestionnaireRecordParameter.setQuestionnaireId(questionnaireId);
            return this;
        }

        public PolyvChannelQuestionnaireRecordParameterBuilder withStartDate(String startDay) {
            polyvChannelQuestionnaireRecordParameter.setStartDate(startDay);
            return this;
        }

        public PolyvChannelQuestionnaireRecordParameterBuilder withEndDate(String endDay) {
            polyvChannelQuestionnaireRecordParameter.setEndDate(endDay);
            return this;
        }

        @Override
        public PolyvChannelQuestionnaireRecordParameter build() {
            return polyvChannelQuestionnaireRecordParameter;
        }
    }
}
