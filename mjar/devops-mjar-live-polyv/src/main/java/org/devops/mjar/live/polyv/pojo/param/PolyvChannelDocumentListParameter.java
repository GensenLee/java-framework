package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：查询频道已上传文档列表
 */
@Data
public class PolyvChannelDocumentListParameter extends ChannelSignBean {

    /**
     * 文档状态，默认不传查询所有
     */
    @VerifyField(ignore = true)
    private String status;

    /**
     * 第几页，默认不传显示第一页
     */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 每页显示几条数据，默认不传显示10条
     */
    @VerifyField(ignore = true)
    private Integer limit;

    /**
     * 是否展示文档原文件地址，默认不传不展示, Y是, N否
     */
    @VerifyField(ignore = true)
    private String isShowUrl;


    public static final class PolyvChannelDocumentListParameterBuilder extends ParameterBuilder<PolyvChannelDocumentListParameter> {
        private PolyvChannelDocumentListParameter polyvChannelDocumentListParameter;

        private PolyvChannelDocumentListParameterBuilder() {
            polyvChannelDocumentListParameter = new PolyvChannelDocumentListParameter();
        }

        public static PolyvChannelDocumentListParameterBuilder aPolyvChannelDocumentListParameter() {
            return new PolyvChannelDocumentListParameterBuilder();
        }

        public PolyvChannelDocumentListParameterBuilder withStatus(String status) {
            polyvChannelDocumentListParameter.setStatus(status);
            return this;
        }

        public PolyvChannelDocumentListParameterBuilder withPage(Integer page) {
            polyvChannelDocumentListParameter.setPage(page);
            return this;
        }

        public PolyvChannelDocumentListParameterBuilder withLimit(Integer limit) {
            polyvChannelDocumentListParameter.setLimit(limit);
            return this;
        }

        public PolyvChannelDocumentListParameterBuilder withIsShowUrl(String isShowUrl) {
            polyvChannelDocumentListParameter.setIsShowUrl(isShowUrl);
            return this;
        }

        @Override
        public PolyvChannelDocumentListParameter build() {
            return polyvChannelDocumentListParameter;
        }
    }
}
