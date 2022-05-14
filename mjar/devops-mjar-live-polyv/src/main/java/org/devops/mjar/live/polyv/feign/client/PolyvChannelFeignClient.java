package org.devops.mjar.live.polyv.feign.client;

import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.feign.annotation.PolyvFeignClient;
import org.devops.mjar.live.polyv.feign.annotation.PolyvGetMapping;
import org.devops.mjar.live.polyv.feign.annotation.PolyvPostMapping;
import org.devops.mjar.live.polyv.pojo.model.*;
import org.devops.mjar.live.polyv.pojo.param.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author GENSEN
 * @date 2020/10/21 14:54
 * @description：频道相关
 */
@PolyvFeignClient(name = "PolyvChannelFeignClient")
public interface PolyvChannelFeignClient {

    /**
     * 获取账号下回放视频
     * https://dev.polyv.net/2020/liveproduct/l-api/zhsz/user-playback-list/
     *
     * @param param
     * @return
     */
//    @PolyvGetMapping(value = "/live/v3/channel/playback/special-list")
    @PolyvGetMapping(value = "/live/v3/user/playback/list")
    PolyvApiResult<PolyvPaginator<PolyvChannelPlayBackItem>> getPlayBackSpecialList(@RequestParam PolyvChannelPlayBackListParameter param);

    /**
     * 获取直播暂存列表
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/record/list-simple")
    PolyvApiResult<PolyvPaginator<PolyvChannelRecordItem>> getRecordList(@RequestParam PolyvChannelRecordListParameter param);

    /**
     * 查询频道录制视频信息
     * https://help.polyv.net/index.html#/live/api/channel/playback/get_record_info
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/channels/{channelId}/recordFiles")
    PolyvApiResult<List<PolyvChannelRecordInfo>> getRecordFiles(@RequestParam PolyvChannelRecordFilesParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 根据fileId查询录制视频信息
     * https://help.polyv.net/index.html#/live/api/channel/playback/get_record_file
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/record/get")
    PolyvApiResult<PolyvChannelRecordDetail> getRecordDetail(@RequestParam PolyvChannelRecordDetailParameter param);

    /**
     * 创建重制课件任务
     * https://help.polyv.net/index.html#/live/api/channel/operate/add_record_task
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/pptRecord/addRecordTask")
    PolyvApiResult<Boolean> addRecordTask(@RequestParam PolyvChannelAddRecordTaskParameter param);

    /**
     * 批量转存录制视频到云点播
     * https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/async-convert/
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/record/convert")
    PolyvApiResult<String> recordConvert(@RequestParam PolyvChannelRecordConvertParameter param);

    /**
     * 异步合并直播录制文件
     * https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/async-merge/
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/record/merge")
    PolyvApiResult<String> recordMerge(@RequestParam PolyvChannelRecordMergeParameter param);

    /**
     * 合并直播录制文件并回调mp4下载地址
     * https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/merge-record-mp4/
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/record/merge-mp4")
    PolyvApiResult<PolyvMergeMp4> recordMergeMp4(@RequestParam PolyvChannelRecordMergeMp4Parameter param);

    /**
     * 删除直播暂存中的录制文件
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/lzhf/delete-record/
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channel/recordFile/{channelId}/delete-record")
    PolyvApiResult<String> recordDelete(@RequestParam PolyvChannelRecordDeleteParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 删除视频库中视频
     * https://help.polyv.net/index.html#/live/api/channel/playback/delete_playback
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channel/recordFile/{channelId}/playback/delete")
    PolyvApiResult<String> playBackDelete(@RequestParam PolyvChannelPlayBackDeleteParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 获取可添加到回放列表的视频列表
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/playback/get-video-pools")
    PolyvApiResult<PolyvPaginator<PolyvChannelPlayBackVideoPoolItem>> getVideoPools(@RequestParam PolyvChannelPlayBackVideoPoolsParameter param);

    /**
     * 批量删除暂存列表视频
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/record/batch-delete")
    PolyvApiResult<String> recordDeleteBatch(@RequestParam PolyvChannelRecordBatchDeleteParameter param);

    /**
     * 批量删除回放列表的回放视频
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/playback/batch-delete")
    PolyvApiResult<String> playBackDeleteBatch(@RequestParam PolyvChannelPlayBackBatchDeleteParameter param);

    /**
     * 修改视频库视频排序
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/lzhf/sort-playback/
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/playback/sort", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<String> playBackSort(@RequestParam PolyvChannelPlayBackSortParameter param, @RequestBody PolyvChannelPlayBackSortParameter.PolyvChannelPlayBackSortParameterBody body);

    default PolyvApiResult<String> playBackSort(PolyvChannelPlayBackSortParameter param) {
        PolyvChannelPlayBackSortParameter.PolyvChannelPlayBackSortParameterBody body = param.getBody();
        param.setBody(null);
        return playBackSort(param, body);
    }

    /**
     * 回放列表视频单个排序
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/playback/single-sort")
    PolyvApiResult<String> playBackSingleSort(@RequestParam PolyvChannelPlayBackSingleSortParameter param);

    /**
     * 将点播视频添加到视频库
     * https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/add-vod-playback/
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/playback/add")
    PolyvApiResult<PolyvChannelPlayBackItem> playBackAddVideos(@RequestParam PolyvChannelPlayBackAddVideosParameter param);

    /**
     * 批量添加点播视频到回放列表
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/playback/add-videos")
    PolyvApiResult<String> playBackAddBatchAddVideos(@RequestParam PolyvChannelPlayBackBatchAddParameter param);

    /**
     * 通过socket发送聊天室商品信息
     *
     * @param param
     * @param channelId
     * @param appId
     * @param timestamp
     * @param sign
     * @param status
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/chat/send-product-msg?channelId={channelId}&appId={appId}&timestamp={timestamp}&sign={sign}&status={status}")
    PolyvApiResult<Object> sendProductMsg(@RequestBody Object param,
                                          @PathVariable("channelId") Integer channelId, @PathVariable("appId") String appId,
                                          @PathVariable("timestamp") Long timestamp, @PathVariable("sign") String sign,
                                          @PathVariable("status") Integer status);

    /**
     * 频道页面皮肤设置
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/theme/set")
    PolyvApiResult<Boolean> themeSet(@RequestParam Map<String, Object> param);

    /**
     * 查询连麦详单
     * https://dev.polyv.net/2020/uncategorized/link-mic-detail-list/
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/mic/log/list-detail")
    PolyvApiResult<PolyvPaginator<PolyvMicLog>> getMicLogList(@RequestParam PolyvChannelMicLogListParameter param);


//    /**
//     * 获取频道简略详细
//     *
//     * @param param
//     * @return
//     */
//    @PolyvGetMapping(value = "/live/v3/channel/detail/get")
//    PolyvApiBaseResult<PolyvChannelInfo> getDetail(@RequestParam PolyvChannelParameter param);

