package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/12/9 10:53
 * @description：频道问卷列表
 */
@Data
public class PolyvChannelQuestionnaireListParameter extends ChannelSignBean {

    /**
     * 页数，默认为1
     */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 每页显示的数据条数，默认20
     */
    @VerifyField(ignore = true)
    private Integer pageSize;

    /**
     * 查询的记录的开始时间，13位位毫秒级时间戳
     */
    @VerifyField(ignore = true)
    private Long startTime;

    /**
     * 查询的记录的结束时间，13位位毫秒级时间戳
     */
    @VerifyField(ignore = true)
    private Long endTime;


    public static final class PolyvChannelQuestionnaireListParameterBuilder extends ParameterBuilder<PolyvChannelQuestionnaireListParameter> {
        private PolyvChannelQuestionnaireListParameter polyvChannelQuestionnaireListParameter;

        private PolyvChannelQuestionnaireListParameterBuilder() {
            polyvChannelQuestionnaireListParameter = new PolyvChannelQuestionnaireListParameter();
        }

        public static PolyvChannelQuestionnaireListParameterBuilder aPolyvChannelQuestionnaireListParameter() {
            return new PolyvChannelQuestionnaireListParameterBuilder();
        }

        public PolyvChannelQuestionnaireListParameterBuilder withPage(Integer page) {
            polyvChannelQuestionnaireListParameter.setPage(page);
            return this;
        }

        public PolyvChannelQuestionnaireListParameterBuilder withPageSize(Integer pageSize) {
            polyvChannelQuestionnaireListParameter.setPageSize(pageSize);
            return this;
        }

        public PolyvChannelQuestionnaireListParameterBuilder withStartTime(Long startTime) {
            polyvChannelQuestionnaireListParameter.setStartTime(startTime);
            return this;
        }

        public PolyvChannelQuestionnaireListParameterBuilder withEndTime(Long endTime) {
            polyvChannelQuestionnaireListParameter.setEndTime(endTime);
            return this;
        }

        @Override
        public PolyvChannelQuestionnaireListParameter build() {
            return polyvChannelQuestionnaireListParameter;
        }
    }
}
