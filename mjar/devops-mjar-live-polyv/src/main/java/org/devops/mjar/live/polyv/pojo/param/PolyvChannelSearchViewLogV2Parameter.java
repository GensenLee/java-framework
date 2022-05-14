package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;
import lombok.Getter;

/**
 * 分页查询频道直播观看详情数据
 */
@Data
public class PolyvChannelSearchViewLogV2Parameter extends ChannelSignBean {

    /**
     * 当前页码，默认为1
     */
    @VerifyField(ignore = true)
    private String page;

    /**
     * 每页显示的数据条数，默认每页显示1000条数据
     */
    @VerifyField(ignore = true)
    private String pageSize;

    /**
     * 查询开始时间，为13位毫秒级时间戳
     */
    @VerifyField(ignore = true)
    private Long startTime;

    /**
     * 查询日期，格式：yyyy-MM-dd
     */
    @VerifyField(ignore = true)
    private String currentDay;

    /**
     * 查询结束时间，13位毫秒级时间戳
     */
    @VerifyField(ignore = true)
    private Long endTime;

    /**
     * 观看用户ID，默认查询全部
     */
    @VerifyField(ignore = true)
    private String param1;
    /**
     * 观看用户昵称，默认为管理员
     */
    @VerifyField(ignore = true)
    private String param2;
    /**
     * 观看日志类型，默认为live
     * vod：观看回放
     * live：直播
     */
    @VerifyField(ignore = true)
    private String param3;

    @Getter
    static enum Param3Type{
        VOD("vod","观看回放"),
        LIVE("live","直播");

        private String code;
        private String value;

        Param3Type(String code,String value){
            this.code = code;
            this.value = value;
        }
    }

    public static final class PolyvChannelSearchViewLogV2ParameterBuilder extends ParameterBuilder<PolyvChannelSearchViewLogV2Parameter> {
        private PolyvChannelSearchViewLogV2Parameter polyvChannelSearchViewLogV2Parameter;

        private PolyvChannelSearchViewLogV2ParameterBuilder() {
            polyvChannelSearchViewLogV2Parameter = new PolyvChannelSearchViewLogV2Parameter();
        }

        public static PolyvChannelSearchViewLogV2ParameterBuilder aPolyvChannelSearchViewLogV2Parameter() {
            return new PolyvChannelSearchViewLogV2ParameterBuilder();
        }

        public PolyvChannelSearchViewLogV2ParameterBuilder withPage(String page) {
            polyvChannelSearchViewLogV2Parameter.setPage(page);
            return this;
        }

        public PolyvChannelSearchViewLogV2ParameterBuilder withPageSize(String pageSize) {
            polyvChannelSearchViewLogV2Parameter.setPageSize(pageSize);
            return this;
        }

        public PolyvChannelSearchViewLogV2ParameterBuilder withStartTime(Long startTime) {
            polyvChannelSearchViewLogV2Parameter.setStartTime(startTime);
            return this;
        }

        public PolyvChannelSearchViewLogV2ParameterBuilder withCurrentDay(String currentDay) {
            polyvChannelSearchViewLogV2Parameter.setCurrentDay(currentDay);
            return this;
        }

        public PolyvChannelSearchViewLogV2ParameterBuilder withEndTime(Long endTime) {
            polyvChannelSearchViewLogV2Parameter.setEndTime(endTime);
            return this;
        }

        public PolyvChannelSearchViewLogV2ParameterBuilder withParam1(String param1) {
            polyvChannelSearchViewLogV2Parameter.setParam1(param1);
            return this;
        }

        public PolyvChannelSearchViewLogV2ParameterBuilder withParam2(String param2) {
            polyvChannelSearchViewLogV2Parameter.setParam2(param2);
            return this;
        }

        public PolyvChannelSearchViewLogV2ParameterBuilder withParam3(Param3Type param3) {
            polyvChannelSearchViewLogV2Parameter.setParam3(param3.getCode());
            return this;
        }

        public PolyvChannelSearchViewLogV2Parameter build() {
            return polyvChannelSearchViewLogV2Parameter;
        }
    }
}
