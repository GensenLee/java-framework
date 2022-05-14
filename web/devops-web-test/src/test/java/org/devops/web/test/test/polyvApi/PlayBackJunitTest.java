package org.devops.web.test.test.polyvApi;

import org.devops.core.utils.constant.CommonConstant;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.ListUtil;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvAppClient;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.enums.PlaybackType;
import org.devops.mjar.live.polyv.enums.VideoListType;
import org.devops.mjar.live.polyv.operator.requester.*;
import org.devops.mjar.live.polyv.pojo.model.*;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelAddRecordTaskParameter;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPlayBackDeleteParameter;
import org.junit.Test;

import java.util.List;

/**
 * 回放管理
 */
@EnableMjarLivePolyv
public class PlayBackJunitTest extends AbstractJUnit4ServiceAction {

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
     * 查询频道回放开关
     */
    @Test
    public void searchPlayBackEnable(){
        PolyvChannelPlayBackEnableRequester requester = channelClient().playBackEnableRequest();
        PolyvApiResult<EnableSetting> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道回放开关
     */
    @Test
    public void updatePlayBackVideo(){
        PolyvAppUpdatePlayBackEnabledRequester requester = appClient().setPlayBackEnabledRequest();
        requester.parameter().withPlayBackEnabled(EnableSetting.N);
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道回放设置
     */
    @Test
    public void UpdateplayBackSetting() {
        PolyvChannelUpdatePlayBackEnabledRequester requester = channelClient().updatePlayBackEnabledRequest();
        requester.parameter()
                .withPlayBackEnabled(EnableSetting.Y)
                .withType(PlaybackType.LIST);
//                .withOrigin(PlaybackOrigin.PLAYBACK);
        PolyvApiResult<Boolean> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道录制视频信息
     */
    @Test
    public void searchRecordFiles() {
        PolyvChannelRecordFilesRequester requester = channelClient().recordFilesRequest();
        requester.parameter()
                .withStartDate("2021-07-13")
                .withEndDate("2021-07-19");
        PolyvApiResult<List<PolyvChannelRecordInfo>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 根据fileId查询录制视频信息
     */
    @Test
    public void searchRecordDetailByFileId() {
        PolyvChannelRecordDetailRequester requester = channelClient().recordDetailRequest();
        requester.parameter()
                .withFileId("6d6a10733e99ccade17c2f1b422c0c03");
        PolyvApiResult<PolyvChannelRecordDetail> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 批量转存录制视频到云点播
     */
    @Test
    public void recordConvert() {
        PolyvChannelRecordConvertRequester requester = channelClient().recordConvertRequest();
        requester.parameter()
                .withFileIds("3e8eb269e3aeca10d9c927390f25058c,6d6a10733e99ccade17c2f1b422c0c03");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 删除直播暂存中的录制文件
     */
    @Test
    public void recordDelete() {
        PolyvChannelRecordDeleteRequester requester = channelClient().recordDeleteRequest();
        requester.parameter()
                .withSessionId("g0xvjlpapj");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 将点播视频添加到视频库
     */
    @Test
    public void playBackAddVideos() {
        PolyvChannelPlayBackAddVideosRequester requester = channelClient().playBackAddVideosRequest();
        requester.parameter()
                .withVid("bf95ea5c05b0b7e94af94eef86982069_b");
        PolyvApiResult<PolyvChannelPlayBackItem> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 分页获取直播暂存列表
     */
    @Test
    public void getRecordList() {
        PolyvChannelRecordListRequester requester = channelClient().recordListRequest();
        requester.parameter()
                .withPage(1)
                .withLimit(10);
        PolyvApiResult<PolyvPaginator<PolyvChannelRecordItem>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     *  查询视频库列表
     */
    @Test
    public void getPlayBackList() {
        PolyvChannelPlayBackListRequester requester = channelClient().playBackListRequest();
        requester.parameter()
                .withPage(1).withPageSize(10);
        PolyvApiResult<PolyvPaginator<PolyvChannelPlayBackItem>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询所有频道的回放视频
     */
    @Test
    public void getPlayBackSpecialList() {
        PolyvChannelPlayBackSpecialListRequester requester = channelClient().playBackSpecialListRequest();
        requester.parameter()
                .withPage(1);
        PolyvApiResult<PolyvPaginator<PolyvChannelPlayBackItem>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 删除视频库中的视频
     */
    @Test
    public void playBackDelete() {
        PolyvChannelPlayBackDeleteRequester requester = channelClient().playBackDeleteRequest();
        requester.parameter()
                .withListType(PolyvChannelPlayBackDeleteParameter.ListType.PLAYBACK)
                .withVideoId("67660c31c8");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改视频库的默认视频
     */
    @Test
    public void UpdateDefaultVedio(){
        PolyvChannelPlayBackSetDefaultRequester requester = channelClient().playBackSetDefaultRequest();
        requester.parameter().withListType(VideoListType.VOD)
                .withVideoId("b3f7e4a5c9");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改视频库视频排序
     */
    @Test
    public void playBackSingleSort() {
        PolyvChannelPlayBackSortRequester requester = channelClient().playBackSortRequest();
        requester.parameter()
                .withVideoIds("{\\\"videoIds\\\": [\\\"3f4593653e1ba7735eb3254a9ce95a02\\\",\\\"5243b4185370cf8c75a4704afc4fa97f\\\"]}");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改回放视频名称
     */
    @Test
    public void updatePlayBackVideoName(){
        PolyvChannelPlayBackUpdateTitleRequester requester = channelClient().playBackUpdateTitleRequest();
        requester.parameter().withVideoId("b3f7e4a5c9").withTitle("bigbossVideo");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 裁剪录制文件
     */
    @Test
    public void recordClip() {
        PolyvChannelRecordClipRequester requester = channelClient().recordClipRequest();
        requester.parameter()
                .withFileId("6d6a10733e99ccade17c2f1b422c0c03")
                .withDeleteTimeFrame("[{\"start\":10, \"end\":30}]")
                .withFileName("bigbossTurnVideo");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 合并直播录制文件
     */
    @Test
    public void recordMerge() {
        PolyvChannelRecordMergeRequester requester = channelClient().recordMergeRequest();
        requester.parameter()
                .withFileIds("6d6a10733e99ccade17c2f1b422c0c03,c327913248ab464b52366a399e95d3d1")
                .withFileName("BigbossDownVideo");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 导出合并的录制文件并回调mp4下载地址
     */
    @Test
    public void recordMergeMp4() {
        PolyvChannelRecordMergeMp4Requester requester = channelClient().recordMergeMp4Request();
        requester.parameter()
                .withStartTime(LongUtil.toLong("1626143262000"))
                .withEndTime(LongUtil.toLong("1626166250000"));
        PolyvApiResult<PolyvMergeMp4> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 创建重置课件任务
     */
    @Test
    public void addRecordTask() {
        PolyvChannelAddRecordTaskRequester requester = channelClient().addRecordTaskRequest();
        requester.parameter()
                .withVideoType(PolyvChannelAddRecordTaskParameter.VideoType.RECORD)
                .withVideoId("b17eb0f75100dfc83655091c5e1dfcb7");
        PolyvApiResult<Boolean> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询重制课件任务列表
     */
    @Test
    public void searchPptRecordListTest() {
        PolyvChannelPptRecordListRequester requester = channelClient().polyvChannelPptRecordListRequest();
        requester.parameter()
//                .withVideoId("b17eb0f75100dfc83655091c5e1dfcb7");
                .withPage(1)
                .withPageSize(999);
//                .withStatus(PptRecordStatus.Fail);
        PolyvApiResult<PolyvPaginator<PolyvChannelPptRecordList>> apiResult = requester.execute();
//        System.out.println(FastJsonUtils.toJsonString(apiResult));

        List<PolyvChannelPptRecordList> contents = apiResult.getData().getContents();
        List<String> taskIdList = ListUtil.toStringList(contents, "taskId");
        String taskIds = StringUtil.join(taskIdList, CommonConstant.COMMA_MARK);
        System.out.println(taskIds);
    }

    /**
     * 删除重制课件
     */
    @Test
    public void deletePptRecord(){
        PolyvChannelDeletePptRecordRequester requester = channelClient().deletePptRecordRequest();
        requester.parameter().withTaskIds("370948,370947");
        PolyvApiResult<PolyvChannelPptRecordResult> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 批量查询频道单个回放信息
     */
    @Test
    public void getPlayBack() {
        PolyvChannelPlayBackGetRequester requester = appClient().playBackGetRequest();
        requester.parameter().withChannelIds("2490359,2487942");
        PolyvApiResult<List<PolyvPlayBackInfo>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

}
