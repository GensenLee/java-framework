package org.devops.mjar.live.polyv.client;

import org.devops.mjar.live.core.processor.Processor;
import org.devops.mjar.live.polyv.feign.client.PolyvAppFeignClient;
import org.devops.mjar.live.polyv.feign.client.PolyvChannelFeignClient;
import org.devops.mjar.live.polyv.feign.client.PolyvFileFeignClient;
import org.devops.mjar.live.polyv.operator.requester.*;
import org.devops.mjar.live.polyv.service.MjarLivePolyvBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 所有方法名必须以Request为后缀
 *
 * @author GENSEN
 * @date 2020/10/26 16:35
 * @description：
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Scope("prototype")
public class PolyvAppClient extends Processor {

    PolyvAppClient() {
        super();
    }

    @Autowired
    private PolyvChannelFeignClient channelFeignClient;

    @Autowired
    private PolyvFileFeignClient fileFeignClient;

    @Autowired
    private PolyvAppFeignClient appFeignClient;

    @Autowired
    private MjarLivePolyvBaseService mjarLivePolyvBaseService;

    /**
     * 图片上传
     *
     * @return
     */
    public PolyvAppUploadImageFileRequester uploadImageRequest() {
        return new PolyvAppUploadImageFileRequester(fileFeignClient::uploadImage);
    }

    /**
     * 频道创建
     * https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/pdcz/create-channel/
     *
     * @return
     */
    public PolyvChannelCreateRequester createChannelRequest() {
        return new PolyvChannelCreateRequester(channelFeignClient::create);
    }

    public PolyvChannelCreateV3Requester createChannelV3Request() {
        return new PolyvChannelCreateV3Requester(channelFeignClient::createV3);
    }

    public PolyvChannelCreateV4Requester createChannelV4Request() {
        return new PolyvChannelCreateV4Requester(channelFeignClient::createV4);
    }

    /**
     * 频道删除
     *
     * @return
     */
    public PolyvChannelDeleteRequester deleteChannelRequest() {
        return new PolyvChannelDeleteRequester(channelFeignClient::delete);
    }


    /**
     * 查询账号下所有频道缩略信息
     * https://dev.polyv.net/2018/liveproduct/l-api/zhsz/get-simple-channel-list/
     *
     * @return
     */
    public PolyvChannelBriefInfoListRequester channelBriefInfoListRequest() {
        return new PolyvChannelBriefInfoListRequester(channelFeignClient::getBriefInfoList);
    }

    /**
     * 查询账号下所有频道列表
     *
     * @return
     */
    public PolyvChannelListRequester channelListRequest() {
        return new PolyvChannelListRequester(channelFeignClient::list);
    }

    /**
     * 频道基础列表
     *
     * @return
     */
    public PolyvChannelBasicListRequester channelBasicListRequest() {
        return new PolyvChannelBasicListRequester(channelFeignClient::getBasicList);
    }

    /**
     * 频道详情列表
     *
     * @return
     */
    public PolyvChannelDetailListRequester channelDetailListRequest() {
        return new PolyvChannelDetailListRequester(channelFeignClient::getDetailList);
    }

    /**
     * 账号回放设置
     * https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/setplaybackenabled/
     *
     * @return
     */
    public PolyvAppUpdatePlayBackEnabledRequester setPlayBackEnabledRequest() {
        return new PolyvAppUpdatePlayBackEnabledRequester(appFeignClient::setPlayBackEnabled);
    }


    /**
     * 查询多个频道汇总的统计数据
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/gksj/channel_play_summary/
     *
     * @return
     */
    public PolyvAppStatisticsSummaryRequester statisticsSummaryRequest() {
        return new PolyvAppStatisticsSummaryRequester(channelFeignClient::getStatisticsSummary);
    }

    /**
     * 设置观看日志入库回调地址
     *
     * @return
     */
    public PolyvAppUpdateViewLogReadyCallbackRequester updateViewLogReadyCallbackUrlRequest() {
        return new PolyvAppUpdateViewLogReadyCallbackRequester(appFeignClient::setViewLogCallbackUrl);
    }

    /**
     * 获取观看日志入库回调地址
     *
     * @return
     */
    public PolyvAppViewLogReadyCallbackRequester viewLogReadyCallbackUrlRequest() {
        return new PolyvAppViewLogReadyCallbackRequester(appFeignClient::getViewLogCallbackUrl);
    }

    /**
     * 查询多个频道的实时在线人数
     *
     * @return
     */
    public PolyvChannelsRealtimeViewerListRequester realtimeViewerListRequest() {
        return new PolyvChannelsRealtimeViewerListRequester(appFeignClient::getRealtimeViewerList);
    }

    /**
     * 批量删除频道
     *
     * @return
     */
    public PolyvAppBatchDeleteChannelRequester batchDeleteChannelRequest() {
        return new PolyvAppBatchDeleteChannelRequester(appFeignClient::batchDeleteChannel);
    }

    /**
     * 批量创建频道
     *
     * @return
     */
    public PolyvAppBatchCreateChannelRequester batchCreateChannelRequest() {
        return new PolyvAppBatchCreateChannelRequester(appFeignClient::batchCreateChannel);
    }

    /**
     * 打赏设置更新
     *
     * @return
     */
    public PolyvAppUpdateDonatePointRequester updateDonatePointSettingRequest() {
        return new PolyvAppUpdateDonatePointRequester(appFeignClient::updateDonatePointSetting);
    }


