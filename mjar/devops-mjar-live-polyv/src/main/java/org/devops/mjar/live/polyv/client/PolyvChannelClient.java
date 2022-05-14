package org.devops.mjar.live.polyv.client;

import org.devops.mjar.live.core.processor.Processor;
import org.devops.mjar.live.polyv.feign.client.PolyvChannelFeignClient;
import org.devops.mjar.live.polyv.operator.requester.*;
import org.devops.mjar.live.polyv.service.MjarLivePolyvBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author GENSEN
 * @date 2020/10/26 16:35
 * @description：
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Scope("prototype")
public class PolyvChannelClient extends Processor {

    PolyvChannelClient() {
        super();
    }

    @Autowired
    private PolyvChannelFeignClient channelFeignClient;

    @Autowired
    private MjarLivePolyvBaseService mjarLivePolyvBaseService;


    /**
     * 获取账号下回放视频
     *
     * @return
     */
    public PolyvChannelPlayBackSpecialListRequester playBackSpecialListRequest() {
        return new PolyvChannelPlayBackSpecialListRequester(channelFeignClient::getPlayBackSpecialList);
    }

    /**
     * 分页获取直播暂存列表
     *
     * @return
     */
    public PolyvChannelRecordListRequester recordListRequest() {
        return new PolyvChannelRecordListRequester(channelFeignClient::getRecordList);
    }

    /**
     * 查询频道录制视频信息
     *
     * @return
     */
    public PolyvChannelRecordFilesRequester recordFilesRequest() {
        return new PolyvChannelRecordFilesRequester(channelFeignClient::getRecordFiles);
    }

    /**
     * 根据fileId查询录制视频信息
     *
     * @return
     */
    public PolyvChannelRecordDetailRequester recordDetailRequest() {
        return new PolyvChannelRecordDetailRequester(channelFeignClient::getRecordDetail);
    }

    /**
     * 创建重置课件任务
     *
     * @return
     */
    public PolyvChannelAddRecordTaskRequester addRecordTaskRequest() {
        return new PolyvChannelAddRecordTaskRequester(channelFeignClient::addRecordTask);
    }

    /**
     * 批量转存录制视频到云点播
     *
     * @return
     */
    public PolyvChannelRecordConvertRequester recordConvertRequest() {
        return new PolyvChannelRecordConvertRequester(channelFeignClient::recordConvert);
    }

    /**
     * 异步合并直播录制文件
     *
     * @return
     */
    public PolyvChannelRecordMergeRequester recordMergeRequest() {
        return new PolyvChannelRecordMergeRequester(channelFeignClient::recordMerge);
    }

    /**
     * 合并直播录制文件并回调mp4下载地址
     *
     * @return
     */
    public PolyvChannelRecordMergeMp4Requester recordMergeMp4Request() {
        return new PolyvChannelRecordMergeMp4Requester(channelFeignClient::recordMergeMp4);
    }

    /**
     * 删除直播暂存的录制文件
     *
     * @return
     */
    public PolyvChannelRecordDeleteRequester recordDeleteRequest() {
        return new PolyvChannelRecordDeleteRequester(channelFeignClient::recordDelete);
    }

    /**
     * 删除视频库中视频
     *
     * @return
     */
    public PolyvChannelPlayBackDeleteRequester playBackDeleteRequest() {
        return new PolyvChannelPlayBackDeleteRequester(channelFeignClient::playBackDelete);
    }

    /**
     * 获取可添加到回放列表的视频列表
     *
     * @return
     */
    public PolyvChannelPlayBackVideoPoolsRequester playBackVideoPoolsRequest() {
        return new PolyvChannelPlayBackVideoPoolsRequester(channelFeignClient::getVideoPools);
    }

    /**
     * 批量删除暂存列表视频
     *
     * @return
     */
    public PolyvChannelRecordBatchDeleteRequester recordBatchDeleteRequest() {
        return new PolyvChannelRecordBatchDeleteRequester(channelFeignClient::recordDeleteBatch);
    }

    /**
     * 批量删除回放列表的回放视频
     *
     * @return
     */
    public PolyvChannelPlayBackBatchDeleteRequester playBackBatchDeleteRequest() {
        return new PolyvChannelPlayBackBatchDeleteRequester(channelFeignClient::playBackDeleteBatch);
    }

    /**
     * 修改视频库的视频排序
     *
     * @return
     */
    public PolyvChannelPlayBackSortRequester playBackSortRequest() {
        return new PolyvChannelPlayBackSortRequester(channelFeignClient::playBackSort);
    }

    /**
     * 回放列表视频单个排序
     *
     * @return
     */
    public PolyvChannelPlayBackSingleSortRequester playBackSingleSortRequest() {
        return new PolyvChannelPlayBackSingleSortRequester(channelFeignClient::playBackSingleSort);
    }

    /**
     * 将点播视频添加到视频库
     *
     * @return
     */
    public PolyvChannelPlayBackAddVideosRequester playBackAddVideosRequest() {
        return new PolyvChannelPlayBackAddVideosRequester(channelFeignClient::playBackAddVideos);
    }

    /**
     * 批量添加点播视频到回放列表
     *
     * @return
     */
    public PolyvChannelPlayBackBatchAddRequester playBackBatchAddVideosRequest() {
        return new PolyvChannelPlayBackBatchAddRequester(channelFeignClient::playBackAddBatchAddVideos);
    }

    /**
     * 独立授权观看地址
     *
     * @return
     */
    public PolyvChannelDirectPlayUrlRequester directPlayUrlRequest() {
        return new PolyvChannelDirectPlayUrlRequester(mjarLivePolyvBaseService::getDirectPlayUrl);
    }