    /**
     * 获取频道详细
     * https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/pdcz/getchannelid-2/
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/channels/{channelId}/get")
    PolyvApiResult<PolyvChannelInfo> getDetail(@RequestParam PolyvChannelParameter param, @PathVariable("channelId") String channelId);

    /**
     * 创建channel
     * https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/pdcz/create-channel/
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channels")
    PolyvApiResult<PolyvChannel> create(PolyvChannelCreateParameter param);

    /**
     * 创建channel
     * https://help.polyv.net/index.html#/live/api/channel/operate/basic_create
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/basic/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<PolyvChannelV3> createV3(@RequestParam PolyvChannelCreateV3Parameter param, @RequestBody PolyvChannelCreateV3Parameter.PolyvChannelCreateV3ParameterBody body);

    default PolyvApiResult<PolyvChannelV3> createV3(PolyvChannelCreateV3Parameter param) {
        PolyvChannelCreateV3Parameter.PolyvChannelCreateV3ParameterBody body = param.getBody();
        param.setBody(null);
        System.out.println(FastJsonUtils.toJsonString(body));
        return createV3(param, body);
    }

    /**
     * 创建频道v4
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v4/channel/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<PolyvChannelV4> createV4(@RequestParam PolyvChannelCreateV4Parameter param, @RequestBody PolyvChannelCreateV4Parameter.PolyvChannelCreateV4ParameterBody body);

    default PolyvApiResult<PolyvChannelV4> createV4(PolyvChannelCreateV4Parameter param) {
        PolyvChannelCreateV4Parameter.PolyvChannelCreateV4ParameterBody body = param.getBody();
        param.setBody(null);
        return createV4(param, body);
    }


    /**
     * 查询账号下所有频道缩略信息
     * https://dev.polyv.net/2018/liveproduct/l-api/zhsz/get-simple-channel-list/
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/management/list")
    PolyvApiResult<PolyvPaginator<PolyvChannelBriefInfo>> getBriefInfoList(@RequestParam PolyvChannelBriefInfoListParameter param);

    /**
     * 查询账号下的频道列表
     * https://dev.polyv.net/2016/liveproduct/l-api/zhsz/channels/
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/user/channels")
    PolyvApiResult<PolyvChannelIdList> list(@RequestParam PolyvAppNoneParamParameter param);

    /**
     * 查询所有频道的基础信息
     * https://dev.polyv.net/2020/liveproduct/l-api/zhsz/user-channel-basic-list/
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/basic/list")
    PolyvApiResult<PolyvPaginator<PolyvChannelBasic>> getBasicList(@RequestParam PolyvChannelBasicListParameter param);

    /**
     * 查询账号下所有频道详细信息
     * https://dev.polyv.net/2019/liveproduct/l-api/zhsz/chennel-detail/
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/management/list-detail")
    PolyvApiResult<PolyvPaginator<PolyvChannelDetail>> getDetailList(PolyvChannelDetailListParameter param);

    /**
     * 删除channel
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channels/{channelId}/delete")
    PolyvApiResult<Boolean> delete(@RequestParam PolyvChannelDeleteParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 查询多个频道汇总的统计数据
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/gksj/channel_play_summary/
     *
     * @param param
     * @param userId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/statistics/{userId}/channel_summary")
    PolyvApiResult<List<PolyvChannelStatistics>> getStatisticsSummary(@RequestParam PolyvAppStatisticsSummaryParameter param, @PathVariable(name = "userId") String userId);

    /**
     * 分页查询频道观看日志
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/statistics/viewlog-with-checkin")
    PolyvApiResult<PolyvPaginator<PolyvChannelViewLog>> getViewLogList(@RequestParam PolyvChannelViewLogListParameter param);

    /**
     * 查询子频道信息
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/get-account/
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/channelAccount/{channelId}/account")
    PolyvApiResult<PolyvChannelAccount> getAccountDetail(@RequestParam PolyvChannelAccountDetailParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 查询所有子频道信息
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/get-accounts/
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/channelAccount/{channelId}/accounts")
    PolyvApiResult<List<PolyvChannelAccount>> getAccountList(@RequestParam PolyvChannelNoneParamParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 创建子频道
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/add-account/
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channelAccount/{channelId}/add")
    PolyvApiResult<PolyvChannelAccount> addAccount(@RequestParam PolyvChannelCreateAccountParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 修改频道详细信息
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/detail-update/
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/detail/update")
    PolyvApiResult<Boolean> update(PolyvChannelUpdateParameter param);

    @PolyvPostMapping(value = "/live/v3/channel/basic/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<Object> updateV3(@RequestParam PolyvChannelUpdateV3Parameter param, @RequestBody PolyvChannelUpdateV3Parameter.PolyvChannelUpdateV3ParameterBody body);

    default PolyvApiResult<Object> updateV3(PolyvChannelUpdateV3Parameter param) {
        PolyvChannelUpdateV3Parameter.PolyvChannelUpdateV3ParameterBody body = param.getBody();
        param.setBody(null);
        return updateV3(param, body);
    }

    /**
     * 频道回放开关设置
     * https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/set-setting/
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/playback/set-setting")
    PolyvApiResult<Boolean> updatePlayBackSetting(PolyvChannelUpdatePlayBackEnabledParameter param);


    /**
     * 设置讲师信息
     * https://dev.polyv.net/2019/liveproduct/zblts/update-channel-teacher/
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/account/updateTeacher")
    PolyvApiResult<Object> updateTeacher(PolyvChannelUpdateTeacherParameter param);

    /**
     * 查询频道问卷列表
     * https://dev.polyv.net/2019/liveproduct/l-api/zbhd/list-questionaire/
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/questionnaire/list")
    PolyvApiResult<PolyvPaginator<PolyvQuestionnaireReview>> getQuestionnaireList(@RequestParam PolyvChannelQuestionnaireListParameter param);

    /**
     * 问卷导出
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/questionnaire/export")
    ResponseEntity<byte[]> exportQuestionnaire(@RequestParam PolyvChannelQuestionnaireDetailParameter param);

    /**
     * 问卷详情
     * https://dev.polyv.net/2019/liveproduct/l-api/zbhd/get-questionnaire-detail/
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/questionnaire/detail")
    PolyvApiResult<PolyvQuestionnaire> getQuestionnaireDetail(@RequestParam PolyvChannelQuestionnaireDetailParameter param);

    /**
     * 问卷统计
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/questionnaire/get-stats-detail")
    PolyvApiResult<PolyvQuestionnaireStats> getQuestionnaireStats(@RequestParam PolyvChannelQuestionnaireDetailParameter param);


    /**
     * 查询频道直播场次信息
     * https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/get-channel-sessions/
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/session/list")
    PolyvApiResult<PolyvPaginator<PolyvChannelSession>> getSessionList(@RequestParam PolyvChannelSessionListParameter param);


    /**
     * 设置子频道信息
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/update-account/
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channelAccount/{channelId}/update")
    PolyvApiResult<String> updateAccount(@RequestParam PolyvChannelUpdateAccountParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 删除子频道
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/delete-account/
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channelAccount/{channelId}/delete")
    PolyvApiResult<Object> deleteAccount(@RequestParam PolyvChannelDeleteAccountParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 获取频道授权和连麦的token
     * http://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/get-chat-token/
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/common/get-chat-token")
    PolyvApiResult<PolyvChannelChatToken> getChatToken(PolyvChannelChatTokenParameter param);


    /**
     * 获取频道实时推流信息
     * https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/hqzbxx/get-stream-info/
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/monitor/get-stream-info")
    PolyvApiResult<PolyvChannelStream> getStreamInfo(PolyvChannelNoneParamParameter param);


    /**
     * 恢复直播频道推流
     * https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/hqzbxx/resume/
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/stream/{channelId}/resume")
    PolyvApiResult<Object> resumeStream(@RequestParam PolyvChannelNoneParamParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 停止推流
     * https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/hqzbxx/banpush/
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/stream/{channelId}/cutoff")
    PolyvApiResult<Object> cutoffStream(@RequestParam PolyvChannelNoneParamParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 修改频道状态为无直播
     * https://help.polyv.net/index.html#/live/api/channel/state/set_status_end
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channels/{channelId}/end")
    PolyvApiResult<Object> endStream(@RequestParam PolyvChannelNoneParamParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 修改频道状态为直播中
     * https://help.polyv.net/index.html#/live/api/channel/state/set_status_start
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channels/{channelId}/live")
    PolyvApiResult<Object> liveStream(@RequestParam PolyvChannelNoneParamParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 查询视频库列表
     * https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/getplaybacklist/
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/channel/recordFile/{channelId}/playback/list")
    PolyvApiResult<PolyvPaginator<PolyvChannelPlayBackItem>> getPlayBackList(@RequestParam PolyvChannelPlayBackListParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 查询频道的回放开关状态
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/lzhf/get-playback-enbaled/
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/playback/get-enabled")
    PolyvApiResult<EnableSetting> getPlayBackEnabled(@RequestParam PolyvChannelNoneParamParameter param);

    /**
     * 设置频道是否应用通用设置开关
     * https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/kjsz/update-global-enabled/
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/common/update-global-enabled")
    PolyvApiResult<Boolean> updateGlobalEnabled(PolyvChannelUpdateGlobalEnabledParameter param);

    /**
     * 设置单点登陆token
     * https://dev.polyv.net/2018/uncategorized/set-token/
     * https://dev.polyv.net/2020/liveproduct/l-api/zhsz/sso/
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channels/{channelId}/set-token")
    PolyvApiResult<Object> setToken(PolyvChannelUpdateTokenParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 设置子频道单点登陆token
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/set-account-token/
     * <p>
     * https://dev.polyv.net/2020/liveproduct/l-api/zhsz/sso/
     *
     * @param param
     * @param accountId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channels/{accountId}/set-account-token")
    PolyvApiResult<Object> setAccountToken(PolyvChannelUpdateAccountTokenParameter param, @PathVariable(name = "accountId") String accountId);

    /**
     * 修改频道推流方式
     * https://help.polyv.net/index.html#/live/api/channel/operate/update_stream_type
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/stream/update")
    PolyvApiResult<String> updateStream(@RequestParam PolyvChannelUpdateStreamParameter param);


    /**
     * 获取直播频道独立授权的secretkey
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/auth/direct/get-secret-key")
    PolyvApiResult<PolyvSecretKey> getSecretKey(@RequestParam PolyvChannelNoneParamParameter param);

    /**
     * 查询频道发起的签到记录
     * https://help.polyv.net/index.html#/live/api/live_interaction/get_checkin_by_sessionid
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/chat/checkin-by-sessionId")
    PolyvApiResult<List<PolyvChannelCheckin>> listCheckinBySessionId(@RequestParam PolyvChannelCheckinListParameter param);

    /**
     * 查询发起签到记录查询签到结果
     * https://help.polyv.net/index.html#/live/api/live_interaction/get_checkin_by_checkid
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/chat/get-checkins")
    PolyvApiResult<List<PolyvCheckinStats>> listCheckinDetail(@RequestParam PolyvChannelCheckinDetailParameter param);

    /**
     * 查询多个频道实时并发数据
     * https://help.polyv.net/index.html#/live/api/channel/viewdata/get_realtime_viewers
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/statistics/get-realtime-viewers")
    PolyvApiResult<List<PolyvChannelViewers>> getRealtimeViewer(@RequestParam PolyvChannelsRealtimeViewerListParameter param);

    /**
     * 查询频道聊天记录
     * https://help.polyv.net/index.html#/live/api/chat/message/get_message_list
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/chat/get-history")
    PolyvApiResult<List<PolyvChannelChatHistoryV3>> getChatHistory(@RequestParam PolyvChannelChatHistoryParameter param);

    /**
     * 查询重制课件任务列表
     * https://help.polyv.net/index.html#/live/api/channel/operate/pptrecord_list
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/pptRecord/list")
    PolyvApiResult<PolyvPaginator<PolyvChannelPptRecordList>> getPptRecordList(@RequestParam PolyvChannelPptRecordListParameter param);

    /**
     * 查询引导图
     * https://help.polyv.net/index.html#/live/api/web/info/getsplash
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/channelSetting/{channelId}/getSplash")
    PolyvApiResult<PolyvSplash> getSplash(@RequestParam PolyvChannelNoneParamParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 查询频道直播开始时间
     * https://help.polyv.net/index.html#/live/api/web/info/get_countdown
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/channelSetting/{channelId}/get-countdown")
    PolyvApiResult<PolyvCountDown> getCountDown(@RequestParam PolyvChannelNoneParamParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 修改频道名称
     * https://help.polyv.net/index.html#/live/api/web/info/updatechannelname
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channels/{channelId}/update")
    PolyvApiResult<Boolean> updateChannelName(@RequestParam PolyvChannelUpdateNameParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 修改频道图标
     * https://help.polyv.net/index.html#/live/api/web/info/updatechannellogo
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channelSetting/{channelId}/setCoverImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    PolyvApiResult<String> _updateChannelCoverImg(@RequestParam PolyvChannelUpdateCoverImgParameter param, @RequestPart("imgfile") MultipartFile imgfile, @PathVariable(name = "channelId") String channelId);

    default PolyvApiResult<String> updateChannelCoverImg(PolyvChannelUpdateCoverImgParameter param, String channelId) {
        return _updateChannelCoverImg(param, param.getImgfile(), channelId);
    }

    /**
     * 修改引导图
     * https://help.polyv.net/index.html#/live/api/web/info/setsplash
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channelSetting/{channelId}/setSplash", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    PolyvApiResult<String> _updateSplash(@RequestParam PolyvChannelUpdateSplashParameter param, @RequestPart("imgfile") MultipartFile imgfile, @PathVariable(name = "channelId") String channelId);

    default PolyvApiResult<String> updateSplash(PolyvChannelUpdateSplashParameter param, String channelId) {
        return _updateSplash(param, param.getImgfile(), channelId);
    }

    /**
     * 修改频道点赞数和观看次数
     * https://help.polyv.net/index.html#/live/api/web/info/update_likes
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/channels/{channelId}/update-likes")
    PolyvApiResult<String> updateLikes(@RequestParam PolyvChannelUpdateLikesParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 修改直播倒计时设置
     * https://help.polyv.net/index.html#/live/api/web/info/set_countdown
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channelSetting/{channelId}/set-countdown")
    PolyvApiResult<String> updateCountDown(@RequestParam PolyvChannelUpdateCountDownParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 修改频道观看人数限制
     * https://help.polyv.net/index.html#/live/api/channel/operate/set_max_viewer
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channelRestrict/{channelId}/set-max-viewer")
    PolyvApiResult<String> updateMaxViewer(@RequestParam PolyvChannelUpdateMaxViewerParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 查询频道实时并发数据
     * https://help.polyv.net/index.html#/live/api/channel/viewdata/realviewers
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvGetMapping(value = "/live/v1/statistics/{channelId}/realtime")
    PolyvChannelStatisticsRealtime getStaticistRealtime(@RequestParam PolyvChannelStatisticsRealtimeParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 查询频道多场次概览统计数据
     * https://help.polyv.net/index.html#/live/api/channel/viewdata/get_session_stats
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/statistics/get-session-stats")
    PolyvApiResult<PolyvChannelStaticistGetSessionStats> getStaticistGetSessionStats(@RequestParam PolyvChannelStaticistGetSessionStatsParameter param);

    /**
     * 查询频道历史并发数据
     * https://help.polyv.net/index.html#/live/api/channel/viewdata/concurrency
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/statistics/concurrence")
    PolyvApiResult<List<PolyvChannelStatisticsConcurrence>> getStatisticsConcurrence(@RequestParam PolyvChannelStatisticsConcurrenceParameter param);

    /**
     * 查询频道历史最高并发数据
     * https://help.polyv.net/index.html#/live/api/channel/viewdata/get_max_history_concurrent
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/statistics/get-max-history-concurrent")
    PolyvApiResult<String> getMaxHistoryConcurrent(@RequestParam PolyvChannelStatisticsGetMaxHistoryParameter param);

    /**
     * 查询频道直播观看详情数据
     * https://help.polyv.net/index.html#/live/api/channel/viewdata/viewlog_2
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvGetMapping(value = "/live/v1/statistics/{channelId}/viewlog")
    PolyvChannelViewLogV3 getChannelViewLog(@RequestParam PolyvChannelStatisticsViewLogParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 查询频道某段时间的直播观看详情数据
     * https://help.polyv.net/index.html#/live/api/channel/viewdata/summary
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/statistics/{channelId}/summary")
    PolyvChannelStatisticsSummary getStatisticsSummary(@RequestParam PolyvChannelStatisticsSummaryParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 查询频道提问记录
     * https://help.polyv.net/index.html#/live/api/live_interaction/get_question_list
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/chat/{channelId}/getQuestion")
    PolyvApiResult<List<PolyvChannelChatGetQuestion>> getChannelQuestionRecord(@RequestParam PolyvChannelChatGetQuestionParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 查询频道登记观看记录
     * https://help.polyv.net/index.html#/live/api/web/watch_condition/get_record_info
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/auth/get-record-info")
    PolyvApiResult<PolyvPaginator<PolyvChannelAuthGetRecordInfo>> getChannelAuthRecordInfo(@RequestParam PolyvChannelAuthGetRecordInfoParameter param);

    /**
     * 查询频道中奖记录
     * https://help.polyv.net/index.html#/live/api/live_interaction/list_lottery
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/lottery/list-lottery")
    PolyvApiResult<PolyvPaginator<PolyvChannelLotteryListDetail>> getChannelListLottery(@RequestParam PolyvChannelLotteryListLotteryParameter param);

    /**
     * 查询频道场次中奖记录
     * https://help.polyv.net/index.html#/live/api/live_interaction/get_winner_detail
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/lottery/get-winner-detail")
    PolyvApiResult<PolyvPaginator<PolyvChannelLotteryListDetail>> getChannelWinnerDetail(@RequestParam PolyvChannelLotteryGetWinDetailParameter param);

    /**
     * 查询频道问卷结果
     * https://help.polyv.net/index.html#/live/api/live_interaction/get_questionnaire_result
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/questionnaire/answer-records")
    PolyvApiResult<List<PolyvQuestionnaireRecord>> getChannelQuestionnaireResult(@RequestParam PolyvChannelQuestionnaireRecordParameter param);

    /**
     * 分页查询频道问卷结果
     * https://help.polyv.net/index.html#/live/api/live_interaction/list_questionnaire_by_page
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/questionnaire/list-answer-records")
    PolyvApiResult<PolyvPaginator<PolyvQuestionnaireRecord>> getChannelQuestionaireResultList(@RequestParam PolyvChannelQuestionnaireRecordListParameter param);


    /**
     * 查询频道签到结果
     * https://help.polyv.net/index.html#/live/api/live_interaction/get_checkin_list
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/checkin/list")
    PolyvApiResult<PolyvPaginator<PolyvCheckinStats>> getChannelCheckinList(@RequestParam PolyvChannelCheckinListPageParameter param);

    /**
     * 下载频道登记观看记录
     * https://help.polyv.net/index.html#/live/api/web/watch_condition/download_record_info
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/auth/download-record-info")
    ResponseEntity<byte[]> getAuthDownload(@RequestParam PolyvChannelAuthDownloadRecordParameter param);

    /**
     * 下载频道场次中奖记录
     * https://help.polyv.net/index.html#/live/api/live_interaction/download_winner_detail
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/lottery/download-winner-detail")
    ResponseEntity<byte[]> getLotteryDeatilDownLoad(@RequestParam PolyvChannelLotteryDetailDownloadParameter param);

    /**
     * 修改视频库的默认视频
     * https://help.polyv.net/index.html#/live/api/channel/playback/set_record_default
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channel/recordFile/{channelId}/playback/set-Default")
    PolyvApiResult<String> playBackSetDefault(@RequestParam PolyvChannelPlayBackSetDefaultParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 修改回放视频名称
     * https://help.polyv.net/index.html#/live/api/channel/playback/update_playback_title
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/playback/update-title")
    PolyvApiResult<String> playBackUpdateTitle(@RequestParam PolyvChannelPlayBackUpdateTitleParameter param);

    /**
     * 裁剪录制文件
     * https://help.polyv.net/index.html#/live/api/channel/playback/clip_record_file
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/record/clip")
    PolyvApiResult<String> recordClip(@RequestParam PolyvChannelRecordClipParameter param);

    /**
     * 查询单个子频道信息
     * https://help.polyv.net/index.html#/live/api/channel/operate/get_account
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/channelAccount/{channelId}/account")
    PolyvApiResult<PolyvChannelAccount> getSingleChannelAccount(@RequestParam PolyvChannelSearchSingleAccountParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 查询频道号下所有子频道信息
     * https://help.polyv.net/index.html#/live/api/channel/operate/get_account
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/channelAccount/{channelId}/accounts")
    PolyvApiResult<List<PolyvChannelAccount>> getAllChannelAccount(@RequestParam PolyvChannelSearchAccountParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 获取频道API的访问令牌
     * https://help.polyv.net/index.html#/live/api/channel/auth/get_channel_api_access_token
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/common/token/get-channel-token")
    PolyvApiResult<PolyvChannelToken> getChannelApiToken(@RequestParam PolyvChannelCreateTokenParameter param);

    /**
     * 修改暖场设置开关
     * https://help.polyv.net/index.html#/live/api/channel/warmup/update_warmup_switch
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/set-warmup-enabled")
    PolyvApiResult<String> updateWarmUpEnable(@RequestParam PolyvChannelUpdateWarmUpParameter param);

    /**
     * 修改暖场图片
     * https://help.polyv.net/index.html#/live/api/channel/warmup/update_warmup_image
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channels/{channelId}/update")
    PolyvApiResult<String> updateWarmUpImage(@RequestParam PolyvChannelUpdateWarmUpImageParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 修改暖场视频
     * https://help.polyv.net/index.html#/live/api/channel/warmup/update_warmup_video
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channels/{channelId}/update")
    PolyvApiResult<String> updateWarmUpVideo(@RequestParam PolyvChannelUpdateWarmUpVideoParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 增加页面菜单
     * https://help.polyv.net/index.html#/live/api/web/menu/add_menu
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/menu/add")
    PolyvApiResult<PolyvChannelMenuDetail> createMenu(@RequestParam PolyvChannelCreateMenuParameter param);

    /**
     * 查询频道页面菜单信息
     * https://help.polyv.net/index.html#/live/api/web/menu/channel_menu_list
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/menu/list")
    PolyvApiResult<List<PolyvChannelMenuDetail>> searchMenuList(@RequestParam PolyvChannelSearchMenuListParameter param);

    /**
     * 修改页面菜单排序
     * https://help.polyv.net/index.html#/live/api/web/menu/update_rank
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/menu/update-rank")
    PolyvApiResult<String> updateMenuRank(@RequestParam PolyvChannelUpdateMenuRankParameter param);

    /**
     * 修改页面菜单信息
     * https://help.polyv.net/index.html#/live/api/web/menu/update_channel_menu
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/menu/update")
    PolyvApiResult<String> updateMenuInfo(@RequestParam PolyvChannelUpdateMenuInfoParameter param);

    /**
     * 查询频道图文直播内容
     * https://help.polyv.net/index.html#/live/api/web/menu/tuwen_list
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/watch/tuwen/list")
    PolyvApiResult<PolyvChannelMenuList> searchImageMenuList(@RequestParam PolyvChannelSearchImageMenuListParameter param);

    /**
     * 修改提问功能开关
     * https://help.polyv.net/index.html#/live/api/web/menu/update_consulting_enabled
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channel/menu/{channelId}/update-consulting-enabled")
    PolyvApiResult<Object> updateMenuConsult(@RequestParam PolyvChannelUpdateMenuConsultParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 上传文档到某个频道
     * https://help.polyv.net/index.html#/live/api/channel/doc/upload_doc
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/document/upload-doc", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<PolyvDocumentUpload> _uploadDocument(@RequestParam PolyvChannelDocumentUploadFileParameter param, @RequestPart("file") MultipartFile file);

    default PolyvApiResult<PolyvDocumentUpload> uploadDocument(PolyvChannelDocumentUploadParameter param) {
        PolyvChannelDocumentUploadFileParameter parameter = BeanUtil.copy(param, PolyvChannelDocumentUploadFileParameter.class);
        return _uploadDocument(parameter, param.getFile());
    }

    /**
     * 查询频道已上传文档列表
     * https://help.polyv.net/index.html#/live/api/channel/doc/get_doc_list
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/document/doc-list")
    PolyvApiResult<PolyvPaginator<PolyvDocumentInfo>> getDocumentList(@RequestParam PolyvChannelDocumentListParameter param);

    /**
     * 查询频道文档转码状态
     * https://help.polyv.net/index.html#/live/api/channel/doc/get_doc_convert_status
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/document/status/get")
    PolyvApiResult<List<PolyvDocumentStatus>> getDocumentStatus(@RequestParam PolyvChannelDocumentStatusParameter param);

    /**
     * 删除文档
     * https://help.polyv.net/index.html#/live/api/channel/doc/delete_document
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/document/delete")
    PolyvApiResult<String> deleteDocument(@RequestParam PolyvChannelDocumentDeleteParameter param);

    /**
     * 删除菜单
     * https://help.polyv.net/index.html#/live/api/web/menu/menu_delete
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/menu/delete")
    PolyvApiResult<String> deleteMenu(@RequestParam PolyvChannelDeleteMenuParameter param);

    /**
     * 查询频道直播截图
     * https://help.polyv.net/index.html#/live/api/channel/operate/get_capture_image
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/stream/{channelId}/capture")
    PolyvApiResult<String> searchCapture(@RequestParam PolyvChannelSearchCaptureParameter param, @PathVariable("channelId") String channelId);


    /**
     * 设置伪直播
     * https://help.polyv.net/index.html#/live/api/channel/operate/add_disk_videos
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/stream/add-disk-videos")
    PolyvApiResult<String> createDisk(@RequestParam PolyvChannelCreateDiskVideoParameter param);

    /**
     * 删除伪直播
     * https://help.polyv.net/index.html#/live/api/channel/operate/delete_disk_videos
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/stream/delete-disk-videos")
    PolyvApiResult<String> deleteDisk(@RequestParam PolyvChannelDeleteDiskVideoParameter param);

    /**
     * 查询转播频道信息
     * https://help.polyv.net/index.html#/live/api/channel/operate/get_transmit_associations
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/transmit/get-associations")
    PolyvApiResult<List<PolyvChannelTransmit>> searchTransmit(@RequestParam PolyvChannelSearchTransmitParameter param);

    /**
     * 跑马灯设置
     * https://help.polyv.net/index.html#/live/api/player/set_marquee
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channelRestrict/{channelId}/set-diyurl-marquee")
    PolyvApiResult<String> setMarquee(@RequestParam PolyvChannelCreateMarqueeParameter param, @PathVariable("channelId") String channelId);

//    /**
//     * 添加频道商品
//     * @param parameter
//     * @param body
//     * @return
//     */
//    @PolyvPostMapping( value = "/live/v3/channel/product/add", consumes = MediaType.APPLICATION_JSON_VALUE)
//    PolyvApiResult<PolyvChannelProduct> createPorduct(@RequestParam PolyvChannelCreateProductParameter parameter,@RequestBody PolyvChannelCreateProductParameter.PolyvChannelCreateProductParameterBody body);
//
//    default PolyvApiResult<PolyvChannelProduct> createPorduct( PolyvChannelCreateProductParameter parameter ){
//        PolyvChannelCreateProductParameter.PolyvChannelCreateProductParameterBody body = parameter.getBody();
//        parameter.setBody(null);
//        return createPorduct( parameter,body );
//    }

