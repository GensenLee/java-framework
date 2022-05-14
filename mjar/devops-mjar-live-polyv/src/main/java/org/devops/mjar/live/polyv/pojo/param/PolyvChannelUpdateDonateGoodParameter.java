package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.pojo.model.PolyvDonateGoods;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author fangzy
 * @description：设置道具打赏
 */
@Data
public class PolyvChannelUpdateDonateGoodParameter extends ChannelSignBean {

    @SignIgnore
    PolyvChannelUpdateDonateGoodParameterBody body = new PolyvChannelUpdateDonateGoodParameterBody();

    @Data
    public static class PolyvChannelUpdateDonateGoodParameterBody extends BaseBean {

        /**
         * 道具打赏对象数组，道具对象数量必须大于0小于10
         */
        private List<PolyvDonateGoods> goods;

        /**
         * 道具打赏开关，不传默认开启   Y：开启   N：关闭
         */
        @VerifyField(ignore = true)
        private String enabled;
    }


    public static final class PolyvChannelUpdateDonateGoodParameterBuilder extends ParameterBuilder<PolyvChannelUpdateDonateGoodParameter> {
        private PolyvChannelUpdateDonateGoodParameter polyvChannelUpdateDonateGoodParameter;

        private PolyvChannelUpdateDonateGoodParameterBuilder() {
            polyvChannelUpdateDonateGoodParameter = new PolyvChannelUpdateDonateGoodParameter();
        }

        public static PolyvChannelUpdateDonateGoodParameterBuilder aPolyvChannelUpdateDonateGoodParameter() {
            return new PolyvChannelUpdateDonateGoodParameterBuilder();
        }

        public PolyvChannelUpdateDonateGoodParameterBuilder withGoods(List<PolyvDonateGoods> goods) {
            polyvChannelUpdateDonateGoodParameter.getBody().setGoods(goods);
            return this;
        }

        public PolyvChannelUpdateDonateGoodParameterBuilder withEnabled(EnableSetting enabled) {
            polyvChannelUpdateDonateGoodParameter.getBody().setEnabled(enabled.getValue());
            return this;
        }

        @Override
        public PolyvChannelUpdateDonateGoodParameter build() {
            return polyvChannelUpdateDonateGoodParameter;
        }
    }
}
