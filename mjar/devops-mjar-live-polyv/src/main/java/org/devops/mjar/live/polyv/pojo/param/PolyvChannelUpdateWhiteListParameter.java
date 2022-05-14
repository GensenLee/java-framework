package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @description：修改频道单个白名单
 */
@Data
public class PolyvChannelUpdateWhiteListParameter extends ChannelSignBean {

    /**
     * 旧会员码
     */
    private String oldCode;
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

    public static final class PolyvChannelUpdateWhiteListParameterBuilder extends ParameterBuilder<PolyvChannelUpdateWhiteListParameter> {
        private PolyvChannelUpdateWhiteListParameter polyvChannelAddWhiteListParameter;

        private PolyvChannelUpdateWhiteListParameterBuilder() {
            polyvChannelAddWhiteListParameter = new PolyvChannelUpdateWhiteListParameter();
        }

        public static PolyvChannelUpdateWhiteListParameterBuilder aPolyvChannelAddWhiteListParameter() {
            return new PolyvChannelUpdateWhiteListParameterBuilder();
        }

        public PolyvChannelUpdateWhiteListParameterBuilder withRank(Integer rank) {
            polyvChannelAddWhiteListParameter.setRank(rank);
            return this;
        }

        public PolyvChannelUpdateWhiteListParameterBuilder withCode(String code) {
            polyvChannelAddWhiteListParameter.setCode(code);
            return this;
        }

        public PolyvChannelUpdateWhiteListParameterBuilder withName(String name) {
            polyvChannelAddWhiteListParameter.setName(name);
            return this;
        }

        public PolyvChannelUpdateWhiteListParameterBuilder withOldCode(String oldCode) {
            polyvChannelAddWhiteListParameter.setOldCode(oldCode);
            return this;
        }

        @Override
        public PolyvChannelUpdateWhiteListParameter build() {
            return polyvChannelAddWhiteListParameter;
        }
    }
}
