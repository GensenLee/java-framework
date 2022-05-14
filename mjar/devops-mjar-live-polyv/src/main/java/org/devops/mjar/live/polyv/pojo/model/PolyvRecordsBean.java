package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("答题的用户")
public class PolyvRecordsBean extends BaseBean {
    @ApiModelProperty("观众id")
    private String viewerId;
    @ApiModelProperty("观众昵称")
    private String nickname;
    @ApiModelProperty("答案")
    private String answer;
    @ApiModelProperty
    private Boolean correct;
}