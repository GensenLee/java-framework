package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/23 16:13
 * @description：查询频道图文直播内容
 */

@Data
public class PolyvChannelSearchImageMenuListParameter extends ChannelSignBean {

    /**
     * 图文内容的序列号：为空表示获取第一页数据，且同时会返回置顶数据。非空表示获取 id 比该值小的记录（也就是更早发布的内容），此时不返回置顶列表。
     */
    @VerifyField(ignore = true)
    private Integer id;

    /**
     * 是否为图片模式，默认为N
     */
    @VerifyField(ignore = true)
    private String imageMode;

    public static final class PolyvChannelSearchImageMenuListParameterBuilder extends ParameterBuilder<PolyvChannelSearchImageMenuListParameter> {
        private PolyvChannelSearchImageMenuListParameter polyvChannelSearchImageMenuListParameter;

        private PolyvChannelSearchImageMenuListParameterBuilder() {
            polyvChannelSearchImageMenuListParameter = new PolyvChannelSearchImageMenuListParameter();
        }

        public static PolyvChannelSearchImageMenuListParameterBuilder aPolyvChannelSearchImageMenuListParameter() {
            return new PolyvChannelSearchImageMenuListParameterBuilder();
        }

        public PolyvChannelSearchImageMenuListParameterBuilder withId(Integer id) {
            polyvChannelSearchImageMenuListParameter.setId(id);
            return this;
        }

        public PolyvChannelSearchImageMenuListParameterBuilder withImageMode(String imageMode) {
            polyvChannelSearchImageMenuListParameter.setImageMode(imageMode);
            return this;
        }

        @Override
        public PolyvChannelSearchImageMenuListParameter build() {
            return polyvChannelSearchImageMenuListParameter;
        }
    }
}
