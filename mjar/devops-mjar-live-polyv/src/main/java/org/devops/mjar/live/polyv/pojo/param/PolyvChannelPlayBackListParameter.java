package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/12/23 14:28
 * @description：回放列表
 */
@Data
public class PolyvChannelPlayBackListParameter extends ChannelSignBean {

    /**
     * 页数，默认为1
     */
    @VerifyField
    private Integer page;

    /**
     * 每页显示的数据条数，默认每页显示12条数据
     */
    @VerifyField(ignore = true)
    private Integer pageSize;

    /**
     * playback-回放列表，vod-点播列表; 默认普通直播场景为vod，三分屏为playback
     */
    @VerifyField(ignore = true)
    private String listType;


    public enum ListType{
        PLAYBACK("playback","回放列表"),
        VOD("vod","点播列表");
        private String code;
        private String name;

        ListType(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }


    public static final class PolyvChannelPlayBackListParameterBuilder extends ParameterBuilder<PolyvChannelPlayBackListParameter> {
        private PolyvChannelPlayBackListParameter polyvChannelPlayBackListParameter;

        private PolyvChannelPlayBackListParameterBuilder() {
            polyvChannelPlayBackListParameter = new PolyvChannelPlayBackListParameter();
        }

        public static PolyvChannelPlayBackListParameterBuilder aPolyvChannelPlayBackListParameter() {
            return new PolyvChannelPlayBackListParameterBuilder();
        }

        public PolyvChannelPlayBackListParameterBuilder withPage(Integer page) {
            polyvChannelPlayBackListParameter.setPage(page);
            return this;
        }

        public PolyvChannelPlayBackListParameterBuilder withPageSize(Integer pageSize) {
            polyvChannelPlayBackListParameter.setPageSize(pageSize);
            return this;
        }

        public PolyvChannelPlayBackListParameterBuilder withListType(ListType listType) {
            polyvChannelPlayBackListParameter.setListType(listType.code);
            return this;
        }

        @Override
        public PolyvChannelPlayBackListParameter build() {
            return polyvChannelPlayBackListParameter;
        }
    }
}
