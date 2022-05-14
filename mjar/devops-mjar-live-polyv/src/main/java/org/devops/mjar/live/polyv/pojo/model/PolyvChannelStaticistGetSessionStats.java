package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ApiModel(description = "查询频道多场次概览统计数据")
public class PolyvChannelStaticistGetSessionStats extends BaseBean {
    @ApiModelProperty(value = "直播观看数据信息")
    private List<ReturnData> list;

    @Data
    static class ReturnData extends BaseBean {
        @ApiModelProperty( value = "频道id")
        private String channelId;
        @ApiModelProperty( value = "场次id")
        private String sessionId;
        @ApiModelProperty( value = "场次名称")
        private String name;
        @ApiModelProperty("场次开始时间，13位毫秒级时间戳")
        private Long startTime;
        @ApiModelProperty("直播时长，单位秒")
        private Integer duration;
        @ApiModelProperty("直播观看人次")
        private Integer liveUV;
        @ApiModelProperty("直播观看人次")
        private Integer livePV;
        @ApiModelProperty("回看观看人次")
        private Integer playbackPV;
        @ApiModelProperty("回看观看人次")
        private Integer playbackUV;
    }

}
