package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：查询账号禁言列表
 */
@Data
public class PolyvAppBannedListParameter extends AppSignBean {

    /**
     * 分页页码，默认1
     */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 分页大小，默认10，最大1000
     */
    @VerifyField(ignore = true)
    private Integer size;


    public static final class PolyvAppBannedListParameterBuilder extends ParameterBuilder<PolyvAppBannedListParameter> {
        private PolyvAppBannedListParameter polyvAppBannedListParameter;

        private PolyvAppBannedListParameterBuilder() {
            polyvAppBannedListParameter = new PolyvAppBannedListParameter();
        }

        public static PolyvAppBannedListParameterBuilder aPolyvAppBannedListParameter() {
            return new PolyvAppBannedListParameterBuilder();
        }

        public PolyvAppBannedListParameterBuilder withPage(Integer page) {
            polyvAppBannedListParameter.setPage(page);
            return this;
        }

        public PolyvAppBannedListParameterBuilder withSize(Integer size) {
            polyvAppBannedListParameter.setSize(size);
            return this;
        }

        @Override
        public PolyvAppBannedListParameter build() {
            return polyvAppBannedListParameter;
        }
    }
}
