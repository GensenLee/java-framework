package org.devops.mjar.live.polyv.pojo.param;

import lombok.Data;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;

/**
 * @author fangzy
 * @description：删除账号禁言词
 */
@Data
public class PolyvAppBadWordDeleteParameter extends AppSignBean {

    /**
     * 要删除的严禁词，多个以英文逗号","分隔，例如："账号严禁词1,账号严禁词2"
     */
    private String words;


    public static final class PolyvAppBadWordDeleteParameterBuilder extends ParameterBuilder<PolyvAppBadWordDeleteParameter> {
        private PolyvAppBadWordDeleteParameter polyvAppBadWordDeleteParameter;

        private PolyvAppBadWordDeleteParameterBuilder() {
            polyvAppBadWordDeleteParameter = new PolyvAppBadWordDeleteParameter();
        }

        public static PolyvAppBadWordDeleteParameterBuilder aPolyvAppBadWordDeleteParameter() {
            return new PolyvAppBadWordDeleteParameterBuilder();
        }

        public PolyvAppBadWordDeleteParameterBuilder withWords(String words) {
            polyvAppBadWordDeleteParameter.setWords(words);
            return this;
        }

        @Override
        public PolyvAppBadWordDeleteParameter build() {
            return polyvAppBadWordDeleteParameter;
        }
    }
}
