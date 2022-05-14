package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/3/5 11:05
 * @description：打赏道具
 */
@ApiModel("打赏道具")
@Data
public class PolyvDonateGoods extends BaseBean {

    @ApiModelProperty("道具名称")
    private String goodName;

    @ApiModelProperty("道具图片")
    private String goodImg;

    @ApiModelProperty("道具价格")
    private Float goodPrice;

    @ApiModelProperty("道具的开关，取值Y/N")
    private String goodEnabled;
}
