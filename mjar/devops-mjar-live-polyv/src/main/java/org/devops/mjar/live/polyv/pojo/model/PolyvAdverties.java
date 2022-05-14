package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/28 10:20
 * @description：广告列表
 */

@Data
public class PolyvAdverties extends BaseBean {
    /**
     * 文字广告内容
     */
    private String text;
    /**
     * 图片广告图片地址
     */
    private String  img;
    /**
     * 广告跳转链接
     */
    private String href;
}
