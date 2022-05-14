package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：获取可添加到回放列表的视频列表
 */
@Data
public class PolyvChannelPlayBackVideoPoolsParameter extends ChannelSignBean {

    /**
     * 页数，默认为1
     */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 每页显示的数据条数，默认20
     */
    @VerifyField(ignore = true)
    private Integer limit;


    public static final class PolyvChannelPlayBackVideoPoolsParameterBuilder extends ParameterBuilder<PolyvChannelPlayBackVideoPoolsParameter> {
        private PolyvChannelPlayBackVideoPoolsParameter polyvChannelPlayBackVideoPoolsParameter;

        private PolyvChannelPlayBackVideoPoolsParameterBuilder() {
            polyvChannelPlayBackVideoPoolsParameter = new PolyvChannelPlayBackVideoPoolsParameter();
        }

        public static PolyvChannelPlayBackVideoPoolsParameterBuilder aPolyvChannelPlayBackVideoPoolsParameter() {
            return new PolyvChannelPlayBackVideoPoolsParameterBuilder();
        }

        public PolyvChannelPlayBackVideoPoolsParameterBuilder withPage(Integer page) {
            polyvChannelPlayBackVideoPoolsParameter.setPage(page);
            return this;
        }

        public PolyvChannelPlayBackVideoPoolsParameterBuilder withLimit(Integer limit) {
            polyvChannelPlayBackVideoPoolsParameter.setLimit(limit);
            return this;
        }

        @Override
        public PolyvChannelPlayBackVideoPoolsParameter build() {
            return polyvChannelPlayBackVideoPoolsParameter;
        }
    }
}
