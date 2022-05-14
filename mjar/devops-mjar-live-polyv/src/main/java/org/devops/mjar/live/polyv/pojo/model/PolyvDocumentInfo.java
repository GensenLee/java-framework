package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：文档信息
 */
@Data
public class PolyvDocumentInfo extends BaseBean {

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件url
     */
    private String fileUrl;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 上传文档的内容的总页数
     */
    private Integer totalPage;

    /**
     * 频道号
     */
    private String channelId;

    /**
     * 文档状态，不传默认查询所有
     * normal：正常
     * waitUpload：等待上传
     * failUpload：上传失败
     * waitConvert：转换PPT中
     * failConvert：转换PPT失败
     */
    private String status;

    /**
     * 创建时间，13位毫秒级时间戳
     */
    private Long createTime;

    /**
     * 转换类型，默认不传转普通，因为只有ppt，pptx可以转动画，其他类型文件会自动转成普通；文件转动画转失败会直接把类型转为普通
     * common：转普通图片
     * animate：转动画效果
     */
    private String convertType;

    /**
     * 类型，区分旧版PPT还是新版PPT
     * 新版值为：new
     * 旧版值为：old
     */
    private String Type;

    /**
     * ppt预览小图地址
     */
    private String previewImage;

    /**
     * ppt预览大图地址
     */
    private String previewBigImage;

    /**
     * 文档ID
     */
    private Integer autoId;
}
