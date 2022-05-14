package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fangzy
 */
@Data
@ApiModel(description = "直播开始时间信息")
public class PolyvCountDown extends BaseBean {
    @ApiModelProperty("预约观看开关：Y开启、N关闭")
    private String bookingEnabled;
    @ApiModelProperty("倒计时开关：Y开启、N关闭")
    private String countEnabled;
    @ApiModelProperty("直播开始时间，格式：yyyy-MM-dd HH:mm:ss")
    private String startTime;
}
