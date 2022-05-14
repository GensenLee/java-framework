package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/26 15:04
 * @description：删除伪直播
 */
@Data
public class PolyvChannelDeleteDiskVideoParameter extends ChannelSignBean {

    /**
     * 要设置伪直播的单个点播视频ID
     */
    private String vids;

    public static final class PolyvChannelDeleteDiskVideoParameterBuilder extends ParameterBuilder<PolyvChannelDeleteDiskVideoParameter> {
        private PolyvChannelDeleteDiskVideoParameter polyvChannelDeleteDiskVideoParameter;

        private PolyvChannelDeleteDiskVideoParameterBuilder() {
            polyvChannelDeleteDiskVideoParameter = new PolyvChannelDeleteDiskVideoParameter();
        }

        public static PolyvChannelDeleteDiskVideoParameterBuilder aPolyvChannelDeleteDiskVideoParameter() {
            return new PolyvChannelDeleteDiskVideoParameterBuilder();
        }

        public PolyvChannelDeleteDiskVideoParameterBuilder withVids(String vids) {
            polyvChannelDeleteDiskVideoParameter.setVids(vids);
            return this;
        }

        @Override
        public PolyvChannelDeleteDiskVideoParameter build() {
            return polyvChannelDeleteDiskVideoParameter;
        }
    }
}