    /**
     * 查询频道商品库开关状态
     * https://help.polyv.net/index.html#/live/api/channel/operate/get_channel_product_enabled
     *
     * @param parameter
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/product/get-enabled")
    PolyvApiResult<PolyvChannelProductEnabled> checkEnable(@RequestParam PolyvChannelCheckProductEnableParameter parameter);

    /**
     * 查询频道商品列表
     * https://help.polyv.net/index.html#/live/api/channel/operate/get_channel_product_list
     *
     * @param parameter
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/product/list")
    PolyvApiResult<PolyvPaginator<PolyvChannelProduct>> searchProductList(@RequestParam PolyvChannelSearchProductListParameter parameter);

    /**
     * 推送频道商品库商品
     * https://help.polyv.net/index.html#/live/api/channel/operate/push_channel_product
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/product/push-product")
    PolyvApiResult<String> pushProduct(@RequestParam PolyvChannelPushProductParameter param);

    /**
     * 修改频道商品库开关状态
     * https://help.polyv.net/index.html#/live/api/channel/operate/update_channel_product_enabled
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/product/update-enabled")
    PolyvApiResult<String> updateProductEnabled(@RequestParam PolyvChannelUpdateProductEnabledParameter param);


    /**
     * 修改频道商品库上下架状态
     * https://help.polyv.net/index.html#/live/api/channel/operate/shelf_channel_product
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/product/shelf")
    PolyvApiResult<String> updateProductShelf(@RequestParam PolyvChannelUpdateProductShelfParameter param);

    /**
     * 修改频道商品库顺序
     * https://help.polyv.net/index.html#/live/api/channel/operate/sort_channel_product
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/product/sort")
    PolyvApiResult<String> updateProductSort(@RequestParam PolyvChannelUpdateProductSortParameter param);

    /**
     * 删除频道商品
     * https://help.polyv.net/index.html#/live/api/channel/operate/delete_channel_product
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/product/delete")
    PolyvApiResult<String> deleteProduct(@RequestParam PolyvChannelDeleteProductParameter param);

    /**
     * 发送点赞
     * https://help.polyv.net/index.html#/live/api/live_interaction/send_favor
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channels/{channelId}/like")
    PolyvApiResult<String> sentFavor(@RequestParam PolyvChannelSentFavorParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 禁言/解禁用户
     * https://help.polyv.net/index.html#/live/api/chat/banned/update_banned_user
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/chat/banned-user")
    PolyvApiResult<String> updateBannedUser(@RequestParam PolyvChannelUpdateBannedUserParameter param);

    /**
     * 禁言IP
     * https://help.polyv.net/index.html#/live/api/chat/banned/add_banned_ip
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/chat/{channelId}/addBannedIP")
    PolyvApiResult<List<String>> addBannedIp(@RequestParam PolyvChannelAddBannedIpParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 查询频道禁言用户Userid/IP
     * https://help.polyv.net/index.html#/live/api/chat/banned/get_channel_banned_user_list
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/chat/get-banned-list")
    PolyvApiResult<List<String>> getBannedList(@RequestParam PolyvChannelBannedListParameter param);

    /**
     * 查询频道严禁词/禁言ip
     * https://help.polyv.net/index.html#/live/api/chat/banned/get_channel_banned_list
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/badword/list")
    PolyvApiResult<List<String>> getBadWordsList(@RequestParam PolyvChannelBadWordsListParameter param);

    /**
     * 删除频道严禁词/禁言ip
     * https://help.polyv.net/index.html#/live/api/chat/banned/delete_channel_banned
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/chat/{channelId}/delBanned")
    PolyvApiResult<String> deleteBadWord(@RequestParam PolyvChannelBadWordDeleteParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 批量删除频道多条聊天记录
     * https://help.polyv.net/index.html#/live/api/channel/operate/remove_chat_contents
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/chat/remove-contents")
    PolyvApiResult<String> chatDeleteBatch(@RequestParam PolyvChannelChatBatchDeleteParameter param);

    /**
     * 删除频道单条聊天记录
     * https://help.polyv.net/index.html#/live/api/chat/message/delete_message_by_id
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/chat/{channelId}/delChat")
    PolyvApiResult<String> chatDelete(@RequestParam PolyvChannelChatDeleteParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 清空频道聊天记录
     * https://help.polyv.net/index.html#/live/api/chat/message/delete_all_message
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/chat/{channelId}/cleanChat")
    PolyvApiResult<String> chatClean(@RequestParam PolyvChannelNoneParamParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 查询频道踢人列表
     * https://help.polyv.net/index.html#/live/api/chat/banned/get_channel_kicked_user_list
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/chat/list-kicked")
    PolyvApiResult<List<PolyvChannelKicked>> getKickedList(@RequestParam PolyvChannelNoneParamParameter param);

    /**
     * 修改管理员身份信息
     * https://help.polyv.net/index.html#/live/api/chat/role/update_admin_info
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channelSetting/{channelId}/set-chat-admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    PolyvApiResult<String> _updateAdminInfo(@RequestParam PolyvChannelUpdateAdminInfoParameter param, @RequestPart("avatar") MultipartFile avatar, @PathVariable(name = "channelId") String channelId);

    default PolyvApiResult<String> updateAdminInfo(PolyvChannelUpdateAdminInfoParameter param, String channelId) {
        return _updateAdminInfo(param, param.getAvatar(), channelId);
    }

    /**
     * 查询管理员身份信息
     * https://help.polyv.net/index.html#/live/api/chat/role/get_admin_info
     *
     * @param param
     * @param channelId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channelSetting/{channelId}/get-chat-admin")
    PolyvApiResult<PolyvAdminInfo> getAdminInfo(@RequestParam PolyvChannelNoneParamParameter param, @PathVariable(name = "channelId") String channelId);

    /**
     * 发送图文信息
     * https://help.polyv.net/index.html#/live/api/chat/message/send_message
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/chat/send-admin-msg")
    PolyvApiResult<String> sentMessage(@RequestParam PolyvChannelSentMessageParameter param);

    /**
     * 提交中奖者信息
     * https://help.polyv.net/index.html#/live/api/live_interaction/add_receive_info
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/chat/add-receive-info")
    PolyvApiResult<String> addReceiveInfo(@RequestParam PolyvChannelAddReceiveInfoParameter param);

    /**
     * 新增或修改频道问卷
     * https://help.polyv.net/index.html#/live/api/live_interaction/add_edit_questionnaire
     *
     * @param param
     * @param body
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/questionnaire/add-edit-questionnaire", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<PolyvChannelQuestionBasic> addQuestionnaire(@RequestParam PolyvChannelQuestionnaireAddParameter param, @RequestBody PolyvChannelQuestionnaireAddParameter.PolyvChannelQuestionnaireAddParameterBody body);

    default PolyvApiResult<PolyvChannelQuestionBasic> addQuestionnaire(PolyvChannelQuestionnaireAddParameter param) {
        PolyvChannelQuestionnaireAddParameter.PolyvChannelQuestionnaireAddParameterBody body = param.getBody();
        param.setBody(null);
        return addQuestionnaire(param, body);
    }


    /**
     * 添加频道商品
     *
     * @param parameter
     * @param body
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/product/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<PolyvChannelProduct> createPorduct(@RequestParam PolyvChannelCreateProductParameter parameter, @RequestBody PolyvChannelCreateProductParameter.PolyvChannelCreateProductParameterBody body);

    default PolyvApiResult<PolyvChannelProduct> createPorduct(PolyvChannelCreateProductParameter parameter) {
        PolyvChannelCreateProductParameter.PolyvChannelCreateProductParameterBody body = parameter.getBody();
        parameter.setBody(null);
        return createPorduct(parameter, body);
    }

    /**
     * 修改频道商品
     *
     * @param parameter
     * @param body
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/product/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<String> updatePorduct(@RequestParam PolyvChannelUpdateProductParameter parameter, @RequestBody PolyvChannelUpdateProductParameter.PolyvChannelUpdateProductParameterBody body);

    default PolyvApiResult<String> updatePorduct(PolyvChannelUpdateProductParameter parameter) {
        PolyvChannelUpdateProductParameter.PolyvChannelUpdateProductParameterBody body = parameter.getBody();
        parameter.setBody(null);
        return updatePorduct(parameter, body);
    }

    /**
     * 修改设置授权认证URL
     * https://help.polyv.net/index.html#/live/api/web/watch_condition/update_auth_url
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/restrict/update-auth-url")
    PolyvApiResult<String> updateAuthUrl(@RequestParam PolyvChannelUpdateAuthUrlParameter param);

    /**
     * 查询频道观看条件
     * https://help.polyv.net/index.html#/live/api/web/watch_condition/get_watch_condition
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/auth/get")
    PolyvApiResult<List<PolyvChannelViewCondition>> getViewCondition(@RequestParam PolyvChannelSearchViewConditionParameter param);

    /**
     * 修改频道观看条件
     * https://help.polyv.net/index.html#/live/api/web/watch_condition/set_watch_condition
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/auth/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<String> updateViewCondition(@RequestParam PolyvChannelUpdateViewConditionParameter param, @RequestBody PolyvChannelUpdateViewConditionParameter.AuthSettingsBody body);

    default PolyvApiResult<String> updateViewCondition(PolyvChannelUpdateViewConditionParameter parameter) {
        PolyvChannelUpdateViewConditionParameter.AuthSettingsBody body = parameter.getBody();
        parameter.setBody(null);
        System.out.println(FastJsonUtils.toJsonString(body));
        return updateViewCondition(parameter, body);
    }


    /**
     * 修改频道外部授权设置
     * https://help.polyv.net/index.html#/live/api/web/watch_condition/set_externalauth
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channelSetting/{userId}/auth-external")
    PolyvApiResult<List<PolyvChannelAuthExternal>> updateAuthExternal(@RequestParam PolyvChannelUpdateAuthExternalParameter param, @PathVariable("userId") String userId);

    /**
     * 修改频道自定义授权设置
     * https://help.polyv.net/index.html#/live/api/web/watch_condition/set_authorized_address
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channelSetting/{userId}/auth-external")
    PolyvApiResult<List<PolyvChannelAuthExternal>> updateOauthCustom(@RequestParam PolyvChannelUpdateOauthCustomParameter param, @PathVariable("userId") String userId);

    /**
     * 查询频道白名单
     * https://help.polyv.net/index.html#/live/api/web/watch_condition/get_white_list
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/auth/get-white-list")
    PolyvApiResult<PolyvPaginator<PolyvChannelWhiteInfo>> getWhiteList(@RequestParam PolyvChannelSearchWhiteListParameter param);

    /**
     * 增加频道单个白名单
     * https://help.polyv.net/index.html#/live/api/web/watch_condition/add_white_list
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/auth/add-white-list")
    PolyvApiResult<String> addWhiteList(@RequestParam PolyvChannelAddWhiteListParameter param);

    /**
     * 批量导入频道白名单
     * https://help.polyv.net/index.html#/live/api/web/watch_condition/upload_white_list
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/auth/upload-white-list", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    PolyvApiResult<String> _uploadWhiteList(@RequestParam PolyvChannelBatchUploadWhiteListFileParameter param, @RequestPart("file") MultipartFile file);

    default PolyvApiResult<String> uploadWhiteList(PolyvChannelBatchUploadWhiteListParameter param) {

        return _uploadWhiteList(BeanUtil.copy(param, PolyvChannelBatchUploadWhiteListFileParameter.class), param.getFile());
    }

    /**
     * 修改频道单个白名单
     * https://help.polyv.net/index.html#/live/api/web/watch_condition/update_white_list
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/auth/update-white-list")
    PolyvApiResult<String> updateWhiteList(@RequestParam PolyvChannelUpdateWhiteListParameter param);

    /**
     * 删除频道单个白名单
     * https://help.polyv.net/index.html#/live/api/web/watch_condition/delete_white_list
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/auth/delete-white-list")
    PolyvApiResult<String> deleteWhiteList(@RequestParam PolyvChannelDeleteWhiteListParameter param);

    /**
     * 查询频道登记观看设置的字段信息
     * https://help.polyv.net/index.html#/live/api/web/watch_condition/get_record_field
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/auth/get-record-field")
    PolyvApiResult<List<PolyvChannelAuthSetting.InfoField>> searchFields(@RequestParam PolyvChannelSearchRecordFieldParameter param);

    /**
     * 下载频道白名单
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/auth/download-white-list")
    ResponseEntity<byte[]> downLoadWhiteList(@RequestParam PolyvChannelDownLoadWhiteListParameter param);

    /**
     * 批量创建子频道
     * https://help.polyv.net/index.html#/live/api/channel/operate/batch_create
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/account/batch-create", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<List<PolyvChannelAccount>> _batchCreateAccount(@RequestParam PolyvChannelBatchCreateAccountParameter param, @RequestBody Object body);

    default PolyvApiResult<List<PolyvChannelAccount>> batchCreateAccount(PolyvChannelBatchCreateAccountParameter parameter) {
        List<PolyvChannelBatchCreateAccountItem> list = parameter.getList();
        parameter.setList(null);
        return _batchCreateAccount(parameter, list);
    }

    /**
     * 查询频道广告列表
     * https://help.polyv.net/index.html#/live/api/channel/operate/get_channel_adverts
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/advert/list")
    PolyvApiResult<List<PolyvAdverties>> searchAdvertList(@RequestParam PolyvChannelSearchAdvertListParameter param);


    /**
     * 修改频道播放器片头广告
     * https://help.polyv.net/index.html#/live/api/player/update_head
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channelAdvert/{channelId}/updateHead")
    PolyvApiResult<String> updatePolyvAdverties(@RequestParam PolyvChannelUpdateHeadAdvertParameter param, @PathVariable("channelId") String channelId);


    /**
     * 修改频道播放器暂停广告
     * https://help.polyv.net/index.html#/live/api/player/update_stop
     *
     * @param param
     * @return
     */

