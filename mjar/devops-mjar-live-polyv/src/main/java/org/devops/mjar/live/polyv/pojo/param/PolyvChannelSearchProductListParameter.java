package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;


/**
 * @author bigboss
 * @date 2021/7/26 17:30
 * @description：查询频道商品列表
 */
@Data
public class PolyvChannelSearchProductListParameter extends ChannelSignBean {

    /**
     * 当前页，默认1
     */
    @VerifyField(ignore = true)
    private Integer page;
    /**
     * 每一页数据大小，默认10
     */
    @VerifyField(ignore = true)
    private Integer size;


    public static final class PolyvChannelSearchProductListParameterBuilder extends ParameterBuilder<PolyvChannelSearchProductListParameter> {
        private PolyvChannelSearchProductListParameter polyvChannelSearchProductListParameter;

        private PolyvChannelSearchProductListParameterBuilder() {
            polyvChannelSearchProductListParameter = new PolyvChannelSearchProductListParameter();
        }

        public static PolyvChannelSearchProductListParameterBuilder aPolyvChannelSearchProductListParameter() {
            return new PolyvChannelSearchProductListParameterBuilder();
        }

        public PolyvChannelSearchProductListParameterBuilder withPage(Integer page) {
            polyvChannelSearchProductListParameter.setPage(page);
            return this;
        }

        public PolyvChannelSearchProductListParameterBuilder withSize(Integer size) {
            polyvChannelSearchProductListParameter.setSize(size);
            return this;
        }

        @Override
        public PolyvChannelSearchProductListParameter build() {
            return polyvChannelSearchProductListParameter;
        }
    }
}
