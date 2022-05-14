package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("合并mp4")
public class PolyvMergeMp4 extends BaseBean {

    @ApiModelProperty("文件ID")
    private String fileId;

    @ApiModelProperty("合并中返回空字符串，已合并成功返回MP4文件地址")
    private String fileUrl;
}
