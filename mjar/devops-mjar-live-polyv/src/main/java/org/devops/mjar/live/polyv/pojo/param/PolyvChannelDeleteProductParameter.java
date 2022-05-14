package org.devops.mjar.live.polyv.pojo.param;


import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/7/26 18:20
 * @description：删除频道商品
 */
@Data
public class PolyvChannelDeleteProductParameter extends ChannelSignBean {

    private Integer productId;

    public static final class PolyvChannelDeleteProductParameterBuilder extends ParameterBuilder<PolyvChannelDeleteProductParameter> {
        private PolyvChannelDeleteProductParameter polyvChannelDeleteProductParameter;

        private PolyvChannelDeleteProductParameterBuilder() {
            polyvChannelDeleteProductParameter = new PolyvChannelDeleteProductParameter();
        }

        public static PolyvChannelDeleteProductParameterBuilder aPolyvChannelDeleteProductParameter() {
            return new PolyvChannelDeleteProductParameterBuilder();
        }

        public PolyvChannelDeleteProductParameterBuilder withProductId(Integer productId) {
            polyvChannelDeleteProductParameter.setProductId(productId);
            return this;
        }

        @Override
        public PolyvChannelDeleteProductParameter build() {
            return polyvChannelDeleteProductParameter;
        }
    }
}
