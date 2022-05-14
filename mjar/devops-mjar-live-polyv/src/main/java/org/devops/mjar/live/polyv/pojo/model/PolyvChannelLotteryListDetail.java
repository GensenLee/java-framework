package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/21 17:24
 * @description：查询频道中奖记录
 */
@Data
public class PolyvChannelLotteryListDetail extends BaseBean {

    @ApiModelProperty("抽奖场次ID")
    private String lotteryId;
    @ApiModelProperty("频道号")
    private String channelId;
    @ApiModelProperty("抽奖时的直播场次ID")
    private String sessionId;
    @ApiModelProperty("账号userId")
    private String userId;
    @ApiModelProperty("抽奖范围")
    private String lotteryRange;
    @ApiModelProperty("抽奖范围为按头衔抽奖时的头衔")
    private String actor;
    @ApiModelProperty("奖品名称")
    private String prize;
    @ApiModelProperty("预设中奖人数")
    private Integer amount;
    @ApiModelProperty("预设中奖观众ID，多个ID 用英文逗号分开")
    private String preset;
    @ApiModelProperty("抽奖时间")
    private Long createdTime;
    @ApiModelProperty("最后修改时间")
    private String lastModified;
    @ApiModelProperty("实际中奖人数")
    private String winnerCount;
    @ApiModelProperty("json 格式的字符串,表示抽奖的额外拓展信息")
    private String ext;

}