    /**
     * 频道详情
     *
     * @return
     */
    public PolyvChannelDetailRequester detailRequest() {
        return new PolyvChannelDetailRequester(channelFeignClient::getDetail);
    }

    /**
     * 子频道信息
     *
     * @return
     */
    public PolyvChannelAccountDetailRequester accountDetailRequest() {
        return new PolyvChannelAccountDetailRequester(channelFeignClient::getAccountDetail);
    }

    /**
     * 所有子频道信息
     *
     * @return
     */
    public PolyvChannelAccountListRequester accountListRequest() {
        return new PolyvChannelAccountListRequester(channelFeignClient::getAccountList);
    }

    /**
     * 子频道创建
     *
     * @return
     */
    public PolyvChannelCreateAccountRequester accountCreateRequest() {
        return new PolyvChannelCreateAccountRequester(channelFeignClient::addAccount);
    }

    /**
     * 分页查询观看日志
     *
     * @return
     */
    public PolyvChannelViewLogListRequester viewLogListRequest() {
        return new PolyvChannelViewLogListRequester(channelFeignClient::getViewLogList);
    }

    /**
     * 连麦日志查询
     *
     * @return
     */
    public PolyvChannelMicLogListRequester micLogListRequest() {
        return new PolyvChannelMicLogListRequester(channelFeignClient::getMicLogList);
    }

    /**
     * 频道信息更新请求
     *
     * @return
     */
    public PolyvChannelUpdateRequester updateRequest() {
        return new PolyvChannelUpdateRequester(channelFeignClient::update);
    }

    /**
     * 频道信息更新请求 v3
     *
     * @return
     */
    public PolyvChannelUpdateV3Requester updateV3Request() {
        return new PolyvChannelUpdateV3Requester(channelFeignClient::updateV3);
    }

    /**
     * 频道回放设置
     *
     * @return
     */
    public PolyvChannelUpdatePlayBackEnabledRequester updatePlayBackEnabledRequest() {
        return new PolyvChannelUpdatePlayBackEnabledRequester(channelFeignClient::updatePlayBackSetting);
    }

    /**
     * 讲师更新
     *
     * @return
     */
    public PolyvChannelUpdateTeacherRequester updateTeacherRequest() {
        return new PolyvChannelUpdateTeacherRequester(channelFeignClient::updateTeacher);
    }

    /**
     * 问卷列表
     *
     * @return
     */
    public PolyvChannelQuestionnaireListRequester questionnaireListRequest() {
        return new PolyvChannelQuestionnaireListRequester(channelFeignClient::getQuestionnaireList);
    }


    /**
     * 问卷导出
     *
     * @return
     */
    public PolyvChannelExportQuestionnaireRequester exportQuestionnaireRequest() {
        return new PolyvChannelExportQuestionnaireRequester(channelFeignClient::exportQuestionnaire);
    }

    /**
     * 问卷详情
     *
     * @return
     */
    public PolyvChannelQuestionnaireDetailRequester questionnaireDetailRequest() {
        return new PolyvChannelQuestionnaireDetailRequester(channelFeignClient::getQuestionnaireDetail);
    }

    /**
     * 问卷统计
     *
     * @return
     */
    public PolyvChannelQuestionnaireStatsRequester questionnaireStatsRequest() {
        return new PolyvChannelQuestionnaireStatsRequester(channelFeignClient::getQuestionnaireStats);
    }

    /**
     * 查询视频库列表
     *
     * @return
     */
    public PolyvChannelPlayBackListRequester playBackListRequest() {
        return new PolyvChannelPlayBackListRequester(channelFeignClient::getPlayBackList);
    }

    /**
     * 直播场次列表
     *
     * @return
     */
    public PolyvChannelSessionListRequester sessionListRequest() {
        return new PolyvChannelSessionListRequester(channelFeignClient::getSessionList);
    }

    /**
     * 子频道更新
     *
     * @return
     */
    public PolyvChannelUpdateAccountRequester updateAccountRequest() {
        return new PolyvChannelUpdateAccountRequester(channelFeignClient::updateAccount);
    }

    /**
     * 子频道删除
     *
     * @return
     */
    public PolyvChannelDeleteAccountRequester deleteAccountRequest() {
        return new PolyvChannelDeleteAccountRequester(channelFeignClient::deleteAccount);
    }

    /**
     * 获取频道授权和连麦的token
     *
     * @return
     */
    public PolyvChannelChatTokenRequester chatTokenRequest() {
        return new PolyvChannelChatTokenRequester(channelFeignClient::getChatToken);
    }

    /**
     * 查询频道实时推流信息
     *
     * @return
     */
    public PolyvChannelStreamInfoRequester streamInfoRequest() {
        return new PolyvChannelStreamInfoRequester(channelFeignClient::getStreamInfo);
    }

    /**
     * 恢复推流
     *
     * @return
     */
    public PolyvChannelResumeStreamRequester streamResumeRequest() {
        return new PolyvChannelResumeStreamRequester(channelFeignClient::resumeStream);
    }

    /**
     * 停止推流
     *
     * @return
     */
    public PolyvChannelCutoffStreamRequester streamCutoffRequest() {
        return new PolyvChannelCutoffStreamRequester(channelFeignClient::cutoffStream);
    }

    /**
     * 修改频道状态为无直播
     *
     * @return
     */
    public PolyvChannelEndStreamRequester streamEndRequest() {
        return new PolyvChannelEndStreamRequester(channelFeignClient::endStream);
    }

    /**
     * 修改频道状态为直播中
     *
     * @return
     */
    public PolyvChannelLiveStreamRequester streamLiveRequest() {
        return new PolyvChannelLiveStreamRequester(channelFeignClient::liveStream);
    }