    @PolyvPostMapping(value = "/live/v2/channelAdvert/{channelId}/updateStop")
    PolyvApiResult<String> updatePolyvStopAdverties(@RequestParam PolyvChannelUpdateStopAdvertParameter param, @PathVariable("channelId") String channelId);


    /**
     * 查询频道微信分享信息
     * https://help.polyv.net/index.html#/live/api/web/page_interaction/get_weixin_share
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/weixin-share/get")
    PolyvApiResult<PolyvWeChatShare> searchWechatSearch(@RequestParam PolyvChannelSearchWeChatShareParameter param);

    /**
     * 修改频道微信分享信息
     * https://help.polyv.net/index.html#/live/api/web/page_interaction/update_weixin_share
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "live/v3/channel/weixin-share/update")
    PolyvApiResult<String> updateWeChatShare(@RequestParam PolyvChannelUpdateWeChatShareParameter param);


    /**
     * 设置现金打赏
     * https://help.polyv.net/index.html#/live/api/web/page_interaction/update_cash
     *
     * @param param
     * @param body
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/donate/update-cash", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<Object> updateDonateCash(@RequestParam PolyvChannelUpdateDonateCashParameter param, @RequestBody PolyvChannelUpdateDonateCashParameter.PolyvChannelUpdateDonateCashParameterBody body);

    default PolyvApiResult<Object> updateDonateCash(PolyvChannelUpdateDonateCashParameter param) {
        PolyvChannelUpdateDonateCashParameter.PolyvChannelUpdateDonateCashParameterBody body = param.getBody();
        param.setBody(null);
        return updateDonateCash(param, body);
    }

    /**
     * 设置道具打赏
     * https://help.polyv.net/index.html#/live/api/web/page_interaction/update_good
     *
     * @param param
     * @param body
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/donate/update-good", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<Object> updateDonateGood(@RequestParam PolyvChannelUpdateDonateGoodParameter param, @RequestBody PolyvChannelUpdateDonateGoodParameter.PolyvChannelUpdateDonateGoodParameterBody body);

    default PolyvApiResult<Object> updateDonateGood(PolyvChannelUpdateDonateGoodParameter param) {
        PolyvChannelUpdateDonateGoodParameter.PolyvChannelUpdateDonateGoodParameterBody body = param.getBody();
        param.setBody(null);
        return updateDonateGood(param, body);
    }

    /*
     * 发送打赏消息
     * https://help.polyv.net/index.html#/live/api/live_interaction/send_reward_msg
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/chat/send-reward-msg")
    PolyvApiResult<String> sentRewardMsg(@RequestParam PolyvChannelSentRewardMsgParameter param);

    /**
     * 修改频道的功能开关状态
     * https://help.polyv.net/index.html#/live/api/account/switch_update
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/switch/update")
    PolyvApiResult<Object> updateSwitch(@RequestParam PolyvChannelUpdateSwitchParameter param);

    /**
     * 查询频道的功能开关状态
     * https://help.polyv.net/index.html#/live/api/account/switch_get
     *
     * @param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/switch/get")
    PolyvApiResult<List<PolyvChannelSwitchState>> searchSwitch(@RequestParam PolyvChannelSearchSwitchParameter param);

    /**
     * 查询频道信息
     * https://help.polyv.net/index.html#/live/api/channel/operate/get_channel_detail_setting
     */
    @PolyvGetMapping(value = "/live/v3/channel/basic/get")
    PolyvApiResult<PolyvChannel> getPolyvChannel(@RequestParam PolyvChannelSearchParameter param);

