package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/28 18:04
 * @description：图片上传请求
 */
@Data
public class PolyvAppImageUploadParameter extends AppSignBean {
    /**
     * 文件类型
     */
    private String type;
}