    /**
     * 获取回放开关
     *
     * @return
     */
    public PolyvChannelPlayBackEnableRequester playBackEnableRequest() {
        return new PolyvChannelPlayBackEnableRequester(channelFeignClient::getPlayBackEnabled);
    }

    /**
     * 设置频道是否应用通用设置开关
     *
     * @return
     */
    public PolyvChannelUpdateGlobalEnabledRequester updateGlobalEnabledRequest() {
        return new PolyvChannelUpdateGlobalEnabledRequester(channelFeignClient::updateGlobalEnabled);
    }

    /**
     * 设置频道单点登录token参数
     *
     * @return
     */
    public PolyvChannelUpdateTokenRequester updateTokenRequest() {
        return new PolyvChannelUpdateTokenRequester(channelFeignClient::setToken);
    }

    /**
     * 频道后台登录
     *
     * @return
     */
    public PolyvChannelLoginRequester channelLoginRequest() {
        return new PolyvChannelLoginRequester(mjarLivePolyvBaseService::getChannelLoginUrl);
    }


    /**
     * 设置子频道单点登录token参数
     *
     * @return
     */
    public PolyvChannelUpdateAccountTokenRequester updateAccountTokenRequest() {
        return new PolyvChannelUpdateAccountTokenRequester(channelFeignClient::setAccountToken);
    }

    /**
     * 子频道后台登录
     *
     * @return
     */
    public PolyvChannelAccountLoginRequester accountLoginRequest() {
        return new PolyvChannelAccountLoginRequester(mjarLivePolyvBaseService::getAccountLoginUrl);
    }

    /**
     * 查询独立授权secreKey
     *
     * @return
     */
    public PolyvChannelSecretKeyRequester secretKeyRequest() {
        return new PolyvChannelSecretKeyRequester(channelFeignClient::getSecretKey);
    }

    /**
     * 查询频道发起的签到记录
     *
     * @return
     */
    public PolyvChannelCheckinListRequester checkinListRequest() {
        return new PolyvChannelCheckinListRequester(channelFeignClient::listCheckinBySessionId);
    }

    /**
     * 获取嘉宾链接
     *
     * @return
     */
    public PolyvChannelGuestStartUrlRequester guestStartUrlRequest() {
        return new PolyvChannelGuestStartUrlRequester(mjarLivePolyvBaseService::getGuestPlayUrl);
    }

    /**
     * 获取助教链接
     *
     * @return
     */
    public PolyvChannelGuestStartUrlRequester assistantStartUrlRequest() {
        return new PolyvChannelGuestStartUrlRequester(mjarLivePolyvBaseService::getAssistantPlayUrl);
    }

    /**
     * 获取主持人开播链接
     *
     * @return
     */
    public PolyvChannelHostStartUrlRequester hostStartUrlRequest() {
        return new PolyvChannelHostStartUrlRequester(mjarLivePolyvBaseService::getHostStartUrl);
    }

    /**
     * 根据发起签到记录查询签到结果
     *
     * @return
     */
    public PolyvChannelCheckinDetailRequester checkinDetailRequest() {
        return new PolyvChannelCheckinDetailRequester(channelFeignClient::listCheckinDetail);
    }

    /**
     * 查询多个频道实时并发数据
     *
     * @return
     */
    public PolyvChannelsRealtimeViewerListRequester realtimeDataListRequest() {
        return new PolyvChannelsRealtimeViewerListRequester(channelFeignClient::getRealtimeViewer);
    }

    /**
     * 查询频道聊天记录
     *
     * @return
     */
    public PolyvChannelChatHistoryRequester chatHistoryRequest() {
        return new PolyvChannelChatHistoryRequester(channelFeignClient::getChatHistory);
    }


    /**
     * 获取讲师客户端链接
     *
     * @return
     */
    public PolyvChannelHostClientUrlRequester hostClientStartUrlRequest() {
        return new PolyvChannelHostClientUrlRequester(mjarLivePolyvBaseService::getClientStartUrl);
    }

    /**
     * 获取嘉宾客户端链接
     *
     * @return
     */
    public PolyvChannelGuestClientUrlRequester guestClientStartUrlRequest() {
        return new PolyvChannelGuestClientUrlRequester(mjarLivePolyvBaseService::getClientStartUrl);
    }

    /**
     * 查询重制课件任务列表
     * http://api.polyv.net/live/v3/channel/pptRecord/list
     *
     * @return
     */
    public PolyvChannelPptRecordListRequester polyvChannelPptRecordListRequest() {
        return new PolyvChannelPptRecordListRequester(channelFeignClient::getPptRecordList);
    }

    /**
     * 查询频道实时并发数据
     * http://api.polyv.net/live/v1/statistics/{channelId}/realtime
     *
     * @return
     */
    public PolyvChannelStaticRealtimeRequester polyvChannelStaticRealtimeRequest() {
        return new PolyvChannelStaticRealtimeRequester(channelFeignClient::getStaticistRealtime);
    }

    /**
     * 查询频道多场次概览统计数据
     * https://help.polyv.net/index.html#/live/api/channel/viewdata/get_session_stats
     *
     * @return
     */
    public PolyvChannelStaticistGetSessionStstsRequester polyvChannelStaticistGetSessionStstsRequest() {
        return new PolyvChannelStaticistGetSessionStstsRequester(channelFeignClient::getStaticistGetSessionStats);
    }

    /**
     * 查询频道历史并发数据
     * https://help.polyv.net/index.html#/live/api/channel/viewdata/concurrency
     *
     * @return
     */
    public PolyvChannelStatisticsConcurrenceRequester polyvChannelStatisticsConcurrenceRequest() {
        return new PolyvChannelStatisticsConcurrenceRequester(channelFeignClient::getStatisticsConcurrence);
    }

