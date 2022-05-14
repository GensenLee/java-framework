package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author fangzy
 */
@Data
@ApiModel(description = "引导图信息")
public class PolyvSplash extends BaseBean {
    @ApiModelProperty("引导图片url")
    private String splashImg;
    @ApiModelProperty("引导功能开关: Y：开启、N：关闭")
    private String splashEnabled;
}
