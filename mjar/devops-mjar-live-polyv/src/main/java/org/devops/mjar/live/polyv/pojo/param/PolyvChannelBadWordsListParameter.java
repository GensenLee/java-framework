package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：查询频道严禁词/禁言ip
 */
@Data
public class PolyvChannelBadWordsListParameter extends ChannelSignBean {

    /**
     * 查询类型，不传默认为badword：严禁词
     * ip：禁言ip
     * badword：严禁词
     */
    @VerifyField(ignore = true)
    private String type;

    public enum Type {
        IP("ip","查询禁言IP"),
        BADWORD("badword", "查询禁言用户id");

        Type(String value, String name) {
            this.value = value;
            this.name = name;
        }

        private String value;
        private String name;
    }


    public static final class PolyvChannelBadWordsListParameterBuilder extends ParameterBuilder<PolyvChannelBadWordsListParameter> {
        private PolyvChannelBadWordsListParameter polyvChannelBadWordsListParameter;

        private PolyvChannelBadWordsListParameterBuilder() {
            polyvChannelBadWordsListParameter = new PolyvChannelBadWordsListParameter();
        }

        public static PolyvChannelBadWordsListParameterBuilder aPolyvChannelBadWordsListParameter() {
            return new PolyvChannelBadWordsListParameterBuilder();
        }

        public PolyvChannelBadWordsListParameterBuilder withType(Type type) {
            polyvChannelBadWordsListParameter.setType(type.value);
            return this;
        }

        @Override
        public PolyvChannelBadWordsListParameter build() {
            return polyvChannelBadWordsListParameter;
        }
    }
}
