package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelQuestions;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author fangzy
 * @description：新增或修改频道问卷
 */
@Data
public class PolyvChannelQuestionnaireAddParameter extends ChannelSignBean {

    @SignIgnore
    PolyvChannelQuestionnaireAddParameterBody body = new PolyvChannelQuestionnaireAddParameterBody();

    @Data
    public static class PolyvChannelQuestionnaireAddParameterBody extends BaseBean {

        /**
         * 问卷标题
         */
        private String questionnaireTitle;

        /**
         * 题目数组
         */
        private List<PolyvChannelQuestions> questions;

        /**
         * 问卷id,修改问卷时需要
         */
        @VerifyField(ignore = true)
        private String questionnaireId;

        /**
         * 客户自定义问卷id
         */
        @VerifyField(ignore = true)
        private String customQuestionnaireId;
    }


    public static final class PolyvChannelQuestionnaireAddParameterBuilder extends ParameterBuilder<PolyvChannelQuestionnaireAddParameter> {
        private PolyvChannelQuestionnaireAddParameter polyvChannelQuestionnaireAddParameter;

        private PolyvChannelQuestionnaireAddParameterBuilder() {
            polyvChannelQuestionnaireAddParameter = new PolyvChannelQuestionnaireAddParameter();
        }

        public static PolyvChannelQuestionnaireAddParameterBuilder aPolyvChannelQuestionnaireAddParameter() {
            return new PolyvChannelQuestionnaireAddParameterBuilder();
        }

        public PolyvChannelQuestionnaireAddParameterBuilder withQuestionnaireTitle(String questionnaireTitle) {
            polyvChannelQuestionnaireAddParameter.getBody().setQuestionnaireTitle(questionnaireTitle);
            return this;
        }

        public PolyvChannelQuestionnaireAddParameterBuilder withQuestions(List<PolyvChannelQuestions> questions) {
            polyvChannelQuestionnaireAddParameter.getBody().setQuestions(questions);
            return this;
        }

        public PolyvChannelQuestionnaireAddParameterBuilder withQuestionnaireId(String questionnaireId) {
            polyvChannelQuestionnaireAddParameter.getBody().setQuestionnaireId(questionnaireId);
            return this;
        }

        public PolyvChannelQuestionnaireAddParameterBuilder withCustomQuestionnaireId(String customQuestionnaireId) {
            polyvChannelQuestionnaireAddParameter.getBody().setCustomQuestionnaireId(customQuestionnaireId);
            return this;
        }

        @Override
        public PolyvChannelQuestionnaireAddParameter build() {
            return polyvChannelQuestionnaireAddParameter;
        }
    }
}
