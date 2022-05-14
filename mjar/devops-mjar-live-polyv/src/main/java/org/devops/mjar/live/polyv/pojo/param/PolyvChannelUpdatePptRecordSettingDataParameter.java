package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * 更新频道重制课件设置
 */
@Data
public class PolyvChannelUpdatePptRecordSettingDataParameter extends ChannelSignBean {

    /**
     * 应用全局设置开关 Y开启、N关闭
     */
    @VerifyField(ignore = true)
    private String globalSettingEnabled;

    /**
     * 摄像头画面比例 0为16:9、1为4:3
     */
    @VerifyField(ignore = true)
    private Integer videoRatio;

    /**
     * 视频布局方式，0.三分屏、1.纯文档
     */
    @VerifyField(ignore = true)
    private Integer type;

}
