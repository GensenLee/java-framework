package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/26 18:20
 * @description：删除频道白名单
 */
@Data
public class PolyvChannelDeleteWhiteListParameter extends ChannelSignBean {

    /**
     * 主要观看条件为1，次要观看条件为2
     */
    private Integer rank;

    /**
     * 会员码（最多为50个字符）
     */
    @VerifyField(ignore = true)
    private String code;

    /**
     * 是否一键清空白名单
     * Y：清空白名单
     * N：根据请求参数code清除白名单
     */
    private String isClear;


    public static final class PolyvChannelDeleteWhiteListParameterBuilder extends ParameterBuilder<PolyvChannelDeleteWhiteListParameter> {
        private PolyvChannelDeleteWhiteListParameter polyvChannelDeleteWhiteListParameter;

        private PolyvChannelDeleteWhiteListParameterBuilder() {
            polyvChannelDeleteWhiteListParameter = new PolyvChannelDeleteWhiteListParameter();
        }

        public static PolyvChannelDeleteWhiteListParameterBuilder aPolyvChannelDeleteWhiteListParameter() {
            return new PolyvChannelDeleteWhiteListParameterBuilder();
        }

        public PolyvChannelDeleteWhiteListParameterBuilder withRank(Integer rank) {
            polyvChannelDeleteWhiteListParameter.setRank(rank);
            return this;
        }

        public PolyvChannelDeleteWhiteListParameterBuilder withCode(String code) {
            polyvChannelDeleteWhiteListParameter.setCode(code);
            return this;
        }

        public PolyvChannelDeleteWhiteListParameterBuilder withIsClear(String isClear) {
            polyvChannelDeleteWhiteListParameter.setIsClear(isClear);
            return this;
        }

        @Override
        public PolyvChannelDeleteWhiteListParameter build() {
            return polyvChannelDeleteWhiteListParameter;
        }
    }
}
