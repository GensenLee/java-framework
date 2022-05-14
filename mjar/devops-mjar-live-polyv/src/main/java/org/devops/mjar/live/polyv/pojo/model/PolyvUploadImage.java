package org.devops.mjar.live.polyv.pojo.model;


import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("上传封面")
public class PolyvUploadImage extends BaseBean {

    @ApiModelProperty("上传成功后的图片地址")
    private String activeimage;
    @ApiModelProperty("上传成功时间")
    private String modifiedtime;
}
