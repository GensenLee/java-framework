package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.enums.LiveScene;
import org.devops.mjar.live.polyv.enums.WatchLayout;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/21 15:19
 * @description：创建频道
 */
@Data
public class PolyvChannelCreateParameter extends AppSignBean {
    /**
     * 频道名称
     */
    @VerifyField("频道名称")
    private String name;

    /**
     * 频道密码
     */
    @VerifyField("频道密码")
    private String channelPasswd;

    @VerifyField(value = "是否自动播放，0/1，默认1", ignore = true)
    private Integer autoPlay;

    /**
     *播放器控制栏颜色，默认：#666666
     */
    @VerifyField(value = "播放器控制栏颜色", ignore = true)
    private String playerColor;

    /**
     *  直播场景：\n" +
     *     "alone 直播助手\n" +
     *       "ppt 云课堂\n" +
     *       "topclass 大班课
     */
    @VerifyField(value = "直播场景", ignore = true)
    private String scene;

    /**
     *   新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到）
     */
    @VerifyField(value = "播放器控制栏颜色", ignore = true)
    private String categoryId;

    /**
     *   频道的最大在线人数观看限制的人数
     */
    @VerifyField(value = "频道的最大在线人数观看限制的人数", ignore = true)
    private Integer maxViewer;

    /**
     *   三分屏频道的观看布局，不设置会使用账号的通用设置，取值：ppt 文档为主，video 视频为主
     */
    @VerifyField(value = "三分屏频道的观看布局", ignore = true)
    private String watchLayout;

    /**
     *   连麦人数; -1：使用账号的连麦人数，范围>=-1，<=账号的连麦人数，最大16人
     */
    @VerifyField(value = "连麦人数", ignore = true)
    private Integer linkMicLimit;

    /**
     *  是否为无延时直播，Y 表示开启，默认为N
     */
    @VerifyField(value = "是否为无延时直播", ignore = true)
    private String pureRtcEnabled;


    /**
     *   是否为接收转播频道，Y 表示是，不填或者填其他值为发起转播频道(注：需要开启频道转播功能该参数才生效)
     */
    @VerifyField(value = "是否为接收转播频道", ignore = true)
    private String receive;

    /**
     *  接收转播频道号，多个频道号用半角逗号,隔开，如果receive参数值为Y时，此参数无效(注：需要开启频道转播功能该参数才生效)
     */
    @VerifyField(value = "接收转播频道号", ignore = true)
    private String receiveChannelIds;

    public static final class PolyvChannelCreateParameterBuilder extends ParameterBuilder<PolyvChannelCreateParameter> {
        private PolyvChannelCreateParameter polyvChannelCreateParameter;

        private PolyvChannelCreateParameterBuilder() {
            polyvChannelCreateParameter = new PolyvChannelCreateParameter();
        }

        public static PolyvChannelCreateParameterBuilder aPolyvChannelCreateReqParameter() {
            return new PolyvChannelCreateParameterBuilder();
        }

        public PolyvChannelCreateParameterBuilder withName(String name) {
            polyvChannelCreateParameter.setName(name);
            return this;
        }

        public PolyvChannelCreateParameterBuilder withChannelPasswd(String channelPasswd) {
            polyvChannelCreateParameter.setChannelPasswd(channelPasswd);
            return this;
        }

        public PolyvChannelCreateParameterBuilder withAutoPlay(EnableSetting setting) {
            polyvChannelCreateParameter.setAutoPlay(setting.getCode());
            return this;
        }

        public PolyvChannelCreateParameterBuilder withPlayerColor(String playerColor) {
            polyvChannelCreateParameter.setPlayerColor(playerColor);
            return this;
        }

        public PolyvChannelCreateParameterBuilder withScene(LiveScene scene) {
            polyvChannelCreateParameter.setScene(scene.getValue());
            return this;
        }

        public PolyvChannelCreateParameterBuilder withCategoryId(String categoryId) {
            polyvChannelCreateParameter.setCategoryId(categoryId);
            return this;
        }

        public PolyvChannelCreateParameterBuilder withMaxViewer(Integer maxViewer) {
            polyvChannelCreateParameter.setMaxViewer(maxViewer);
            return this;
        }

        public PolyvChannelCreateParameterBuilder withWatchLayout(WatchLayout layout) {
            polyvChannelCreateParameter.setWatchLayout(layout.getValue());
            return this;
        }

        public PolyvChannelCreateParameterBuilder withLinkMicLimit(Integer linkMicLimit) {
            polyvChannelCreateParameter.setLinkMicLimit(linkMicLimit);
            return this;
        }

        public PolyvChannelCreateParameterBuilder withPureRtcEnabled(EnableSetting enableSetting) {
            polyvChannelCreateParameter.setPureRtcEnabled(enableSetting.getValue());
            return this;
        }

        public PolyvChannelCreateParameterBuilder withReceive(EnableSetting setting) {
            polyvChannelCreateParameter.setReceive(setting.getValue());
            return this;
        }

        public PolyvChannelCreateParameterBuilder withReceiveChannelIds(String receiveChannelIds) {
            polyvChannelCreateParameter.setReceiveChannelIds(receiveChannelIds);
            return this;
        }

        @Override
        public PolyvChannelCreateParameter build() {
            return polyvChannelCreateParameter;
        }
    }
}
