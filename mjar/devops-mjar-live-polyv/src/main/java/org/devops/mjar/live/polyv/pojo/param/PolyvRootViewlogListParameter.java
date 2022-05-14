package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/3/1 14:22
 * @description：应用日志查询
 */
@Data
public class PolyvRootViewlogListParameter extends AppSignBean {

    /**
     * 查询日期
     */
    private String date;


    public static final class PolyvRootViewlogParameterBuilder extends ParameterBuilder<PolyvRootViewlogListParameter> {
        private PolyvRootViewlogListParameter polyvRootViewlogListParameter;

        private PolyvRootViewlogParameterBuilder() {
            polyvRootViewlogListParameter = new PolyvRootViewlogListParameter();
        }

        public static PolyvRootViewlogParameterBuilder aPolyvRootViewlogListParameter() {
            return new PolyvRootViewlogParameterBuilder();
        }

        public PolyvRootViewlogParameterBuilder withDate(String date) {
            polyvRootViewlogListParameter.setDate(date);
            return this;
        }


        @Override
        public PolyvRootViewlogListParameter build() {
            return polyvRootViewlogListParameter;
        }
    }
}
