package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/12/12 13:50
 * @description：问卷请求
 */
@Data
public class PolyvChannelQuestionnaireDetailParameter extends ChannelSignBean {

    /**
     * 问卷id
     */
    @VerifyField("问卷id")
    private String questionnaireId;


    public static final class PolyvChannelQuestionnaireDetailParameterBuilder extends ParameterBuilder<PolyvChannelQuestionnaireDetailParameter> {
        private PolyvChannelQuestionnaireDetailParameter polyvChannelQuestionnaireDetailParameter;

        private PolyvChannelQuestionnaireDetailParameterBuilder() {
            polyvChannelQuestionnaireDetailParameter = new PolyvChannelQuestionnaireDetailParameter();
        }

        public static PolyvChannelQuestionnaireDetailParameterBuilder aPolyvChannelQuestionnaireDetailParameter() {
            return new PolyvChannelQuestionnaireDetailParameterBuilder();
        }

        public PolyvChannelQuestionnaireDetailParameterBuilder withQuestionnaireId(String questionnaireId) {
            polyvChannelQuestionnaireDetailParameter.setQuestionnaireId(questionnaireId);
            return this;
        }

        @Override
        public PolyvChannelQuestionnaireDetailParameter build() {
            return polyvChannelQuestionnaireDetailParameter;
        }
    }
}