    /**
     * 修改频道播放器中显示的logo
     * https://help.polyv.net/index.html#/live/api/player/update_logo
     */
    @PolyvPostMapping(value = "/live/v2/channels/{channelId}/update")
    PolyvApiResult<String> updateLogo(@RequestParam PolyvChannelLogoParameter param, @PathVariable("channelId") String channelId);

    /**
     * 分页查询频道直播观看详情数据
     * https://help.polyv.net/index.html#/live/api/channel/viewdata/viewlog_page
     */
    @PolyvGetMapping(value = "/live/v2/statistics/{channelId}/viewlog")
    PolyvApiResult<PolyvPaginator<PolyvChannelViewLogV1>> getViewLogV2(@RequestParam PolyvChannelSearchViewLogV2Parameter param, @PathVariable("channelId") String channelId);

    /**
     * 查询频道答题卡结果
     * https://help.polyv.net/index.html#/live/api/live_interaction/get_answer_list
     */

    @PolyvGetMapping(value = "/live/v3/channel/question/answer-records")
    PolyvApiResult<List<PolyvQuestionResult>> getQuestionResult(@RequestParam PolyvChannelSearchQuestionaireResultParameter param);

    /**
     * 删除重制课件
     * https://dev.polyv.net/2020/uncategorized/batch-delete-ppt-record/
     */
    @PolyvPostMapping(value = "/live/v3/channel/pptRecord/batch-delete")
    PolyvApiResult<PolyvChannelPptRecordResult> deletePptRecord(@RequestParam PolyvChannelDeletePptRecordParameter parameter );

