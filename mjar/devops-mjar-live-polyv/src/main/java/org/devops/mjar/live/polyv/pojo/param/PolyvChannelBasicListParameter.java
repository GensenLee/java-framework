package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：查询所有频道的基础信息
 */
@Data
public class PolyvChannelBasicListParameter extends AppSignBean {

    /**
     * 分类ID，多个id用英文逗号分隔
     */
    @VerifyField(ignore = true)
    private String categoryIds;

    /**
     *   页数，默认为1
     */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 每页显示的数据条数，默认为20，最大20
     */
    @VerifyField(ignore = true)
    private Integer pageSize;


    public static final class PolyvChannelBasicListParameterBuilder extends ParameterBuilder<PolyvChannelBasicListParameter> {
        private PolyvChannelBasicListParameter polyvChannelBasicListParameter;

        private PolyvChannelBasicListParameterBuilder() {
            polyvChannelBasicListParameter = new PolyvChannelBasicListParameter();
        }

        public static PolyvChannelBasicListParameterBuilder aPolyvChannelBasicListParameter() {
            return new PolyvChannelBasicListParameterBuilder();
        }

        public PolyvChannelBasicListParameterBuilder withCategoryIds(String categoryIds) {
            polyvChannelBasicListParameter.setCategoryIds(categoryIds);
            return this;
        }

        public PolyvChannelBasicListParameterBuilder withPage(Integer page) {
            polyvChannelBasicListParameter.setPage(page);
            return this;
        }

        public PolyvChannelBasicListParameterBuilder withPageSize(Integer pageSize) {
            polyvChannelBasicListParameter.setPageSize(pageSize);
            return this;
        }

        @Override
        public PolyvChannelBasicListParameter build() {
            return polyvChannelBasicListParameter;
        }
    }
}
