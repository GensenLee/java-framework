package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;


/**
 * @author bigboss
 * @date 2021/07/23 11:55
 * @description:修改暖场视频
 */
@Data
public class PolyvChannelUpdateWarmUpVideoParameter extends ChannelSignBean {


    /**
     * 暖场视频地址，移动端不支持FLV视频文件，建议使用MP4视频文件
     */
    private String warmUpFlv;


    public static final class PolyvChannelUpdateWarmVideoParameterBuilder extends ParameterBuilder<PolyvChannelUpdateWarmUpVideoParameter> {
        private PolyvChannelUpdateWarmUpVideoParameter polyvChannelUpdateWarmUpVideoParameter;

        private PolyvChannelUpdateWarmVideoParameterBuilder() {
            polyvChannelUpdateWarmUpVideoParameter = new PolyvChannelUpdateWarmUpVideoParameter();
        }

        public static PolyvChannelUpdateWarmVideoParameterBuilder aPolyvChannelUpdateWarmVideoParameter() {
            return new PolyvChannelUpdateWarmVideoParameterBuilder();
        }

        public PolyvChannelUpdateWarmVideoParameterBuilder withWarmUpFlv(String warmUpFlv) {
            polyvChannelUpdateWarmUpVideoParameter.setWarmUpFlv(warmUpFlv);
            return this;
        }

        @Override
        public PolyvChannelUpdateWarmUpVideoParameter build() {
            return polyvChannelUpdateWarmUpVideoParameter;
        }
    }
}
