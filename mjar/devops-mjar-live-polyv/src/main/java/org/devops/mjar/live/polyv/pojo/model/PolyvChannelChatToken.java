package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "获取频道授权和连麦token")
public class PolyvChannelChatToken  extends BaseBean {
    
    @ApiModelProperty(value = "连麦需要的key")
    private String mediaChannelKey;
    
    @ApiModelProperty(value = "请求接口需要的token值")
    private String token;
}
