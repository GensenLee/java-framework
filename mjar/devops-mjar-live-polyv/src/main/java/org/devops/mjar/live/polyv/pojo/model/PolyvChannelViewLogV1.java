package org.devops.mjar.live.polyv.pojo.model;

import io.swagger.annotations.ApiModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel( "查询频道直播观看详情数据" )
public class PolyvChannelViewLogV1 extends PolyvChannelViewLog {
    @ApiModelProperty("1：无延迟观看 0：普通观看")
    private Integer ptype;
}
