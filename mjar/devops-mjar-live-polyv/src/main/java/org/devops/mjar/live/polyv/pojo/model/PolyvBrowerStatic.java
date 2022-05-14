package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gensen.Lee
 */
@Data
@ApiModel("终端观看数据")
public class PolyvBrowerStatic extends BaseBean {
    @ApiModelProperty("浏览器名称")
    private String browser;
    @ApiModelProperty("数量")
    private Integer count;
    @ApiModelProperty("观看的平台：pc mobile")
    private String platform;
}
