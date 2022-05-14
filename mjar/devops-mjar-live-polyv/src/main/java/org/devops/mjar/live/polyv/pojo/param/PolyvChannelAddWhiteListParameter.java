package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @description：增加频道单个白名单
 */
@Data
public class PolyvChannelAddWhiteListParameter extends ChannelSignBean {

    /**
     * 主要观看条件为1，次要观看条件为2
     */
    private Integer rank;
    /**
     * 会员码（最多为50个字符）
     */
    private String code;
    /**
     * 昵称（最多为50个字符）
     */
    private String name;

    public static final class PolyvChannelAddWhiteListParameterBuilder extends ParameterBuilder<PolyvChannelAddWhiteListParameter> {
        private PolyvChannelAddWhiteListParameter polyvChannelAddWhiteListParameter;

        private PolyvChannelAddWhiteListParameterBuilder() {
            polyvChannelAddWhiteListParameter = new PolyvChannelAddWhiteListParameter();
        }

        public static PolyvChannelAddWhiteListParameterBuilder aPolyvChannelAddWhiteListParameter() {
            return new PolyvChannelAddWhiteListParameterBuilder();
        }

        public PolyvChannelAddWhiteListParameterBuilder withRank(Integer rank) {
            polyvChannelAddWhiteListParameter.setRank(rank);
            return this;
        }

        public PolyvChannelAddWhiteListParameterBuilder withCode(String code) {
            polyvChannelAddWhiteListParameter.setCode(code);
            return this;
        }

        public PolyvChannelAddWhiteListParameterBuilder withName(String name) {
            polyvChannelAddWhiteListParameter.setName(name);
            return this;
        }

        @Override
        public PolyvChannelAddWhiteListParameter build() {
            return polyvChannelAddWhiteListParameter;
        }
    }
}
