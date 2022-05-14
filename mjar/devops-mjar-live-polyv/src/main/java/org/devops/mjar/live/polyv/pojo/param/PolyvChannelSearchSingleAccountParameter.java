package org.devops.mjar.live.polyv.pojo.param;


import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/23 9:20
 * @description：查询单个子频道信息
 */
@Data
public class PolyvChannelSearchSingleAccountParameter extends ChannelSignBean {


    /**
     * 子频道号（不能以数字类型提交，否则可能去掉子频道号前的00)
     */
    private String account;


    public static final class PolyvChannelSearchSingleAccountParameterBuilder extends ParameterBuilder<PolyvChannelSearchSingleAccountParameter> {
        private PolyvChannelSearchSingleAccountParameter polyvAppSearchSingleAccountParameter;

        private PolyvChannelSearchSingleAccountParameterBuilder() {
            polyvAppSearchSingleAccountParameter = new PolyvChannelSearchSingleAccountParameter();
        }

        public static PolyvChannelSearchSingleAccountParameterBuilder aPolyvAppSearchSingleAccountParameter() {
            return new PolyvChannelSearchSingleAccountParameterBuilder();
        }

        public PolyvChannelSearchSingleAccountParameterBuilder withAccount(String account) {
            polyvAppSearchSingleAccountParameter.setAccount(account);
            return this;
        }

        @Override
        public PolyvChannelSearchSingleAccountParameter build() {
            return polyvAppSearchSingleAccountParameter;
        }
    }
}