    /**
     * 查询频道重制课件设置
     * http://api.polyv.net/live/v3/channel/pptRecord/get-setting
     */
    @PolyvGetMapping(value = "/live/v3/channel/pptRecord/get-setting")
    PolyvApiResult<PolyvPptRecordSetting> searchPptRecordSetting(@RequestParam PolyvChannelSearchPptRecordSettingParameter parameter );

    /**
     * 更新频道重制课件设置
     * https://help.polyv.net/index.html#/live/api/channel/operate/update_pptrecord_setting
     */
    @PolyvPostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/live/v3/channel/pptRecord/setting" )
    PolyvApiResult<Object> _updatePptRecordSetting(@RequestParam PolyvChannelUpdatePptRecordSettingDataParameter parameter, @RequestPart("brandImgFile") MultipartFile brandImgFile, @RequestPart("backgroundImgFile") MultipartFile backgroundImgFile );

    default PolyvApiResult<Object> updatePptRecordSetting(PolyvChannelUpdatePptRecordSettingParameter parameter){
        return _updatePptRecordSetting(BeanUtil.copy(parameter,PolyvChannelUpdatePptRecordSettingDataParameter.class),parameter.getBrandImgFile(),parameter.getBackgroundImgFile());
    }

    /**
     * 更新观看页引导设置
     * @param param
     * @param body
     * @return
     */
    @PolyvPostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/live/v4/channel/decorate/update" )
    PolyvApiResult<Object> _updateDecorate(@RequestParam PolyvChannelDecorateUpdateV4Parameter param, @RequestBody PolyvDecorateConfigV4 body);

