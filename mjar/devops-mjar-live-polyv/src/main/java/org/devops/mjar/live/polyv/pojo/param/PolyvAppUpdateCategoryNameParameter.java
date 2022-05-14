package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/22 18:21
 * @description：修改直播分类名称
 */
@Data
public class PolyvAppUpdateCategoryNameParameter extends AppSignBean {

    /**
     * 频道分类id
     */
    private String categoryId;

    /**
     * 频道分类名称
     */
    private String categoryName;


    public static final class PolyvAppUpdateCategoryNameParameterBuilder extends ParameterBuilder<PolyvAppUpdateCategoryNameParameter> {
        private PolyvAppUpdateCategoryNameParameter polyvAppUpdateCategoryNameParameter;

        private PolyvAppUpdateCategoryNameParameterBuilder() {
            polyvAppUpdateCategoryNameParameter = new PolyvAppUpdateCategoryNameParameter();
        }

        public static PolyvAppUpdateCategoryNameParameterBuilder aPolyvAppUpdateCategoryNameParameter() {
            return new PolyvAppUpdateCategoryNameParameterBuilder();
        }

        public PolyvAppUpdateCategoryNameParameterBuilder withCategoryId(String categoryId) {
            polyvAppUpdateCategoryNameParameter.setCategoryId(categoryId);
            return this;
        }

        public PolyvAppUpdateCategoryNameParameterBuilder withCategoryName(String categoryName) {
            polyvAppUpdateCategoryNameParameter.setCategoryName(categoryName);
            return this;
        }

        @Override
        public PolyvAppUpdateCategoryNameParameter build() {
            return polyvAppUpdateCategoryNameParameter;
        }
    }
}
