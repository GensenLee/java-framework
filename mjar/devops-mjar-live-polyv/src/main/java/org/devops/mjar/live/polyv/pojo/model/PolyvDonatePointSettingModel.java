package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author GENSEN
 * @date 2021/3/5 11:52
 * @description：打赏设置
 */
@ApiModel("打赏设置")
@Data
public class PolyvDonatePointSettingModel extends BaseBean {

    @ApiModelProperty("积分打赏设置开关，取值Y/N")
    private String donatePointEnabled;

    @ApiModelProperty("积分查询接口URL")
    private String queryPointUrl;

    @ApiModelProperty("积分更新接口URL")
    private String updatePointUrl;

    @ApiModelProperty("接口请求失败错误提示")
    private String requestFailTips;

    @ApiModelProperty("打赏积分不足提示")
    private String pointNotEnoughTips;

    @ApiModelProperty("积分的单位")
    private String pointUnit;

    @ApiModelProperty("最低打赏金额")
    private Double cashMin;

    @ApiModelProperty("打赏金额选项")
    private List<Double> cashes;

    @ApiModelProperty("积分打赏道具列表")
    private List<PolyvDonateGoods> goods;

}
