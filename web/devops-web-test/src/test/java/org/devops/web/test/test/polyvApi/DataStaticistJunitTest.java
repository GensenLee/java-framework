package org.devops.web.test.test.polyvApi;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvAppClient;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.operator.requester.*;
import org.devops.mjar.live.polyv.pojo.model.*;
import org.junit.Test;

import java.util.List;

/**
 * 数据统计
 */
@EnableMjarLivePolyv
public class DataStaticistJunitTest extends AbstractJUnit4ServiceAction {

    private PolyvChannelClient channelClient(){
        return PolyvClientBuilder.standardChannelClient()
                .withAppId("fzu9jlakze")
                .withUserId("bf95ea5c05")
                .withAppSecret("91fc3d7946444bb4bb098bc497c52018")
                .withChannelId("2490359")
                .build();
    }

    private PolyvAppClient appClient(){
        return PolyvClientBuilder.standardAppClient()
                .withAppId("fzu9jlakze")
                .withUserId("bf95ea5c05")
                .withAppSecret("91fc3d7946444bb4bb098bc497c52018")
                .build();
    }

    /**
     * 查询频道实时并发数据
     */
    @Test
    public void searchlStaticistRealtime() {
        PolyvChannelStaticRealtimeRequester requester = channelClient().polyvChannelStaticRealtimeRequest();
        PolyvChannelStatisticsRealtime apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道多场次概览统计数据
     */
    @Test
    public void searchStaticistGetSessionStats() {
        PolyvChannelStaticistGetSessionStstsRequester requester = channelClient().polyvChannelStaticistGetSessionStstsRequest();
        requester.parameter()
                .withSessionIds("g0p0t3h13f")
                .withEndTime("1626923146000")
                .withStartTime("1625108746000");
        PolyvApiResult<PolyvChannelStaticistGetSessionStats> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询多个频道概览统计数据
     */
    @Test
    public void searchStaticsistChannelSummary() {
        PolyvAppStatisticsChannelSummaryRequester requester = appClient().getChannelSummaryRequest();
        requester.parameter()
                .withChannelIds("2419208")
                .withEndDate("2021-07-01")
                .withStartDate("2021-07-22");
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询多个频道实时并发数据
     */
    @Test
    public void searchRealtimeData() {
        PolyvChannelsRealtimeViewerListRequester requester = appClient().realtimeViewerListRequest();
        requester.parameter().withChannelIds("2419208");
        PolyvApiResult<List<PolyvChannelViewers>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道历史并发数据
     */
    @Test
    public void getStaticistConcurrent() {
        PolyvChannelStatisticsConcurrenceRequester requester = channelClient().polyvChannelStatisticsConcurrenceRequest();
        requester.parameter()
                .withEndDate("2021-07-21")
                .withStartDate("2021-07-01");
        PolyvApiResult<List<PolyvChannelStatisticsConcurrence>> apiResult = requester.execute();
        System.out.println(apiResult);
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道历史最高并发数据
     */
    @Test
    public void getHistoryMaxConcurrence() {
        PolyvChannelStatisticsGetMaxHistoryRequester requester = channelClient().channelGetMaxHistoryRequest();
        requester.parameter()
                .withStartTime(1625122100000L)
                .withEndTime(1626850100000L);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(apiResult);
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道直播观看详情数据
     */
    @Test
    public void getChannelViewLog() {
        PolyvChannelStatisticsViewLogRequester requester = channelClient().channelStatisticsViewLogRequest();
        requester.parameter()
                .withCurrentDay("2021-07-13")
                .withParam1("bf95ea5c05")
                .withUserId("bf95ea5c05")
        ;
        PolyvChannelViewLogV3 apiResult = requester.execute();
        System.out.println(apiResult);
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道某段时间的直播观看详情数据
     */
    @Test
    public void getViewDetailInTime() {
        PolyvChannelStatisticsSummaryRequester requester = channelClient().channelStatisticsSummaryRequest();
        requester.parameter()
                .withStartDay("2021-07-01")
                .withEndDay("2021-07-21");
        PolyvChannelStatisticsSummary apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 分页查询频道直播观看详情数据
     */
    @Test
    public void getDetailByPation() {
        PolyvChannelSearchViewLogV2Requester requester = channelClient().searchViewLogViewRequest();
        requester.parameter().withCurrentDay("2021-07-30");
        PolyvApiResult<PolyvPaginator<PolyvChannelViewLogV1>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道连麦详情数据
     */
    @Test
    public void searchMicLog() {
        PolyvChannelMicLogListRequester requester = channelClient().micLogListRequest();
        requester.parameter().withEndDate("2021-07-01").withStartDate("2021-07-30")
                .withPage(1).withPageSize(10);
        PolyvApiResult<PolyvPaginator<PolyvMicLog>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道聊天记录
     */
    @Test
    public void searchChatHistory() {
        PolyvChannelChatHistoryRequester requester = channelClient().chatHistoryRequest();
        requester.parameter().withStartDay("2021-07-01").withEndDay("2021-07-30");
        PolyvApiResult<List<PolyvChannelChatHistoryV3>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道提问记录
     */
    @Test
    public void getChannelRecord() {
        PolyvChannelChatGetQuestionRequester requester = channelClient().chatGetQuestionRequest();
        requester.parameter()
                .withBegin(0)
                .withEnd(10);
        PolyvApiResult<List<PolyvChannelChatGetQuestion>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道登记观看记录
     */
    @Test
    public void getChannelAuthRecord() {
        PolyvChannelAuthGetRecordInfoRequester requester = channelClient().authGetRecordInfoRequest();
        requester.parameter()
                .withPage(1)
                .withPageSize(10);
        PolyvApiResult<PolyvPaginator<PolyvChannelAuthGetRecordInfo>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 下载频道登记观看记录
     */
    @Test
    public void downLoadAuth() {
//        PolyvChannelAuthDownloadRequester request = channelClient().getAuthDownloadRequest();
//        request.parameter().withRank(2);
//        ResponseEntity<byte[]> entity = request.execute();
//        return entity;
    }

    /**
     * 查询频道中奖记录
     */
    @Test
    public void getListLottery() {
        PolyvChannelLotteryListLotteryRequester requester = channelClient().listLotteryRequest();
        requester.parameter()
                .withPage(1)
                .withLimit(10)
                .withStartTime(1625122100000L)
                .withEndTime(1626850100000L)
                .withSessionId("g0lr6ijoca");
        PolyvApiResult<PolyvPaginator<PolyvChannelLotteryListDetail>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道场次中奖记录
     */
    @Test
    public void getLotteryWinDetail() {
        PolyvChannelLotteryGetWinDetailRequester requester = channelClient().getLotteryWinDetailRequest();
        requester.parameter()
                .withPage(1)
                .withLimit(10)
                .withLotteryId("1625122100000");
        PolyvApiResult<PolyvPaginator<PolyvChannelLotteryListDetail>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 下载频道场次中奖记录
     */
    @Test
    public void downLoadLottery() {
//        PolyvChannelLotteryDetailDownloadRequester requester = channelClient().getLotteryDetailDownloadRequest();
//        requester.parameter().withLotteryId("1122");
//        ResponseEntity<byte[]> entity = requester.execute();
//        return entity;
    }

    /**
     * 查询频道问卷题目与结果
     */
    @Test
    public void getQuestionaireDetail() {
        PolyvChannelQuestionnaireDetailRequester requester = channelClient().questionnaireDetailRequest();
        requester.parameter().withQuestionnaireId("");
        PolyvApiResult<PolyvQuestionnaire> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道问卷结果
     */
    @Test
    public void getChannelResult() {
        PolyvChannelQuestionnaireRecordRequester requester = channelClient().getChannelQuestionnaireResultRequest();
        requester.parameter()
                .withStartDate("2021-07-01")
                .withStartDate("2021-07-30");
        PolyvApiResult<List<PolyvQuestionnaireRecord>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 分页查询频道问卷结果
     */
    @Test
    public void getChannelResultList() {
        PolyvChannelQuestionnaireRecordListRequester requester = channelClient().getChannelQuestionaireResultListRequest();
        requester.parameter()
                .withStartDate("2021-07-01")
                .withStartDate("2021-07-30")
                .withPage(1)
                .withPageSize(10);
        PolyvApiResult<PolyvPaginator<PolyvQuestionnaireRecord>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道签到结果
     */
    @Test
    public void getChannelCheckinList() {
        PolyvChannelCheckinListPageRequester requester = channelClient().getChannelCheckinListRequest();
        requester.parameter()
                .withDate("2021-07-01")
                .withPage(1)
                .withPageSize(10);

        PolyvApiResult<PolyvPaginator<PolyvCheckinStats>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道发起的签到记录
     */
    @Test
    public void searchCheckinRecord() {
        PolyvChannelCheckinListRequester requester = channelClient().checkinListRequest();
        requester.parameter()
                .withSessionId("");
        PolyvApiResult<List<PolyvChannelCheckin>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 根据发起签到记录查询签到结果
     */
    @Test
    public void searchCheckinResult() {
        PolyvChannelCheckinDetailRequester requester = channelClient().checkinDetailRequest();
        requester.parameter().withCheckinId("");
        PolyvApiResult<List<PolyvCheckinStats>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道答题卡结果
     */
    @Test
    public void getAnswerRecord() {
        PolyvChannelSearchQuestionaireResultRequester requester = channelClient().searchQuestionaireResultRequest();
        requester.parameter().withEndDate("2021-07-30").withStartDate("2021-07-01");
        PolyvApiResult<List<PolyvQuestionResult>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

}
