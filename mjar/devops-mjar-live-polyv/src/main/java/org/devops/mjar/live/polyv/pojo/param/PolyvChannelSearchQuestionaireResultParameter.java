package org.devops.mjar.live.polyv.pojo.param;


import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * 查询频道答题卡结果
 */

@Data
public class PolyvChannelSearchQuestionaireResultParameter extends ChannelSignBean {

    @VerifyField(ignore = true)
    private String startDate;
    @VerifyField(ignore = true)
    private String endDate;


    public static final class PolyvChannelSearchQuestionaireResultParameterBuilder extends ParameterBuilder<PolyvChannelSearchQuestionaireResultParameter> {
        private PolyvChannelSearchQuestionaireResultParameter polyvChannelSearchQuestionaireResultParameter;

        private PolyvChannelSearchQuestionaireResultParameterBuilder() {
            polyvChannelSearchQuestionaireResultParameter = new PolyvChannelSearchQuestionaireResultParameter();
        }

        public static PolyvChannelSearchQuestionaireResultParameterBuilder aPolyvChannelSearchQuestionaireResultParameter() {
            return new PolyvChannelSearchQuestionaireResultParameterBuilder();
        }

        public PolyvChannelSearchQuestionaireResultParameterBuilder withStartDate(String startDate) {
            polyvChannelSearchQuestionaireResultParameter.setStartDate(startDate);
            return this;
        }

        public PolyvChannelSearchQuestionaireResultParameterBuilder withEndDate(String endDate) {
            polyvChannelSearchQuestionaireResultParameter.setEndDate(endDate);
            return this;
        }

        @Override
        public PolyvChannelSearchQuestionaireResultParameter build() {
            return polyvChannelSearchQuestionaireResultParameter;
        }
    }
}
