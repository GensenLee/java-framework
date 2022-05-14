package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import lombok.Data;
import org.devops.mjar.live.core.sign.RootSignBean;

/**
 * @author GENSEN
 * @date 2021/3/3 10:57
 * @description：直播监控请求
 */
@Data
public class PolyvRootMonitorListParameter extends RootSignBean {

    /**
     * 页码，默认为第一页
     */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 每页条数，默认为1条
     */
    @VerifyField(ignore = true)
    private Integer pageSize;

    /**
     * 直播状态 (live: 直播中,end: 暂无直播, waiting: 等待直播,  pull : 拉流直播中， disk ：硬盘直播中）
     */
    @VerifyField(ignore = true)
    private String status;

    /**
     * 直播场景 ( alone : 活动直播， ppt : 三分屏， topclass：大班课
     */
    @VerifyField(ignore = true)
    private String scene;

    /**
     *  频道ID
     */
    @VerifyField(ignore = true)
    private String channelIds;

    /**
     * 频道名称，支持模糊搜索
     */
    @VerifyField(ignore = true)
    private String channelName;


    public static final class PolyvRootMonitorListParameterBuilder extends ParameterBuilder<PolyvRootMonitorListParameter> {
        private PolyvRootMonitorListParameter polyvRootMonitorListParameter;

        private PolyvRootMonitorListParameterBuilder() {
            polyvRootMonitorListParameter = new PolyvRootMonitorListParameter();
        }

        public static PolyvRootMonitorListParameterBuilder aPolyvRootMonitorListParameter() {
            return new PolyvRootMonitorListParameterBuilder();
        }

        public PolyvRootMonitorListParameterBuilder withPage(Integer page) {
            polyvRootMonitorListParameter.setPage(page);
            return this;
        }

        public PolyvRootMonitorListParameterBuilder withPageSize(Integer pageSize) {
            polyvRootMonitorListParameter.setPageSize(pageSize);
            return this;
        }

        public PolyvRootMonitorListParameterBuilder withStatus(String status) {
            polyvRootMonitorListParameter.setStatus(status);
            return this;
        }

        public PolyvRootMonitorListParameterBuilder withScene(String scene) {
            polyvRootMonitorListParameter.setScene(scene);
            return this;
        }

        public PolyvRootMonitorListParameterBuilder withChannelIds(String channelIds) {
            polyvRootMonitorListParameter.setChannelIds(channelIds);
            return this;
        }

        public PolyvRootMonitorListParameterBuilder withChannelName(String channelName) {
            polyvRootMonitorListParameter.setChannelName(channelName);
            return this;
        }

        @Override
        public PolyvRootMonitorListParameter build() {
            return polyvRootMonitorListParameter;
        }
    }
}