    /**
     * @param param
     * @return
     */
    default PolyvApiResult<Object> updateDecorate(PolyvChannelDecorateUpdateV4Parameter param){
        PolyvDecorateConfigV4 body = param.getBody();
        param.setBody(null);
        return _updateDecorate(param, body);
    }

    /**
     * 查询频道页面装修(新版)
     * http://api.polyv.net/live/v4/channel/decorate/get
     * @param parameter
     * @return
     */
    @PolyvGetMapping( value = "/live/v4/channel/decorate/get")
    PolyvApiResult<PolyvDecorateConfigV4> getDecorate(@RequestParam PolyvChannelGetDecorateV4Parameter parameter);

    /**
     * 查询频道卡片推送
     * https://help.polyv.net/index.html#/live/api/v4/channel/market/cardPush/get
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v4/channel/card-push/list")
    PolyvApiResult<List<PolyvCardPushInfo>> cardPushList(@RequestParam PolyvChannelNoneParamParameter param);

    /**
     * 创建频道卡片推送
     * https://help.polyv.net/index.html#/live/api/v4/channel/market/cardPush/create
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v4/channel/card-push/create")
    PolyvApiResult<PolyvCardPushInfo> createCardPush(@RequestParam PolyvChannelCardPushCreateParameter param);

    /**
     * 修改频道卡片推送
     * https://help.polyv.net/index.html#/live/api/v4/channel/market/cardPush/update
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v4/channel/card-push/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<Object> _updateCardPush(@RequestParam PolyvChannelCardPushUpdateParameter param, @RequestBody PolyvChannelCardPushUpdateParameter.PolyvChannelCardPushUpdateParameterBody body);

    default PolyvApiResult<Object> updateCardPush(PolyvChannelCardPushUpdateParameter param) {
        PolyvChannelCardPushUpdateParameter.PolyvChannelCardPushUpdateParameterBody body = param.getBody();
        param.setBody(null);
        return _updateCardPush(param, body);
    }

    /**
     * 删除频道卡片推送
     * https://help.polyv.net/index.html#/live/api/v4/channel/market/cardPush/delete
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v4/channel/card-push/delete")
    PolyvApiResult<Object> deleteCardPush(@RequestParam PolyvChannelCardPushDeleteParameter param);

    /**
     * 推送频道卡片
     * https://help.polyv.net/index.html#/live/api/v4/channel/market/cardPush/push
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v4/channel/card-push/push")
    PolyvApiResult<Object> pushCardPush(@RequestParam PolyvChannelCardPushPushParameter param);

    /**
     * 取消推送卡片
     * https://help.polyv.net/index.html#/live/api/v4/channel/market/cardPush/cancelPush
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v4/channel/card-push/cancel-push")
    PolyvApiResult<Object> cancelCardPush(@RequestParam PolyvChannelCardPushCancelParameter param);

    /**
     * 分页获取伪直播信息
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/disk-video/list")
    PolyvApiResult<PolyvPaginator<PolyvDiskVideo>> diskVideoList(@RequestParam PolyvChannelDiskVideoListParameter param);

    /**
     * 获取直播监控信息接口
     * https://dev.polyv.net/2020/uncategorized/get-monitor-info/
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/monitor/get-info")
    PolyvApiResult<PolyvMonitorInfo> getMonitorInfo(@RequestParam PolyvChannelNoneParamParameter param);

}

