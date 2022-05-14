package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

@Data
public class PolyvPptRecordSetting extends BaseBean {

    private Integer channelId;

    private String userId;

    /**
     * 视频布局方式，0.三分屏、1.纯文档
     */
    private Integer type;

    /**
     * 应用全局设置开关 Y开启、N关闭
     */
    private String globalSettingEnabled;

    /**
     * 摄像头画面比例 0为16:9、1为4:3
     */
    private Integer videoRatio;

    /**
     * 背景图片，旧版重制1.0使用
     */
    private String backgroundImg;

    /**
     * 展示图片，新版重制2.0使用
     */
    private String brandImg;

}
