package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName
 * @Description 查询重制课件任务列表返回数据
 * @Author wsl
 * @Date 2021
 * @Version 1.0
 **/

@Data
@ApiModel(description = "回放列表--获取列表")
public class PolyvChannelPptRecordList extends BaseBean {
        @ApiModelProperty("任务id")
        private Integer taskId;
        @ApiModelProperty("频道id")
        private String channelId;
        @ApiModelProperty("对应回放的名称")
        private String title;
        @ApiModelProperty("重制mp4下载地址，有24小时的防盗链超时时间")
        private String url;
        @ApiModelProperty("重制课件模块中的场次id")
        private String sessionId;
        @ApiModelProperty("对应回放的直播开始时间，格式为yyyyMMddhhmmss")
        private String startTime;
        @ApiModelProperty("状态值waiting：等待处理process：处理中success：重制成功fail：重制失败uploaded：上传点播成功uploadFailed：上传点播失败")
        private String status;
        @ApiModelProperty("重制剩余的过期时间，过期后将无法访问和下载")
        private Integer remainDay;
        @ApiModelProperty("重制的视频时长，单位秒")
        private Integer duration;
}
