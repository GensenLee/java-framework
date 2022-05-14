package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fangzy
 */
@Data
@ApiModel("直播分类信息")
public class PolyvCategory extends BaseBean {
    @ApiModelProperty("分类id")
    private String categoryId;
    @ApiModelProperty("频道分类名称")
    private String categoryName;
    @ApiModelProperty("POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）")
    private String userId;
    @ApiModelProperty("分类排序号，rank=0表示为默认排序")
    private Integer rank;

}
