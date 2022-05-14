package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.ViewLogType;
import org.devops.mjar.live.core.exception.LiveApiRuntimeException;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/11/3 18:25
 * @description：频道观看日志分页查询
 */
@Data
public class PolyvChannelViewLogListParameter extends ChannelSignBean {
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
     * 1. 如果查询一段时间的记录，可以传：startTime、endTime （startTime和endTime 必须在同一个月），如果查询某天的记录，则传currentDay；
     * 2. startTime、endTime 和 currentDay不能都不传；
     * 3. currentDay与startTime、endTime 同时传将使用currentDay的值。*/

    /**
     *  查询日期，格式：yyyy-MM-dd
     */
    @VerifyField(ignore = true)
    private String currentDay;

    /**
     *  查询开始时间，为13位毫秒级时间戳
     */
    @VerifyField(ignore = true)
    private Long startTime;

    /**
     * 查询结束时间，为13位毫秒级时间戳
     */
    @VerifyField(ignore = true)
    private Long endTime;
    /**
     *  观看用户ID
     */
    @VerifyField(ignore = true)
    private String param1;

    /**
     *  观看用户昵称
     */
    @VerifyField(ignore = true)
    private String param2;

    /**
     * 观看日志类型，取值 vod 表示观看回放，取值live 表示直播
     */
    @VerifyField(ignore = true)
    private String param3;


    public static final class PolyvChannelViewLogListParameterBuilder extends ParameterBuilder<PolyvChannelViewLogListParameter> {
        private PolyvChannelViewLogListParameter polyvChannelViewLogListParameter;

        private PolyvChannelViewLogListParameterBuilder() {
            polyvChannelViewLogListParameter = new PolyvChannelViewLogListParameter();
        }

        public static PolyvChannelViewLogListParameterBuilder aPolyvChannelViewLogListParameter() {
            return new PolyvChannelViewLogListParameterBuilder();
        }

        public PolyvChannelViewLogListParameterBuilder withPage(int page) {
            polyvChannelViewLogListParameter.setPage(page);
            return this;
        }

        public PolyvChannelViewLogListParameterBuilder withPageSize(int pageSize) {
            polyvChannelViewLogListParameter.setPageSize(pageSize);
            return this;
        }

        public PolyvChannelViewLogListParameterBuilder withCurrentDay(String currentDay) {
            polyvChannelViewLogListParameter.setCurrentDay(currentDay);
            return this;
        }

        public PolyvChannelViewLogListParameterBuilder withStartTime(Long startTime) {
            polyvChannelViewLogListParameter.setStartTime(startTime);
            return this;
        }

        public PolyvChannelViewLogListParameterBuilder withEndTime(Long endTime) {
            polyvChannelViewLogListParameter.setEndTime(endTime);
            return this;
        }

        public PolyvChannelViewLogListParameterBuilder withViewerId(String viewerId) {
            polyvChannelViewLogListParameter.setParam1(viewerId);
            return this;
        }

        public PolyvChannelViewLogListParameterBuilder withUserName(String userName) {
            polyvChannelViewLogListParameter.setParam2(userName);
            return this;
        }

        public PolyvChannelViewLogListParameterBuilder withViewLogType(ViewLogType viewLogType) {
            polyvChannelViewLogListParameter.setParam3(viewLogType.getCode());
            return this;
        }

        @Override
        public PolyvChannelViewLogListParameter build(){
            if (StringUtil.isEmpty(polyvChannelViewLogListParameter.getCurrentDay()) &&
                    (LongUtil.isZero(polyvChannelViewLogListParameter.getStartTime()))
                    && LongUtil.isZero(polyvChannelViewLogListParameter.getEndTime())) {
                throw new LiveApiRuntimeException("startTime、endTime 和 currentDay不能都不传");
            }
            return polyvChannelViewLogListParameter;
        }
    }
}
