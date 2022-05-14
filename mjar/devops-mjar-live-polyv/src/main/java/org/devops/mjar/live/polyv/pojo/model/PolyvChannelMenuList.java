package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author bigboss
 * @date 2021/7/23 16:19
 * @description：查询频道图文直播内容
 */
@Data
public class PolyvChannelMenuList extends BaseBean {

    @ApiModelProperty("总的返回结果条数")
    private Integer total;
    @ApiModelProperty("返回的图文列表内容")
    private List<Contents> contents;
    @ApiModelProperty("置顶的图文信息 ")
    private List<Contents> topContents;
    @ApiModelProperty("图文直播相关设置信息 ")
    private Setting setting;

    @Data
    static class Setting {
        @ApiModelProperty("操作人的昵称")
        private String nickname;
        @ApiModelProperty("操作人的头衔")
        private String actor;
        @ApiModelProperty("操作人的头像")
        private String avatar;

    }

    @Data
    static class Contents {

        @ApiModelProperty("图文内容序列号，可用于查询条件参数id")
        private Integer id;
        @ApiModelProperty("频道号")
        private String channelId;
        @ApiModelProperty("文本内容")
        private String total;
        @ApiModelProperty("是否置顶")
        private Integer top;
        @ApiModelProperty("内容发送的时间，13位的时间戳")
        private Long createdTime;
        @ApiModelProperty("图片地址")
        private List<String> images;
    }

}
