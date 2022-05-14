package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gensen.Lee
 */
@Data
@ApiModel(description = "观看最高并发数获取-响应参数")
public class PolyvConcurrenceStatic extends BaseBean {
    @ApiModelProperty("日期，格式：yyyy-MM-dd")
    private String day;
    @ApiModelProperty("小时和分钟，格式：hh:mm")
    private String minute;
    @ApiModelProperty("并发数")
    private Integer viewers;

}
