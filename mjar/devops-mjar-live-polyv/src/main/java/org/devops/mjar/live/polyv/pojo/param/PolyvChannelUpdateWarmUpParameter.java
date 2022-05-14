package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/07/23 11:19
 * @description:修改暖场设置开关
 */
@Data
public class PolyvChannelUpdateWarmUpParameter extends ChannelSignBean {

    /**
     * 开关值
     */
    private String warmUpEnabled;


    public static final class PolyvChannelUpdateWarmUpParameterBuilder extends ParameterBuilder<PolyvChannelUpdateWarmUpParameter> {
        private PolyvChannelUpdateWarmUpParameter polyvChannelUpdateWarmUpParameter;

        private PolyvChannelUpdateWarmUpParameterBuilder() {
            polyvChannelUpdateWarmUpParameter = new PolyvChannelUpdateWarmUpParameter();
        }

        public static PolyvChannelUpdateWarmUpParameterBuilder aPolyvChannelUpdateWarmUpParameter() {
            return new PolyvChannelUpdateWarmUpParameterBuilder();
        }

        public PolyvChannelUpdateWarmUpParameterBuilder withWarmUpEnabled(String warmUpEnabled) {
            polyvChannelUpdateWarmUpParameter.setWarmUpEnabled(warmUpEnabled);
            return this;
        }

        public PolyvChannelUpdateWarmUpParameter build() {
            return polyvChannelUpdateWarmUpParameter;
        }
    }
}
