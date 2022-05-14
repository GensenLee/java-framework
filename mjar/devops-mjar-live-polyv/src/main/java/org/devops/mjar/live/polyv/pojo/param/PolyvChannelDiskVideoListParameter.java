package org.devops.mjar.live.polyv.pojo.param;

import lombok.Data;
import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;

/**
 * @author fangzy
 * @description：分页获取伪直播信息
 */
@Data
public class PolyvChannelDiskVideoListParameter extends ChannelSignBean {

    /**
     * 页数，默认为1
     */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 每页显示的数据条数，默认每页显示20条数据
     */
    @VerifyField(ignore = true)
    private Integer limit;


    public static final class PolyvChannelDiskVideoListParameterBuilder extends ParameterBuilder<PolyvChannelDiskVideoListParameter> {
        private PolyvChannelDiskVideoListParameter polyvChannelDiskVideoListParameter;

        private PolyvChannelDiskVideoListParameterBuilder() {
            polyvChannelDiskVideoListParameter = new PolyvChannelDiskVideoListParameter();
        }

        public static PolyvChannelDiskVideoListParameterBuilder aPolyvChannelDiskVideoListParameter() {
            return new PolyvChannelDiskVideoListParameterBuilder();
        }

        public PolyvChannelDiskVideoListParameterBuilder withPage(Integer page) {
            polyvChannelDiskVideoListParameter.setPage(page);
            return this;
        }

        public PolyvChannelDiskVideoListParameterBuilder withLimit(Integer limit) {
            polyvChannelDiskVideoListParameter.setLimit(limit);
            return this;
        }

        @Override
        public PolyvChannelDiskVideoListParameter build() {
            return polyvChannelDiskVideoListParameter;
        }
    }
}
