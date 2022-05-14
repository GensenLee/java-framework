package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/21 17:24
 * @description：菜单详情
 */
@Data
public class PolyvChannelMenuDetail extends BaseBean {

    @ApiModelProperty("菜单ID")
    private String menuId;
    @ApiModelProperty("菜单类型")
    private String menuType;
    @ApiModelProperty("菜单名称")
    private String name;
    @ApiModelProperty("菜单顺序，值越小，越靠前。新添加的菜单默认位于最后。")
    private String ordered;
    @ApiModelProperty("菜单内容")
    private String content;
    @ApiModelProperty("菜单语言类型")
    private String lang;

}