    /**
     * 查询频道历史最高并发数据
     * https://help.polyv.net/index.html#/live/api/channel/viewdata/get_max_history_concurrent
     *
     * @return
     */
    public PolyvChannelStatisticsGetMaxHistoryRequester channelGetMaxHistoryRequest() {
        return new PolyvChannelStatisticsGetMaxHistoryRequester(channelFeignClient::getMaxHistoryConcurrent);
    }

    /**
     * 查询频道直播观看详情数据
     *
     * @return
     */
    public PolyvChannelStatisticsViewLogRequester channelStatisticsViewLogRequest() {
        return new PolyvChannelStatisticsViewLogRequester(channelFeignClient::getChannelViewLog);
    }

    /**
     * 查询频道某段时间的直播观看详情数据
     * https://help.polyv.net/index.html#/live/api/channel/viewdata/summary
     *
     * @return
     */
    public PolyvChannelStatisticsSummaryRequester channelStatisticsSummaryRequest() {
        return new PolyvChannelStatisticsSummaryRequester(channelFeignClient::getStatisticsSummary);
    }

    /**
     * 查询引导图
     *
     * @return
     */
    public PolyvChannelSplashRequester splashRequest() {
        return new PolyvChannelSplashRequester(channelFeignClient::getSplash);
    }

    /**
     * 查询频道直播开始时间
     *
     * @return
     */
    public PolyvChannelCountDownRequester countDownRequest() {
        return new PolyvChannelCountDownRequester(channelFeignClient::getCountDown);
    }

    /**
     * 修改频道名称
     *
     * @return
     */
    public PolyvChannelUpdateNameRequester updateNameRequest() {
        return new PolyvChannelUpdateNameRequester(channelFeignClient::updateChannelName);
    }

    /**
     * 修改频道图标
     *
     * @return
     */
    public PolyvChannelUpdateCoverImgRequester updateCoverImgRequest() {
        return new PolyvChannelUpdateCoverImgRequester(channelFeignClient::updateChannelCoverImg);
    }


    /**
     * 修改引导图
     *
     * @return
     */
    public PolyvChannelUpdateSplashRequester updateSplashRequest() {
        return new PolyvChannelUpdateSplashRequester(channelFeignClient::updateSplash);
    }


    /**
     * 修改频道点赞数和观看次数
     *
     * @return
     */
    public PolyvChannelUpdateLikesRequester updateLikesRequest() {
        return new PolyvChannelUpdateLikesRequester(channelFeignClient::updateLikes);
    }


    /**
     * 修改直播倒计时设置
     *
     * @return
     */
    public PolyvChannelUpdateCountDownRequester updateCountDownRequest() {
        return new PolyvChannelUpdateCountDownRequester(channelFeignClient::updateCountDown);
    }


    /**
     * 修改频道观看人数限制
     *
     * @return
     */
    public PolyvChannelUpdateMaxViewerRequester updateMaxViewerRequest() {
        return new PolyvChannelUpdateMaxViewerRequester(channelFeignClient::updateMaxViewer);
    }

    /**
     * 查询频道提问记录
     * https://help.polyv.net/index.html#/live/api/live_interaction/get_question_list
     *
     * @return
     */
    public PolyvChannelChatGetQuestionRequester chatGetQuestionRequest() {
        return new PolyvChannelChatGetQuestionRequester(channelFeignClient::getChannelQuestionRecord);
    }

    /**
     * 查询频道登记观看记录
     * https://help.polyv.net/index.html#/live/api/web/watch_condition/get_record_info
     *
     * @return
     */
    public PolyvChannelAuthGetRecordInfoRequester authGetRecordInfoRequest() {
        return new PolyvChannelAuthGetRecordInfoRequester(channelFeignClient::getChannelAuthRecordInfo);
    }

    /**
     * 查询频道中奖记录
     * https://help.polyv.net/index.html#/live/api/live_interaction/list_lottery
     *
     * @return
     */
    public PolyvChannelLotteryListLotteryRequester listLotteryRequest() {
        return new PolyvChannelLotteryListLotteryRequester(channelFeignClient::getChannelListLottery);
    }


    /**
     * 修改频道推流方式
     *
     * @return
     */
    public PolyvChannelUpdateStreamRequester updateStreamRequest() {
        return new PolyvChannelUpdateStreamRequester(channelFeignClient::updateStream);
    }

    /**
     * 查询频道场次中奖记录
     * https://help.polyv.net/index.html#/live/api/live_interaction/get_winner_detail
     *
     * @return
     */
    public PolyvChannelLotteryGetWinDetailRequester getLotteryWinDetailRequest() {
        return new PolyvChannelLotteryGetWinDetailRequester(channelFeignClient::getChannelWinnerDetail);
    }

    /**
     * 查询频道问卷结果
     *
     * @return
     */
    public PolyvChannelQuestionnaireRecordRequester getChannelQuestionnaireResultRequest() {
        return new PolyvChannelQuestionnaireRecordRequester(channelFeignClient::getChannelQuestionnaireResult);
    }

    /**
     * 分页查询频道问卷结果
     *
     * @return
     */
    public PolyvChannelQuestionnaireRecordListRequester getChannelQuestionaireResultListRequest() {
        return new PolyvChannelQuestionnaireRecordListRequester(channelFeignClient::getChannelQuestionaireResultList);
    }

    /**
     * 查询频道签到结果
     *
     * @return
     */
    public PolyvChannelCheckinListPageRequester getChannelCheckinListRequest() {
        return new PolyvChannelCheckinListPageRequester(channelFeignClient::getChannelCheckinList);
    }

    /**
     * 下载频道登记观看记录
     */
    public PolyvChannelAuthDownloadRequester getAuthDownloadRequest() {
        return new PolyvChannelAuthDownloadRequester(channelFeignClient::getAuthDownload);
    }

