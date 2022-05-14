package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/27 14:31
 * @description：查询频道白名单
 */

@Data
public class PolyvChannelSearchWhiteListParameter extends ChannelSignBean {
    /**
     * 1为首要条件，2为次要条件
     */
    private Integer rank;
    /**
     * 要获取的页码，默认为1
     */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 每页数据量，默认为10
     */
    @VerifyField(ignore = true)
    private Integer pageSize;

    /**
     * 关键词，可根据会员码和名称查询
     */
    @VerifyField(ignore = true)
    private String keyword;

    public static final class PolyvChannelSearchWhiteLstParameterBuilder extends ParameterBuilder<PolyvChannelSearchWhiteListParameter> {
        private PolyvChannelSearchWhiteListParameter polyvChannelSearchWhiteListParameter;

        private PolyvChannelSearchWhiteLstParameterBuilder() {
            polyvChannelSearchWhiteListParameter = new PolyvChannelSearchWhiteListParameter();
        }

        public static PolyvChannelSearchWhiteLstParameterBuilder aPolyvChannelSearchWhiteLstParameter() {
            return new PolyvChannelSearchWhiteLstParameterBuilder();
        }

        public PolyvChannelSearchWhiteLstParameterBuilder withRank(Integer rank) {
            polyvChannelSearchWhiteListParameter.setRank(rank);
            return this;
        }

        public PolyvChannelSearchWhiteLstParameterBuilder withPage(Integer page) {
            polyvChannelSearchWhiteListParameter.setPage(page);
            return this;
        }

        public PolyvChannelSearchWhiteLstParameterBuilder withPageSize(Integer pageSize) {
            polyvChannelSearchWhiteListParameter.setPageSize(pageSize);
            return this;
        }

        public PolyvChannelSearchWhiteLstParameterBuilder withKeyword(String keyword) {
            polyvChannelSearchWhiteListParameter.setKeyword(keyword);
            return this;
        }

        @Override
        public PolyvChannelSearchWhiteListParameter build() {
            return polyvChannelSearchWhiteListParameter;
        }
    }
}
