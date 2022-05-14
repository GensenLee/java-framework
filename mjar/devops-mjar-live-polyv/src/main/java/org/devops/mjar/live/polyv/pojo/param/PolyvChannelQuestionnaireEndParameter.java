package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：手动结束问卷
 */
@Data
public class PolyvChannelQuestionnaireEndParameter extends AppSignBean {

    /**
     * 频道号， 多个频道用英文逗号分隔
     */
    private String channelIds;


    public static final class PolyvChannelQuestionnaireEndParameterBuilder extends ParameterBuilder<PolyvChannelQuestionnaireEndParameter> {
        private PolyvChannelQuestionnaireEndParameter polyvChannelQuestionnaireEndParameter;

        private PolyvChannelQuestionnaireEndParameterBuilder() {
            polyvChannelQuestionnaireEndParameter = new PolyvChannelQuestionnaireEndParameter();
        }

        public static PolyvChannelQuestionnaireEndParameterBuilder aPolyvChannelQuestionnaireEndParameter() {
            return new PolyvChannelQuestionnaireEndParameterBuilder();
        }

        public PolyvChannelQuestionnaireEndParameterBuilder withChannelIds(String channelIds) {
            polyvChannelQuestionnaireEndParameter.setChannelIds(channelIds);
            return this;
        }

        public PolyvChannelQuestionnaireEndParameter build() {
            return polyvChannelQuestionnaireEndParameter;
        }
    }
}
