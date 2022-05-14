package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/26 14:51
 * @description：设置伪直播
 */

@Data
public class PolyvChannelCreateDiskVideoParameter extends ChannelSignBean {

    /**
     * 要设置伪直播的单个点播视频ID
     */
    private String vids;

    /**
     *伪直播开始时间，13位毫秒级时间戳
     */
    private String startTimes;

    public static final class PolyvChannelCreateDiskVideoParameterBuilder extends ParameterBuilder<PolyvChannelCreateDiskVideoParameter> {
        private PolyvChannelCreateDiskVideoParameter polyvChannelCreateDiskVideoParameter;

        private PolyvChannelCreateDiskVideoParameterBuilder() {
            polyvChannelCreateDiskVideoParameter = new PolyvChannelCreateDiskVideoParameter();
        }

        public static PolyvChannelCreateDiskVideoParameterBuilder aPolyvChannelCreateDiskVideoParameter() {
            return new PolyvChannelCreateDiskVideoParameterBuilder();
        }

        public PolyvChannelCreateDiskVideoParameterBuilder withVids(String vids) {
            polyvChannelCreateDiskVideoParameter.setVids(vids);
            return this;
        }

        public PolyvChannelCreateDiskVideoParameterBuilder withStartTimes(String startTimes) {
            polyvChannelCreateDiskVideoParameter.setStartTimes(startTimes);
            return this;
        }

        @Override
        public PolyvChannelCreateDiskVideoParameter build() {
            return polyvChannelCreateDiskVideoParameter;
        }
    }
}
