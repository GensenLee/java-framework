package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/26 18:06
 * @description：修改频道商品库上下架状态
 */
@Data
public class PolyvChannelUpdateProductShelfParameter extends ChannelSignBean {

    private Integer productId;

    /**
     * 商品上下架状态
     * 1：上架
     * 2：下架
     */
    private Integer shelf;

    public static final class PolyvChannelUpdateProductShelfParameterBuilder extends ParameterBuilder<PolyvChannelUpdateProductShelfParameter> {
        private PolyvChannelUpdateProductShelfParameter polyvChannelUpdateProductShelfParameter;

        private PolyvChannelUpdateProductShelfParameterBuilder() {
            polyvChannelUpdateProductShelfParameter = new PolyvChannelUpdateProductShelfParameter();
        }

        public static PolyvChannelUpdateProductShelfParameterBuilder aPolyvChannelUpdateProductShelfParameter() {
            return new PolyvChannelUpdateProductShelfParameterBuilder();
        }

        public PolyvChannelUpdateProductShelfParameterBuilder withProductId(Integer productId) {
            polyvChannelUpdateProductShelfParameter.setProductId(productId);
            return this;
        }

        public PolyvChannelUpdateProductShelfParameterBuilder withShelf(Integer shelf) {
            polyvChannelUpdateProductShelfParameter.setShelf(shelf);
            return this;
        }
        @Override
        public PolyvChannelUpdateProductShelfParameter build() {
            return polyvChannelUpdateProductShelfParameter;
        }
    }
}
