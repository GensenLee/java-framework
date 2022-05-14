package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/3/3 13:54
 * @description：批量创建子频道
 */
@ApiModel("批量创建频道item")
@Data
public class PolyvChannelBatchCreateAccountItem extends BaseBean {

    @ApiModelProperty("子频道密码")
    private String passwd;

    @ApiModelProperty("默认不传为助教，传Guest为嘉宾（只支持三分屏场景的频道")
    @VerifyField(ignore = true)
    private String role;

    @ApiModelProperty("创建的助教或嘉宾昵称")
    @VerifyField(ignore = true)
    private String nickname;

    @ApiModelProperty("创建的助教或嘉宾头衔")
    @VerifyField(ignore = true)
    private String actor;

    @ApiModelProperty("创建的助教或嘉宾头像")
    @VerifyField(ignore = true)
    private String avatar;

}
