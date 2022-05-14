package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ChannelPlayBackVideoPoolRespVO
 * @Description 回放列表"添加" 获取列表 响应参数
 * @Author DD
 * @Date 2019/5/8 15:34
 * @Version 1.0
 **/
@Data
@ApiModel(description = "回放列表--获取列表")
public class PolyvChannelPlayBackVideoPoolItem extends BaseBean {
    @ApiModelProperty("例如 e6b23c6f516142d0a18a4a22631017c2")
    private String videoPoolId;
    @ApiModelProperty("视频标题")
    private String title;
    @ApiModelProperty("视频预览图")
    private String firstImage;
    @ApiModelProperty("视频时长")
    private String duration;
    @ApiModelProperty("是否加密")
    private Boolean encrypted;
}
