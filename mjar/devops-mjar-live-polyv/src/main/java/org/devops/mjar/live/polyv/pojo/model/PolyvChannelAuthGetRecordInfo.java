package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author bigboss
 * @date 2021/7/21 17:00
 * @description：查询频道登记观看记录
 */
@Data
public class PolyvChannelAuthGetRecordInfo extends BaseBean {

    @ApiModelProperty(value = "登记时间，13位毫秒级时间戳")
    private Long createdTime;

    @ApiModelProperty(value = "登记时间，13位毫秒级时间戳")
    private List<String> params;
}
