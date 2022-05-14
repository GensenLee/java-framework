package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/21 17:51
 * @description：url地址
 */
@Data
public class PolyvAppUrlParameter extends AppSignBean {

    /**
     * url地址
     */
    private String url;


    public static final class PolyvAppUrlParameterBuilder extends ParameterBuilder<PolyvAppUrlParameter> {
        private PolyvAppUrlParameter polyvAppUrlParameter;

        private PolyvAppUrlParameterBuilder() {
            polyvAppUrlParameter = new PolyvAppUrlParameter();
        }

        public static PolyvAppUrlParameterBuilder aPolyvAppUrlParameter() {
            return new PolyvAppUrlParameterBuilder();
        }

        public PolyvAppUrlParameterBuilder withUrl(String url) {
            polyvAppUrlParameter.setUrl(url);
            return this;
        }

        @Override
        public PolyvAppUrlParameter build() {
            return polyvAppUrlParameter;
        }
    }
}
