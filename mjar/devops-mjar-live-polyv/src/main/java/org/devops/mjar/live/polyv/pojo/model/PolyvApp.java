package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gensen.Lee
 */
@Data
@ApiModel("应用")
public class PolyvApp extends BaseBean {
    @ApiModelProperty("应用appId")
    private String appId;
    @ApiModelProperty("应用名称")
    private String name;
    @ApiModelProperty("对应于POLYV的用户ID")
    private String userId;
    @ApiModelProperty("应用所属的调用POLYV的appSecret")
    private String appSecret;
    @ApiModelProperty("应用英文名称")
    private String appEnglishName;
}
