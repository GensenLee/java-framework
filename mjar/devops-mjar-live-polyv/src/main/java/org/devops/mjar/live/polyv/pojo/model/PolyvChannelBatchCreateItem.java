package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/3/3 13:54
 * @description：批量创建频道item
 */
@ApiModel("批量创建频道item")
@Data
public class PolyvChannelBatchCreateItem extends BaseBean {

    @ApiModelProperty(value = "频道名称",required = true)
    private String name;

    @ApiModelProperty(value = "频道密码",required = true)
    private String channelPasswd;

    @ApiModelProperty(value = "是否自动播放，0/1，默认1")
    @VerifyField(ignore = true)
    private Integer autoPlay;

    @ApiModelProperty(value = "播放器控制栏颜色，默认：#666666")
    @VerifyField(ignore = true)
    private String playerColor;

    @ApiModelProperty(value = "直播场景：\n" +
            "alone 直播助手\n" +
            "ppt 云课堂\n" +
            "topclass 大班课",required = true)
    @VerifyField( ignore = true)
    private String scene;

    @ApiModelProperty(value = "新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到）")
    @VerifyField(ignore = true)
    private String categoryId;

}