    /**
     * 打赏设置更新
     *
     * @return
     */
    public PolyvAppChannelDonatePointSettingRequester donatePointSettingRequest() {
        return new PolyvAppChannelDonatePointSettingRequester(appFeignClient::getDonatePointSetting);
    }

    /**
     * 查询多个频道概览统计数据
     *
     * @return
     */
    public PolyvAppStatisticsChannelSummaryRequester getChannelSummaryRequest() {
        return new PolyvAppStatisticsChannelSummaryRequester(appFeignClient::getChannelSummary);
    }

    /**
     * 查询频道点赞数和观看次数
     *
     * @return
     */
    public PolyvChannelLiveLikesRequester liveLikesRequest() {
        return new PolyvChannelLiveLikesRequester(appFeignClient::getLiveLikes);
    }


    /**
     * 修改频道密码
     *
     * @return
     */
    public PolyvChannelUpdatePasswdRequester updatePasswdRequest() {
        return new PolyvChannelUpdatePasswdRequester(appFeignClient::updateChannelPasswd);
    }


    /**
     * 修改频道主持人姓名
     *
     * @return
     */
    public PolyvChannelUpdatePublisherRequester updatePublisherRequest() {
        return new PolyvChannelUpdatePublisherRequester(appFeignClient::updatePublisher);
    }


    /**
     * 查询批量频道状态
     *
     * @return
     */
    public PolyvChannelLiveStatusRequester liveStatusRequest() {
        return new PolyvChannelLiveStatusRequester(appFeignClient::getLiveStatus);
    }

    /**
     * 删除直播分类
     */
    public PolyvAppDeleteCategoryRequester deleteCategoryRequest() {
        return new PolyvAppDeleteCategoryRequester(appFeignClient::deleteChannelCategory);
    }

    /**
     * 修改直播分类名称
     *
     * @return
     */
    public PolyvAppUpdateCategoryNameRequester updateCategoryNameRequest() {
        return new PolyvAppUpdateCategoryNameRequester(appFeignClient::updateChannelCategoryName);
    }

    /**
     * 修改直播分类顺序
     *
     * @return
     */
    public PolyvAppUpdateCategoryRankRequester updateCategoryRankRequest() {
        return new PolyvAppUpdateCategoryRankRequester(appFeignClient::updateChannelCategoryRank);
    }


    /**
     * 批量修改频道弹幕开关
     *
     * @return
     */
    public PolyvAppBatchUpdateDanmuRequester batchUpdateDanmuRequest() {
        return new PolyvAppBatchUpdateDanmuRequester(appFeignClient::updateBatchDanmu);
    }


    /**
     * 创建直播分类
     *
     * @return
     */
    public PolyvAppCreateCategoryRequester createCategoryRequest() {
        return new PolyvAppCreateCategoryRequester(appFeignClient::createCategory);
    }


    /**
     * 查询直播分类
     *
     * @return
     */
    public PolyvAppCategoryListRequester categoryListRequest() {
        return new PolyvAppCategoryListRequester(appFeignClient::getCategoryList);
    }

    /**
     * 批量导入严禁词
     *
     * @return
     */
    public PolyvAppAddBadWordsRequester addBadWordsRequest() {
        return new PolyvAppAddBadWordsRequester(appFeignClient::addBadWords);
    }

    /**
     * 查询账号严禁词
     *
     * @return
     */
    public PolyvAppBadWordListRequester userBadWordRequest() {
        return new PolyvAppBadWordListRequester(appFeignClient::getUserBadWord);
    }

    /**
     * 删除账号严禁词
     *
     * @return
     */
    public PolyvAppBadWordDeleteRequester userBadWordDeleteRequest() {
        return new PolyvAppBadWordDeleteRequester(appFeignClient::deleteUserBadWord);
    }

    /**
     * 查询账号禁言列表
     *
     * @return
     */
    public PolyvAppBannedListRequester userBannedListRequest() {
        return new PolyvAppBannedListRequester(appFeignClient::getUserBannedList);
    }

    /**
     * 账号设置禁言/解禁用户
     *
     * @return
     */
    public PolyvAppUpdateBannedRequester userUpdateBannedRequest() {
        return new PolyvAppUpdateBannedRequester(appFeignClient::updateUserBannedViewer);
    }

    /**
     * 手动结束问卷
     *
     * @return
     */
    public PolyvChannelQuestionnaireEndRequester questionnaireEndRequest() {
        return new PolyvChannelQuestionnaireEndRequester(appFeignClient::endQuestionnaire);
    }

    /**
     * 设置账号单点登录token
     *
     * @return
     */
    public PolyvAppUpdateSsoTokenRequester updateSsoTokenRequest() {
        return new PolyvAppUpdateSsoTokenRequester(appFeignClient::setSsoToken);
    }

    /**
     * 账号后台登录
     *
     * @return
     */
    public PolyvAppLoginRequester appLoginRequest() {
        return new PolyvAppLoginRequester(mjarLivePolyvBaseService::getUserLoginUrl);
    }

    /**
     * 批量查询频道单个回放信息
     * @return
     */
    public PolyvChannelPlayBackGetRequester playBackGetRequest() {
        return new PolyvChannelPlayBackGetRequester(appFeignClient::getPlayBack);
    }
}