    /**
     * 下载频道场次中奖记录
     */
    public PolyvChannelLotteryDetailDownloadRequester getLotteryDetailDownloadRequest() {
        return new PolyvChannelLotteryDetailDownloadRequester(channelFeignClient::getLotteryDeatilDownLoad);
    }

    /**
     * 修改视频库的默认视频
     *
     * @return
     */
    public PolyvChannelPlayBackSetDefaultRequester playBackSetDefaultRequest() {
        return new PolyvChannelPlayBackSetDefaultRequester(channelFeignClient::playBackSetDefault);
    }

    /**
     * 查询单个子频道信息
     */
    public PolyvChannelSearchSingleAccountRequester searchSingleAccountRequest() {
        return new PolyvChannelSearchSingleAccountRequester(channelFeignClient::getSingleChannelAccount);
    }


    /**
     * 查询频道号下所有子频道信息
     */
    public PolyvChannelSearchAccountRequester searchAccountRequest() {
        return new PolyvChannelSearchAccountRequester(channelFeignClient::getAllChannelAccount);
    }

    /**
     * 获取频道API的访问令牌
     */
    public PolyvChannelCreateTokenRequester createChannelTokenRequest() {
        return new PolyvChannelCreateTokenRequester(channelFeignClient::getChannelApiToken);
    }

    /*
     * 修改暖场设置开关
     */
    public PolyvChannelUpdateWarmUpRequester updateWarmUpRequest() {
        return new PolyvChannelUpdateWarmUpRequester(channelFeignClient::updateWarmUpEnable);
    }

    /*
     * 修改暖场图片
     */
    public PolyvChannelUpdateWarmUpImageRequester updateWarmUpImagesRequest() {
        return new PolyvChannelUpdateWarmUpImageRequester(channelFeignClient::updateWarmUpImage);
    }

    /**
     * 修改回放视频名称
     *
     * @return
     */
    public PolyvChannelPlayBackUpdateTitleRequester playBackUpdateTitleRequest() {
        return new PolyvChannelPlayBackUpdateTitleRequester(channelFeignClient::playBackUpdateTitle);
    }

    /**
     * 裁剪录制文件
     *
     * @return
     */
    public PolyvChannelRecordClipRequester recordClipRequest() {
        return new PolyvChannelRecordClipRequester(channelFeignClient::recordClip);
    }

    /*
     * 修改暖场视频
     */
    public PolyvChannelUpdateWarmUpVideoRequester updateWarmUpVideoRequest() {
        return new PolyvChannelUpdateWarmUpVideoRequester(channelFeignClient::updateWarmUpVideo);
    }

    /*
     * 增加页面菜单
     */
    public PolyvChannelCreateMenuRequester createMenuRequest() {
        return new PolyvChannelCreateMenuRequester(channelFeignClient::createMenu);
    }

    /*
     * 查询频道页面菜单信息
     */
    public PolyvChannelSearchMenuListRequester searchMenuListRequest() {
        return new PolyvChannelSearchMenuListRequester(channelFeignClient::searchMenuList);
    }

    //    查询频道图文直播内容
    public PolyvChannelSearchImageMenuListRequester searchImageMenuListRequest() {
        return new PolyvChannelSearchImageMenuListRequester(channelFeignClient::searchImageMenuList);
    }

    /**
     * 修改提问功能开关
     */
    public PolyvChannelUpdateMenuConsultRequester updateMenuConsultRequest() {
        return new PolyvChannelUpdateMenuConsultRequester(channelFeignClient::updateMenuConsult);
    }

    /**
     * 上传文档到某个频道
     *
     * @return
     */
    public PolyvChannelDocumentUploadRequester documentUploadRequest() {
        return new PolyvChannelDocumentUploadRequester(channelFeignClient::uploadDocument);
    }

    /**
     * 查询频道已上传文档列表
     *
     * @return
     */
    public PolyvChannelDocumentListRequester documentListRequest() {
        return new PolyvChannelDocumentListRequester(channelFeignClient::getDocumentList);
    }

    /**
     * 查询频道文档转码状态
     *
     * @return
     */
    public PolyvChannelDocumentStatusRequester documentStatusRequest() {
        return new PolyvChannelDocumentStatusRequester(channelFeignClient::getDocumentStatus);
    }

    /**
     * 删除文档
     *
     * @return
     */
    public PolyvChannelDocumentDeleteRequester documentDeleteRequest() {
        return new PolyvChannelDocumentDeleteRequester(channelFeignClient::deleteDocument);
    }


    /**
     * 修改页面菜单排序
     */
    public PolyvChannelUpdateMenuRankRequester updateMenuRankRequest() {
        return new PolyvChannelUpdateMenuRankRequester(channelFeignClient::updateMenuRank);
    }

    /**
     * 删除页面菜单内容
     */
    public PolyvChannelDeleteMenuRequester deleteMenuRequest() {
        return new PolyvChannelDeleteMenuRequester(channelFeignClient::deleteMenu);
    }


    /**
     * 修改页面菜单内容
     */
    public PolyvChannelUpdateMenuInfoRequester updateMenuInfoRequest() {
        return new PolyvChannelUpdateMenuInfoRequester(channelFeignClient::updateMenuInfo);
    }

    /**
     * 查询频道直播截图
     */
    public PolyvChannelSearchCaptureRequester searchCaptureRequest() {
        return new PolyvChannelSearchCaptureRequester(channelFeignClient::searchCapture);
    }

    /**
     * 设置伪直播
     */
    public PolyvChannelCreateDiskVideoRequester createDiskVideoRequest() {
        return new PolyvChannelCreateDiskVideoRequester(channelFeignClient::createDisk);
    }

