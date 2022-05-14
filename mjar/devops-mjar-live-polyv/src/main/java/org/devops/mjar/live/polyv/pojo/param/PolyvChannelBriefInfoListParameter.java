package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.WatchStatus;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GENSEN
 * @date 2020/10/29 14:10
 * @description：频道简略信息列表请求
 */
@NoArgsConstructor
@Data
public class PolyvChannelBriefInfoListParameter extends AppSignBean {

    /**
     * 页数，默认为1
     */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 每页显示的数据条数，默认每页显示20条数据
     */
    @VerifyField(ignore = true)
    private Integer pageSize;

    /**
     * 所属分类id
     */
    @VerifyField(ignore = true)
    private String categoryId;

    /**
     * 观看页状态筛选，live-直播中，playback-回放中，end-已结束，waiting-未开始
     */
    @VerifyField(ignore = true)
    private String watchStatus;

    /**
     * 频道名称，模糊查询
     */
    @VerifyField(ignore = true)
    private String keyword;


    public static final class PolyvChannelBriefInfoListParameterBuilder extends ParameterBuilder<PolyvChannelBriefInfoListParameter> {
        private PolyvChannelBriefInfoListParameter polyvChannelBriefInfoListParameter;

        private PolyvChannelBriefInfoListParameterBuilder() {
            polyvChannelBriefInfoListParameter = new PolyvChannelBriefInfoListParameter();
        }

        public static PolyvChannelBriefInfoListParameterBuilder aPolyvChannelBriefInfoListParameter() {
            return new PolyvChannelBriefInfoListParameterBuilder();
        }

        public PolyvChannelBriefInfoListParameterBuilder withPage(int page) {
            polyvChannelBriefInfoListParameter.setPage(page);
            return this;
        }

        public PolyvChannelBriefInfoListParameterBuilder withPageSize(int pageSize) {
            polyvChannelBriefInfoListParameter.setPageSize(pageSize);
            return this;
        }

        public PolyvChannelBriefInfoListParameterBuilder withCategoryId(String categoryId) {
            polyvChannelBriefInfoListParameter.setCategoryId(categoryId);
            return this;
        }

        public PolyvChannelBriefInfoListParameterBuilder withWatchStatus(WatchStatus watchStatus) {
            polyvChannelBriefInfoListParameter.setWatchStatus(watchStatus.getValue());
            return this;
        }

        public PolyvChannelBriefInfoListParameterBuilder withKeyword(String keyword) {
            polyvChannelBriefInfoListParameter.setKeyword(keyword);
            return this;
        }

        @Override
        public PolyvChannelBriefInfoListParameter build() {
            return polyvChannelBriefInfoListParameter;
        }
    }
}
