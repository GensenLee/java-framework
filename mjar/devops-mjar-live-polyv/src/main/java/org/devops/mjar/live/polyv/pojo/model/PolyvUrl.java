package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/21 17:51
 * @description：url地址
 */
@ApiModel("url地址")
@Data
public class PolyvUrl extends BaseBean {

    public PolyvUrl(String url) {
        this.url = url;
    }

    @ApiModelProperty("url地址")
    private String url;
}
