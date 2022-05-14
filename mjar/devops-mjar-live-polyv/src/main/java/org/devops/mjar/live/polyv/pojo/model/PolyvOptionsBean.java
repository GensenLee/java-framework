package org.devops.mjar.live.polyv.pojo.model;


import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("题目选项信息")
public class PolyvOptionsBean extends BaseBean {
    @ApiModelProperty("题目")
    private String title;
    @ApiModelProperty("数量")
    private Integer count;
    @ApiModelProperty("百分比")
    private String percent;
}