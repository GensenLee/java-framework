package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * 查询频道重制课件设置
 */
@Data
public class PolyvChannelSearchPptRecordSettingParameter extends ChannelSignBean {


    public static final class PolyvChannelSearchPptRecordSettingParameterBuilder extends ParameterBuilder<PolyvChannelSearchPptRecordSettingParameter> {
        private PolyvChannelSearchPptRecordSettingParameter polyvChannelSearchPptRecordSettingParameter;

        private PolyvChannelSearchPptRecordSettingParameterBuilder() {
            polyvChannelSearchPptRecordSettingParameter = new PolyvChannelSearchPptRecordSettingParameter();
        }

        public static PolyvChannelSearchPptRecordSettingParameterBuilder aPolyvChannelSearchPptRecordSettingParameter() {
            return new PolyvChannelSearchPptRecordSettingParameterBuilder();
        }


        @Override
        public PolyvChannelSearchPptRecordSettingParameter build() {
            return polyvChannelSearchPptRecordSettingParameter;
        }
    }
}
