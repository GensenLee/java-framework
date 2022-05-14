package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：文档转码信息
 */
@Data
public class PolyvDocumentStatus extends BaseBean {

    /**
     * 大图图片数量，响应参数convertStatus=“normal” 时返回
     */
    private Integer imageCount;

    /**
     * 大图地址数组，响应参数convertStatus=“normal” 时返回，如：["imgurl1"，"imgurl2"]
     */
    private String[] images;

    /**
     * 小图地址数组，响应参数convertStatus=“normal” 时返回，如：["imgurl1"，"imgurl2"]
     */
    private String[] smallImages;

    /**
     * 总页数，响应参数convertStatus=“normal” 时返回
     */
    private Integer totalPage;

    /**
     * 动画PPT地址，响应参数convertStatus=“normal” 时返回
     */
    private String htmlUrl;

    /**
     * 文档状态，不传默认查询所有, normal：正常, waitUpload：等待上传, failUpload：上传失败, waitConvert：转换PPT中, failConvert：转换PPT失败
     */
    private String convertStatus;

    /**
     * 转换类型，默认不传转普通, common：转普通图片, animate：转动画效果
     */
    private String type;

    /**
     * 文件ID
     */
    private String fileId;
}
