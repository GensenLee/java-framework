package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：创建直播分类
 */
@Data
public class PolyvAppCreateCategoryParameter extends AppSignBean {

    /**
     * 分类名称
     */
    private String categoryName;


    public static final class PolyvAppCreateCategoryParameterBuilder extends ParameterBuilder<PolyvAppCreateCategoryParameter> {
        private PolyvAppCreateCategoryParameter polyvAppCreateCategoryParameter;

        private PolyvAppCreateCategoryParameterBuilder() {
            polyvAppCreateCategoryParameter = new PolyvAppCreateCategoryParameter();
        }

        public static PolyvAppCreateCategoryParameterBuilder aPolyvAppCreateCategoryParameter() {
            return new PolyvAppCreateCategoryParameterBuilder();
        }

        public PolyvAppCreateCategoryParameterBuilder withCategoryName(String categoryName) {
            polyvAppCreateCategoryParameter.setCategoryName(categoryName);
            return this;
        }

        @Override
        public PolyvAppCreateCategoryParameter build() {
            return polyvAppCreateCategoryParameter;
        }
    }
}
