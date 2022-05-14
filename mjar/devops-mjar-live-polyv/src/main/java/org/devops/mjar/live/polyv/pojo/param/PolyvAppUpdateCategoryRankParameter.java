package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/22 18:21
 * @description：修改直播分类顺序
 */
@Data
public class PolyvAppUpdateCategoryRankParameter extends AppSignBean {


    /**
     * 频道分类id
     */
    private String categoryId;

    /**
     * 将分类id位置移动到该id对应的分类之后
     */
    private String afterCategoryId;

    public static final class PolyvAppUpdateCategoryRankParameterBuilder extends ParameterBuilder<PolyvAppUpdateCategoryRankParameter> {
        private PolyvAppUpdateCategoryRankParameter polyvAppUpdateCategoryRankParameter;

        private PolyvAppUpdateCategoryRankParameterBuilder() {
            polyvAppUpdateCategoryRankParameter = new PolyvAppUpdateCategoryRankParameter();
        }

        public static PolyvAppUpdateCategoryRankParameterBuilder aPolyvAppUpdateCategoryRankParameter() {
            return new PolyvAppUpdateCategoryRankParameterBuilder();
        }

        public PolyvAppUpdateCategoryRankParameterBuilder withCategoryId(String categoryId) {
            polyvAppUpdateCategoryRankParameter.setCategoryId(categoryId);
            return this;
        }

        public PolyvAppUpdateCategoryRankParameterBuilder withAfterCategoryId(String afterCategoryId) {
            polyvAppUpdateCategoryRankParameter.setAfterCategoryId(afterCategoryId);
            return this;
        }
        @Override
        public PolyvAppUpdateCategoryRankParameter build() {
            return polyvAppUpdateCategoryRankParameter;
        }
    }
}
