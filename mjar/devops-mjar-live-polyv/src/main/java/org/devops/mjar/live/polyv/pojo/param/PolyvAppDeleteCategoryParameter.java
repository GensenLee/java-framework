package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/22 17:44
 * @description：图片上传请求
 */
@Data
public class PolyvAppDeleteCategoryParameter extends AppSignBean {


    /**
     * 频道分类id
     */
    @VerifyField(ignore = false )
    private String categoryId;


    public static final class PolyvAppDeleteCategoryParameterBuilder extends ParameterBuilder<PolyvAppDeleteCategoryParameter> {
        private PolyvAppDeleteCategoryParameter polyvAppDeleteCategoryParameter;

        private PolyvAppDeleteCategoryParameterBuilder() {
            polyvAppDeleteCategoryParameter = new PolyvAppDeleteCategoryParameter();
        }

        public static PolyvAppDeleteCategoryParameterBuilder aPolyvAppDeleteCategoryParameter() {
            return new PolyvAppDeleteCategoryParameterBuilder();
        }

        public PolyvAppDeleteCategoryParameterBuilder withCategoryId(String categoryId) {
            polyvAppDeleteCategoryParameter.setCategoryId(categoryId);
            return this;
        }


        @Override
        public PolyvAppDeleteCategoryParameter build() {
            return polyvAppDeleteCategoryParameter;
        }
    }
}