    /**
     * 删除伪直播
     */
    public PolyvChannelDeleteDiskVideoRequester deleteDiskVideoRequest() {
        return new PolyvChannelDeleteDiskVideoRequester(channelFeignClient::deleteDisk);
    }

    /**
     * 查询转播频道信息
     */
    public PolyvChannelSearchTransmitRequester searchTransmitRequest() {
        return new PolyvChannelSearchTransmitRequester(channelFeignClient::searchTransmit);
    }

    /**
     * 跑马灯设置
     */
    public PolyvChannelCreateMarqueeRequester createMarqueeRequest() {
        return new PolyvChannelCreateMarqueeRequester(channelFeignClient::setMarquee);
    }

    /**
     * 查询频道商品库开关状态
     */
    public PolyvChannelCheckProductEnableRequester checkProductEnableRequest() {
        return new PolyvChannelCheckProductEnableRequester(channelFeignClient::checkEnable);
    }

    /**
     * 查询频道商品列表
     */
    public PolyvChannelSearchProductListRequester searchProductListRequest() {
        return new PolyvChannelSearchProductListRequester(channelFeignClient::searchProductList);
    }

    /**
     * 推送频道商品库商品
     */
    public PolyvChannelPushProductRequester pushProductRequest() {
        return new PolyvChannelPushProductRequester(channelFeignClient::pushProduct);
    }


    /**
     * 修改频道商品库开关状态
     */
    public PolyvChannelUpdateProductEnabledRequester updateProductEnabledRequest() {
        return new PolyvChannelUpdateProductEnabledRequester(channelFeignClient::updateProductEnabled);
    }

    /**
     * 修改频道商品库开关状态
     */
    public PolyvChannelUpdateProductShelfRequester updateProductShelfRequest() {
        return new PolyvChannelUpdateProductShelfRequester(channelFeignClient::updateProductShelf);
    }

    /**
     * 修改频道商品库开关状态
     */
    public PolyvChannelUpdateProductSortRequester updateProductSortRequest() {
        return new PolyvChannelUpdateProductSortRequester(channelFeignClient::updateProductSort);
    }

    /**
     * 删除频道商品
     */
    public PolyvChannelDeleteProductRequester deleteProductRequest() {
        return new PolyvChannelDeleteProductRequester(channelFeignClient::deleteProduct);
    }

    /**
     * 发送点赞
     *
     * @return
     */
    public PolyvChannelSentFavorRequester sentFavorRequest() {
        return new PolyvChannelSentFavorRequester(channelFeignClient::sentFavor);
    }

    /**
     * 禁言/解禁用户
     *
     * @return
     */
    public PolyvChannelUpdateBannedUserRequester updateBannedUserRequest() {
        return new PolyvChannelUpdateBannedUserRequester(channelFeignClient::updateBannedUser);
    }

    /**
     * 禁言ip
     *
     * @return
     */
    public PolyvChannelAddBannedIpRequester addBannedIpRequest() {
        return new PolyvChannelAddBannedIpRequester(channelFeignClient::addBannedIp);
    }

    /**
     * 查询频道禁言用户Userid/IP
     *
     * @return
     */
    public PolyvChannelBannedListRequester bannedListRequest() {
        return new PolyvChannelBannedListRequester(channelFeignClient::getBannedList);
    }

    /**
     * 查询频道严禁词/禁言ip
     *
     * @return
     */
    public PolyvChannelBadWordsListRequester badWordsListRequest() {
        return new PolyvChannelBadWordsListRequester(channelFeignClient::getBadWordsList);
    }

    /**
     * 删除频道严禁词/禁言ip
     *
     * @return
     */
    public PolyvChannelBadWordDeleteRequester badWordDeleteRequest() {
        return new PolyvChannelBadWordDeleteRequester(channelFeignClient::deleteBadWord);
    }

    /**
     * 批量删除频道多条聊天记录
     *
     * @return
     */
    public PolyvChannelChatBatchDeleteRequester chatBatchDeleteRequest() {
        return new PolyvChannelChatBatchDeleteRequester(channelFeignClient::chatDeleteBatch);
    }

    /**
     * 删除频道单条聊天记录
     *
     * @return
     */
    public PolyvChannelChatDeleteRequester chatDeleteRequest() {
        return new PolyvChannelChatDeleteRequester(channelFeignClient::chatDelete);
    }

    /**
     * 清空频道聊天记录
     *
     * @return
     */
    public PolyvChannelChatCleanRequester chatCleanRequest() {
        return new PolyvChannelChatCleanRequester(channelFeignClient::chatClean);
    }

    /**
     * 查询频道踢人列表
     *
     * @return
     */
    public PolyvChannelKickedListRequester kickedListRequest() {
        return new PolyvChannelKickedListRequester(channelFeignClient::getKickedList);
    }

    /**
     * 修改管理员身份信息
     *
     * @return
     */
    public PolyvChannelUpdateAdminInfoRequester updateAdminInfoRequest() {
        return new PolyvChannelUpdateAdminInfoRequester(channelFeignClient::updateAdminInfo);
    }

    /**
     * 查询管理员身份信息
     *
     * @return
     */
    public PolyvChannelAdminInfoRequester adminInfoRequest() {
        return new PolyvChannelAdminInfoRequester(channelFeignClient::getAdminInfo);
    }

    /**
     * 发送图文信息
     *
     * @return
     */
    public PolyvChannelSentMessageRequester sentMessageRequest() {
        return new PolyvChannelSentMessageRequester(channelFeignClient::sentMessage);
    }

    /**
     * 提交中奖者信息
     *
     * @return
     */
    public PolyvChannelAddReceiveInfoRequester addReceiveInfoRequest() {
        return new PolyvChannelAddReceiveInfoRequester(channelFeignClient::addReceiveInfo);
    }

