package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gensen.Lee
 */
@Data
@ApiModel(description = "子频道")
public class PolyvChannelAccount extends BaseBean {
    @ApiModelProperty("子频道ID")
    private String account;
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("频道ID")
    private String channelId;
    @ApiModelProperty("子频道密码")
    private String passwd;
    @ApiModelProperty("子频道名称")
    private String nickname;
    @ApiModelProperty("子频道流名（单独使用无效）")
    private String stream;
    @ApiModelProperty("子频道状态")
    private String status;
    @ApiModelProperty("创建子频道时间")
    private Long createdTime;
    @ApiModelProperty("子频道最后修改时间")
    private Long lastModified;
    @ApiModelProperty("频道中所有子频道序号")
    private Integer sort;
    @ApiModelProperty("子频道头像")
    private String avatar;
    @ApiModelProperty("子频道翻页权限（只能一个子频道有）")
    private String pageTurnEnabled;
    @ApiModelProperty("发布公告权限")
    private String notifyEnabled;
    @ApiModelProperty("开启签到权限")
    private String checkinEnabled;
    @ApiModelProperty("发起投票")
    private String voteEnabled;
    private String lotteryEnabled;
    /**
     * 默认不传为助教
     * assistant：助教
     * guest：嘉宾（只支持三分屏场景的频道）
     */
    @ApiModelProperty("子频道角色")
    private String role;
    @ApiModelProperty("子频道推流地址（子频道推流请参考后台导播台使用")
    private String pushUrl;
    @ApiModelProperty("子账号头衔")
    private String actor;
    @ApiModelProperty("域名cname")
    private String cname;
    @ApiModelProperty( "助教页在线列表显示开关" )
    private String chatListEnabled;
    @ApiModelProperty( "助教聊天审核" )
    private String chatAuditEnabled;
    @ApiModelProperty( "监播开关" )
    private String monitorEnabled;
    @ApiModelProperty( "助教轮巡开关" )
    private String roundTourEnabled;
    @ApiModelProperty( "锁定直播间功能开关" )
    private String watchLockEnabled;
}
