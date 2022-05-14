package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：删除频道严禁词/禁言ip
 */
@Data
public class PolyvChannelBadWordDeleteParameter extends ChannelSignBean {

    /**
     * 请求类型
     * ip：取消已禁言IP
     * badword：删除严禁词
     */
    private String type;

    /**
     * 要取消的ip或者严禁词，支持传入多个ip或者严禁词，通过英文逗号","分隔
     */
    private String content;

    public enum Type {
        IP("ip","取消已禁言IP"),
        BADWORD("badword", "删除严禁词");

        Type(String value, String name) {
            this.value = value;
            this.name = name;
        }

        private String value;
        private String name;
    }


    public static final class PolyvChannelBadWordDeleteParameterBuilder extends ParameterBuilder<PolyvChannelBadWordDeleteParameter> {
        private PolyvChannelBadWordDeleteParameter polyvChannelBadWordDeleteParameter;

        private PolyvChannelBadWordDeleteParameterBuilder() {
            polyvChannelBadWordDeleteParameter = new PolyvChannelBadWordDeleteParameter();
        }

        public static PolyvChannelBadWordDeleteParameterBuilder aPolyvChannelBadWordDeleteParameter() {
            return new PolyvChannelBadWordDeleteParameterBuilder();
        }

        public PolyvChannelBadWordDeleteParameterBuilder withType(Type type) {
            polyvChannelBadWordDeleteParameter.setType(type.value);
            return this;
        }

        public PolyvChannelBadWordDeleteParameterBuilder withContent(String content) {
            polyvChannelBadWordDeleteParameter.setContent(content);
            return this;
        }

        @Override
        public PolyvChannelBadWordDeleteParameter build() {
            return polyvChannelBadWordDeleteParameter;
        }
    }
}
