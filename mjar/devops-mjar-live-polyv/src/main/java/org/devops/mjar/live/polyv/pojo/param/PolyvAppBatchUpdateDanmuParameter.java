package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：批量修改频道弹幕开关
 */
@Data
public class PolyvAppBatchUpdateDanmuParameter extends AppSignBean {

    /**
     * 需要修改弹幕开关的频道号，多个频道号用半角逗号 , 隔开
     */
    private String channelIds;

    /**
     * 是否关闭弹幕功能, Y关闭, N开启
     */
    private String closeDanmu;

    /**
     * 是否显示弹幕信息开关，, Y显示, N不显示
     */
    private String showDanmuInfoEnabled;


    public static final class PolyvAppBatchUpdateDanmuParameterBuilder extends ParameterBuilder<PolyvAppBatchUpdateDanmuParameter> {
        private PolyvAppBatchUpdateDanmuParameter polyvAppBatchUpdateDanmuParameter;

        private PolyvAppBatchUpdateDanmuParameterBuilder() {
            polyvAppBatchUpdateDanmuParameter = new PolyvAppBatchUpdateDanmuParameter();
        }

        public static PolyvAppBatchUpdateDanmuParameterBuilder aPolyvAppBatchUpdateDanmuParameter() {
            return new PolyvAppBatchUpdateDanmuParameterBuilder();
        }

        public PolyvAppBatchUpdateDanmuParameterBuilder withChannelIds(String channelIds) {
            polyvAppBatchUpdateDanmuParameter.setChannelIds(channelIds);
            return this;
        }

        public PolyvAppBatchUpdateDanmuParameterBuilder withCloseDanmu(EnableSetting closeDanmu) {
            polyvAppBatchUpdateDanmuParameter.setCloseDanmu(closeDanmu.getValue());
            return this;
        }

        public PolyvAppBatchUpdateDanmuParameterBuilder withShowDanmuInfoEnabled(EnableSetting showDanmuInfoEnabled) {
            polyvAppBatchUpdateDanmuParameter.setShowDanmuInfoEnabled(showDanmuInfoEnabled.getValue());
            return this;
        }

        @Override
        public PolyvAppBatchUpdateDanmuParameter build() {
            return polyvAppBatchUpdateDanmuParameter;
        }
    }
}
