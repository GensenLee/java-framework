package org.devops.mjar.live.polyv.pojo.param;

import lombok.Data;
import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;

/**
 * @author fangzy
 * @description：批量导入禁言词
 */
@Data
public class PolyvAppAddBadWordsParameter extends AppSignBean {

    /**
     * 严禁词，json的数组格式，例如["forbiddenWords1","forbiddenWords2"]
     */
    private String words;

    /**
     * 频道号，非必填，不填则设置为账号级的严禁词,对该账号的所有频道都生效
     */
    @VerifyField(ignore = true)
    private String channelId;


    public static final class PolyvAppAddBadWordsParameterBuilder extends ParameterBuilder<PolyvAppAddBadWordsParameter> {
        private PolyvAppAddBadWordsParameter polyvAppAddBadWordsParameter;

        private PolyvAppAddBadWordsParameterBuilder() {
            polyvAppAddBadWordsParameter = new PolyvAppAddBadWordsParameter();
        }

        public static PolyvAppAddBadWordsParameterBuilder aPolyvAppAddBadWordsParameter() {
            return new PolyvAppAddBadWordsParameterBuilder();
        }

        public PolyvAppAddBadWordsParameterBuilder withWords(String words) {
            polyvAppAddBadWordsParameter.setWords(words);
            return this;
        }

        public PolyvAppAddBadWordsParameterBuilder withChannelId(String channelId) {
            polyvAppAddBadWordsParameter.setChannelId(channelId);
            return this;
        }

        @Override
        public PolyvAppAddBadWordsParameter build() {
            return polyvAppAddBadWordsParameter;
        }
    }
}