    /**
     * 新增或修改频道问卷
     *
     * @return
     */
    public PolyvChannelQuestionnaireAddRequester questionnaireAddRequest() {
        return new PolyvChannelQuestionnaireAddRequester(channelFeignClient::addQuestionnaire);
    }

    /**
     * 添加频道商品
     */
    public PolyvChannelCreateProductRequester createProductRequest() {
        return new PolyvChannelCreateProductRequester(channelFeignClient::createPorduct);
    }

    /**
     * 修改频道商品
     */
    public PolyvChannelUpdateProductRequester updateProductRequest() {
        return new PolyvChannelUpdateProductRequester(channelFeignClient::updatePorduct);
    }

    /**
     * 修改设置授权认证URL
     *
     * @return
     */
    public PolyvChannelUpdateAuthUrlRequester updateAuthUrlRequest() {
        return new PolyvChannelUpdateAuthUrlRequester(channelFeignClient::updateAuthUrl);
    }

    /**
     * 查询频道观看条件
     */
    public PolyvChannelSearchViewConditionRequester searchViewConditionRequest() {
        return new PolyvChannelSearchViewConditionRequester(channelFeignClient::getViewCondition);
    }

    /**
     * 修改频道观看条件
     */
    public PolyvChannelUpdateViewConditionRequester updateViewConditionRequest() {
        return new PolyvChannelUpdateViewConditionRequester(channelFeignClient::updateViewCondition);
    }

    /**
     * 修改频道外部授权设置
     */
    public PolyvChannelUpdateAuthExternalRequester updateAuthExternalRequest() {
        return new PolyvChannelUpdateAuthExternalRequester(channelFeignClient::updateAuthExternal);
    }

    /**
     * 修改频道自定义授权设置
     */
    public PolyvChannelUpdateOauthCustomRequester updateOauthCustomRequest() {
        return new PolyvChannelUpdateOauthCustomRequester(channelFeignClient::updateOauthCustom);
    }

    /**
     * 查询频道白名单
     */
    public PolyvChannelSearchWhiteLstRequester searchWhiteLstRequest() {
        return new PolyvChannelSearchWhiteLstRequester(channelFeignClient::getWhiteList);
    }

    /**
     * 增加频道单个白名单
     */
    public PolyvChannelAddWhiteListRequester addWhiteListRequest() {
        return new PolyvChannelAddWhiteListRequester(channelFeignClient::addWhiteList);
    }

    /**
     * 批量导入频道白名单
     */
    public PolyvChannelBatchUploadWhiteListRequester uploadWhiteListRequest() {
        return new PolyvChannelBatchUploadWhiteListRequester(channelFeignClient::uploadWhiteList);
    }

    /**
     * 修改频道单个白名单
     */
    public PolyvChannelUpdateWhiteListRequester updateWhiteListRequest() {
        return new PolyvChannelUpdateWhiteListRequester(channelFeignClient::updateWhiteList);
    }

    /**
     * 删除频道单个白名单
     */
    public PolyvChannelDeleteWhiteListRequester deleteWhiteListRequest() {
        return new PolyvChannelDeleteWhiteListRequester(channelFeignClient::deleteWhiteList);
    }

    /**
     * 查询频道登记观看设置的字段信息
     */
    public PolyvChannelSearchRecordFieldRequester searchRecordFieldRequest() {
        return new PolyvChannelSearchRecordFieldRequester(channelFeignClient::searchFields);
    }

    /**
     * 下载频道白名单
     */
    public PolyvChannelDownLoadWhiteListRequester downLoadWhiteListRequest() {
        return new PolyvChannelDownLoadWhiteListRequester(channelFeignClient::downLoadWhiteList);
    }

    /**
     * 批量创建子频道
     */
    public PolyvChannelBatchCreateAccountRequester batchCreateAccountRequest() {
        return new PolyvChannelBatchCreateAccountRequester(channelFeignClient::batchCreateAccount);
    }

    /**
     * 修改直播介绍菜单
     */
    public PolyvChannelUpdateIntroductMenuRequester updateIntroductMenuRequest() {
        return new PolyvChannelUpdateIntroductMenuRequester(mjarLivePolyvBaseService::updateIntroductMenu);
    }

    /**
     * 查询频道广告列表
     *
     * @return
     */
    public PolyvChannelSearchAdvertListRequester searchAdvertListRequest() {
        return new PolyvChannelSearchAdvertListRequester(channelFeignClient::searchAdvertList);
    }


    /**
     * 修改频道播放器片头广告
     */
    public PolyvChannelUpdateHeadAdvertRequester updateHeadAdvertRequest() {
        return new PolyvChannelUpdateHeadAdvertRequester(channelFeignClient::updatePolyvAdverties);
    }

    /**
     * 修改频道播放器暂停广告
     *
     * @return
     */
    public PolyvChannelUpdateStopAdvertRequester updateStopAdvertRequest() {
        return new PolyvChannelUpdateStopAdvertRequester(channelFeignClient::updatePolyvStopAdverties);
    }

    /**
     * 查询频道微信分享信息
     */
    public PolyvChannelSearchWeChatShareRequester searchWeChatShareRequest() {
        return new PolyvChannelSearchWeChatShareRequester(channelFeignClient::searchWechatSearch);
    }

    /**
     * 修改频道微信分享信息
     *
     * @return
     */
    public PolyvChannelUpdateWeChatShareRequester updateWeChatShareRequest() {
        return new PolyvChannelUpdateWeChatShareRequester(channelFeignClient::updateWeChatShare);
    }


    /**
     * 设置现金打赏
     *
     * @return
     */
    public PolyvChannelUpdateDonateCashRequester updateDonateCashRequest() {
        return new PolyvChannelUpdateDonateCashRequester(channelFeignClient::updateDonateCash);
    }

