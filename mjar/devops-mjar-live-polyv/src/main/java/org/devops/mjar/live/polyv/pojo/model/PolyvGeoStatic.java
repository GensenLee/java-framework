package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gensen.Lee
 */
@Data
@ApiModel("地域分布对象")
public class PolyvGeoStatic extends BaseBean {
    @ApiModelProperty("省份")
    private String province;
    @ApiModelProperty("观看人数")
    private Integer viewer;
    @ApiModelProperty("pc端的观看时长")
    private Integer pcPlayDuration;
    @ApiModelProperty("pc端的播放流量")
    private Long pcFlowSize;
    @ApiModelProperty("PC端的播放")
    private Integer pcVideoView;
    @ApiModelProperty("PC端的唯一观看人数")
    private Integer pcUniqueViewer;
    @ApiModelProperty("移动端的播放时长")
    private Integer mobilePlayDuration;
    @ApiModelProperty("移动端的流量")
    private Integer mobileFlowSize;
    @ApiModelProperty("移动端的播放次数")
    private Integer mobileVideoView;
    @ApiModelProperty("移动端的唯一观看人数")
    private Integer mobileUniqueViewer;
}
