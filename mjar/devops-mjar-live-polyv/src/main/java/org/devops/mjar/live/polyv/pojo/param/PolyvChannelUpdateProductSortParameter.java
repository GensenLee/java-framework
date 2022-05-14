package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/26 18:06
 * @description：修改频道商品库列表顺序
 */
@Data
public class PolyvChannelUpdateProductSortParameter extends ChannelSignBean {

    private Integer productId;

    /**
     *商品上下移动操作
     * 10：上移
     * 20：下移
     */
    private Integer type;

    public static final class PolyvChannelUpdateProductSortParameterBuilder extends ParameterBuilder<PolyvChannelUpdateProductSortParameter> {
        private PolyvChannelUpdateProductSortParameter polyvChannelUpdateProductSortParameter;

        private PolyvChannelUpdateProductSortParameterBuilder() {
            polyvChannelUpdateProductSortParameter = new PolyvChannelUpdateProductSortParameter();
        }

        public static PolyvChannelUpdateProductSortParameterBuilder aPolyvChannelUpdateProductSortParameter() {
            return new PolyvChannelUpdateProductSortParameterBuilder();
        }

        public PolyvChannelUpdateProductSortParameterBuilder withProductId(Integer productId) {
            polyvChannelUpdateProductSortParameter.setProductId(productId);
            return this;
        }

        public PolyvChannelUpdateProductSortParameterBuilder withType(Integer type) {
            polyvChannelUpdateProductSortParameter.setType(type);
            return this;
        }
        @Override
        public PolyvChannelUpdateProductSortParameter build() {
            return polyvChannelUpdateProductSortParameter;
        }
    }
}