    /**
     * 设置道具打赏
     *
     * @return
     */
    public PolyvChannelUpdateDonateGoodRequester updateDonateGoodRequest() {
        return new PolyvChannelUpdateDonateGoodRequester(channelFeignClient::updateDonateGood);
    }

    /**
     * 发送打赏消息
     *
     * @return
     */
    public PolyvChannelSentRewardMsgRequester sentRewardMsgRequest() {
        return new PolyvChannelSentRewardMsgRequester(channelFeignClient::sentRewardMsg);
    }

    /**
     * 修改频道的功能开关状态
     *
     * @return
     */
    public PolyvChannelUpdateSwitchRequester updateSwitchRequest() {
        return new PolyvChannelUpdateSwitchRequester(channelFeignClient::updateSwitch);
    }

    /**
     * 查询频道的功能开关状态
     */
    public PolyvChannelSearchSwitchRequester searchSwitchRequest() {
        return new PolyvChannelSearchSwitchRequester(channelFeignClient::searchSwitch);
    }

    /**
     * 查询频道信息
     */
    public PolyvChannelSearchRequester searchRequest() {
        return new PolyvChannelSearchRequester(channelFeignClient::getPolyvChannel);
    }

    /**
     * 修改频道播放器中显示的logo
     */
    public PolyvChannelLogoRequester updateLogoRequest() {
        return new PolyvChannelLogoRequester(channelFeignClient::updateLogo);
    }

    /**
     * 分页查询频道直播观看详情数据
     */
    public PolyvChannelSearchViewLogV2Requester searchViewLogViewRequest() {
        return new PolyvChannelSearchViewLogV2Requester(channelFeignClient::getViewLogV2);
    }

    /**
     * 查询频道答题卡结果
     */
    public PolyvChannelSearchQuestionaireResultRequester searchQuestionaireResultRequest() {
        return new PolyvChannelSearchQuestionaireResultRequester(channelFeignClient::getQuestionResult);
    }

    /**
     * 删除重制课件
     */
    public PolyvChannelDeletePptRecordRequester deletePptRecordRequest() {
        return new PolyvChannelDeletePptRecordRequester(channelFeignClient::deletePptRecord);
    }

    /**
     * 获取聊天室在线列表
     */
    public PolyvChannelChatOnLineListRequester chatOnLineListRequest() {
        return new PolyvChannelChatOnLineListRequester(mjarLivePolyvBaseService::getChatOnLineList);
    }

    /**
     * 查询频道重制课件设置
     */
    public PolyvChannelSearchPptRecordSettingRequester searchPptRecordSettingRequest(){
        return new PolyvChannelSearchPptRecordSettingRequester(channelFeignClient::searchPptRecordSetting);
    }

    /**
     * 修改重制课件设置
     * @return
     */
    public PolyvChannelUpdatePptRecordSettingRequester updatePptRecordSettingRequest(){
        return new PolyvChannelUpdatePptRecordSettingRequester(channelFeignClient::updatePptRecordSetting);
    }

    /**
     * 观看页引导设置
     * 需要和 getDecorateV4Request 接口配对使用，从get接口获取数据对象，更新其中的字段后再将数据对象作为参数请求 updateDecorateV4Request
     * @return
     */
    public PolyvChannelDecorateUpdateV4Requester updateDecorateV4Request(){
        return new PolyvChannelDecorateUpdateV4Requester(channelFeignClient::updateDecorate);
    }

    /**
     * 查询频道页面装修(新版)
     * http://api.polyv.net/live/v4/channel/decorate/get
     */
    public PolyvChannelGetDecorateV4Requester getDecorateV4Request(){
        return new PolyvChannelGetDecorateV4Requester(channelFeignClient::getDecorate);
    }

    /**
     * 查询频道卡片推送
     * @return
     */
    public PolyvChannelCardPushListRequester cardPushListRequest() {
        return new PolyvChannelCardPushListRequester(channelFeignClient::cardPushList);
    }

    /**
     * 创建频道卡片推送
     * @return
     */
    public PolyvChannelCardPushCreateRequester createCardPushRequest() {
        return new PolyvChannelCardPushCreateRequester(channelFeignClient::createCardPush);
    }

    /**
     * 修改频道卡片推送
     * @return
     */
    public PolyvChannelCardPushUpdateRequester updateCardPushRequest() {
        return new PolyvChannelCardPushUpdateRequester(channelFeignClient::updateCardPush);
    }

    /**
     * 删除频道卡片推送
     * @return
     */
    public PolyvChannelCardPushDeleteRequester deleteCardPushRequest() {
        return new PolyvChannelCardPushDeleteRequester(channelFeignClient::deleteCardPush);
    }

    /**
     * 推送频道卡片
     * @return
     */
    public PolyvChannelCardPushPushRequester pushCardPushRequest() {
        return new PolyvChannelCardPushPushRequester(channelFeignClient::pushCardPush);
    }

    /**
     * 取消推送卡片
     * @return
     */
    public PolyvChannelCardPushCancelRequester cancelCardPushRequest() {
        return new PolyvChannelCardPushCancelRequester(channelFeignClient::cancelCardPush);
    }

    /**
     * 分页获取伪直播信息
     * @return
     */
    public PolyvChannelDiskVideoListRequester diskVideoListRequest() {
        return new PolyvChannelDiskVideoListRequester(channelFeignClient::diskVideoList);
    }

    /**
     * 获取直播监控信息接口
     * @return
     */
    public PolyvChannelGetMonitorInfoRequester getMonitorInfoRequest() {
        return new PolyvChannelGetMonitorInfoRequester(channelFeignClient::getMonitorInfo);
    }
}
