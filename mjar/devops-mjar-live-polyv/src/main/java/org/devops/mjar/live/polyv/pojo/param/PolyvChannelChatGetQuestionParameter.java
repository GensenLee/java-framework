package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：查询频道提问记录
 */
@Data
public class PolyvChannelChatGetQuestionParameter extends ChannelSignBean {
    /**
     * 起始下标，从0开始
     */
    @VerifyField(ignore = true)
    private Integer begin;
    /**
     *  结束下标，-1表示不分页
     */
    @VerifyField(ignore = true)
    private Integer end;

    public static final class PolyvChannelChatGetQuestionBuilder extends ParameterBuilder<PolyvChannelChatGetQuestionParameter> {
        private PolyvChannelChatGetQuestionParameter polyvChannelChatGetQuestion;

        private PolyvChannelChatGetQuestionBuilder() {
            polyvChannelChatGetQuestion = new PolyvChannelChatGetQuestionParameter();
        }

        public static PolyvChannelChatGetQuestionBuilder aPolyvChannelChatGetQuestion() {
            return new PolyvChannelChatGetQuestionBuilder();
        }

        public PolyvChannelChatGetQuestionBuilder withBegin(Integer begin) {
            polyvChannelChatGetQuestion.setBegin(begin);
            return this;
        }

        public PolyvChannelChatGetQuestionBuilder withEnd(Integer end) {
            polyvChannelChatGetQuestion.setEnd(end);
            return this;
        }

        @Override
        public PolyvChannelChatGetQuestionParameter build() {
            return polyvChannelChatGetQuestion;
        }
    }
}
