package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：文档上传
 */
@Data
public class PolyvDocumentUpload extends BaseBean {

    /**
     * 转换类型，默认不传转普通，因为只有ppt，pptx可以转动画，其他类型文件会自动转成普通；文件转动画转失败会直接把类型转为普通
     * common：转普通图片
     * animate：转动画效果
     */
    private String type;

    /**
     * 文件ID，成功时返回
     */
    private String fileId;

    /**
     * 文档状态
     * normal：正常
     * waitUpload：等待上传
     * failUpload：上传失败
     * waitConvert：转换PPT中
     * failConvert：转换PPT失败
     */
    private String status;

    /**
     * 文件地址
     */
    private String fileUrl;

}
