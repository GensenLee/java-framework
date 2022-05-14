package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel( "获取频道API的访问令牌" )
@Data
public class PolyvChannelToken extends BaseBean {

    @ApiModelProperty("失效时间，13位毫秒级时间戳，在该时间之前的token有效，否则所调用接口将返回code=15, 代表无效token，需要重新获取")
    private String expireTime;

    @ApiModelProperty("频道接口访问令牌，为了避免权限过大而导致误操作，该token仅限访问该频道数据，否则所调用接口将返回无效token的错误")
    private String channelToken;
}
