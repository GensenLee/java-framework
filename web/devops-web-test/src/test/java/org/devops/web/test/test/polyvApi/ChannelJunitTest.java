package org.devops.web.test.test.polyvApi;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvAppClient;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.enums.ChannelAccountRole;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.enums.StreamType;
import org.devops.mjar.live.polyv.operator.requester.*;
import org.devops.mjar.live.polyv.pojo.model.*;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateSwitchParameter;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 频道管理单元测试
 */
@EnableMjarLivePolyv
public class ChannelJunitTest extends AbstractJUnit4ServiceAction {

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
     * 创建单个频道
     */
    @Test
    public void createSingleChannel() {
        PolyvChannelCreateRequester requester = appClient().createChannelRequest();
        requester.parameter().withName("bigboss2").withChannelPasswd("123456");
        PolyvApiResult<PolyvChannel> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 批量创建频道
     */
    @Test
    public void batchCreateChannel() {
        PolyvAppBatchCreateChannelRequester requester = appClient().batchCreateChannelRequest();

        List<PolyvChannelBatchCreateItem> createItems = new ArrayList<>();
        PolyvChannelBatchCreateItem createItem = new PolyvChannelBatchCreateItem();
        createItem.setName("bigboss3");
        createItem.setChannelPasswd("123456");
        createItems.add(createItem);

        requester.parameter().withChannels(createItems);
        PolyvApiResult<PolyvChannelList> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道信息
     */
    @Test
    public void searchChannelInfo() {
        PolyvChannelSearchRequester requester = channelClient().searchRequest();
        PolyvApiResult<PolyvChannel> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询引导图
     */
    @Test
    public void getSplash() {
        PolyvChannelSplashRequester requester = channelClient().splashRequest();
        PolyvApiResult<PolyvSplash> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道点赞数和观看次数
     */
    @Test
    public void getLiveLike() {
        PolyvChannelLiveLikesRequester requester = appClient().liveLikesRequest();
        requester.parameter().withChannelIds("2419208");
        PolyvApiResult<List<PolyvLiveLikes>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道直播开始时间
     */
    @Test
    public void searchCountDown() {
        PolyvChannelCountDownRequester requester = channelClient().countDownRequest();
        PolyvApiResult<PolyvCountDown> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 删除单个频道
     */
    @Test
    public void deleteSingleChannel() {
        PolyvChannelDeleteRequester requester = appClient().deleteChannelRequest();
        requester.parameter().withChannelId("2451908");
        PolyvApiResult<Boolean> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 批量删除频道
     */
    @Test
    public void batchDeleteChannel() {
        PolyvAppBatchDeleteChannelRequester requester = appClient().batchDeleteChannelRequest();
        List<String> channelList = new ArrayList<>();
        channelList.add("2451883");
        requester.parameter().withChannelIds(channelList);
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道信息修改频道信息
     */
    @Test
    public void updaeChannelInfo() {
        PolyvChannelUpdateV3Requester requester = channelClient().updateV3Request();
        PolyvChannelUpdateBasicSetting setting = new PolyvChannelUpdateBasicSetting();
        setting.setDesc("<p><h1>test</h1></p>");
        requester.parameter().withBasicSetting(setting);
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道密码
     */
    @Test
    public void UpdateChannelPassword() {
        PolyvChannelUpdatePasswdRequester requester = appClient().updatePasswdRequest();
        requester.parameter()
                .withPasswd("6666666")
                .withChannelId("2419208");
        PolyvApiResult<Boolean> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道名称
     */
    @Test
    public void updateChannelName() {
        PolyvChannelUpdateNameRequester requester = channelClient().updateNameRequest();
        requester.parameter()
                .withName("BigBoss");
        PolyvApiResult<Boolean> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道主持人姓名
     */
    @Test
    public void updatePublisher() {
        PolyvChannelUpdatePublisherRequester requester = appClient().updatePublisherRequest();
        requester.parameter()
                .withPublisher("bigboss")
                .withChannelId("2419208");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道图标
     */
    @Test
    public void updateChannelCoverImg() throws Exception {
        PolyvChannelUpdateCoverImgRequester requester = channelClient().updateCoverImgRequest();
        requester.parameter()
                .withImgfile(new MockMultipartFile("test", new FileInputStream("C:\\Users\\Admin\\Desktop\\1.jpg")));
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 批量修改频道弹幕开关
     */
    @Test
    public void updateBatchDanmu() {
        PolyvAppBatchUpdateDanmuRequester requester = appClient().batchUpdateDanmuRequest();
        requester.parameter()
                .withCloseDanmu(EnableSetting.Y)
                .withShowDanmuInfoEnabled(EnableSetting.Y)
                .withChannelIds("2419208");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改引导图
     */
    @Test
    public void updateSplash() throws Exception {
        PolyvChannelUpdateSplashRequester requester = channelClient().updateSplashRequest();
        requester.parameter()
                .withSplashEnabled(EnableSetting.Y)
                .withImgfile(new MockMultipartFile("test", new FileInputStream("C:\\Users\\Admin\\Desktop\\1.jpg")));
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道点赞数和观看次数
     */
    @Test
    public void updateLiveLikes() {
        PolyvChannelUpdateLikesRequester requester = channelClient().updateLikesRequest();
        requester.parameter()
                .withLikes(30000)
                .withViewers(10000);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改直播倒计时设置
     */
    @Test
    public void updateCountDown() {
        PolyvChannelUpdateCountDownRequester requester = channelClient().updateCountDownRequest();
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道观看人数限制
     */
    @Test
    public void updateMaxViewer() {
        PolyvChannelUpdateMaxViewerRequester requester = channelClient().updateMaxViewerRequest();
        requester.parameter()
                .withMaxViewer(3000);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道推流方式
     */
    @Test
    public void updateStream() {
        PolyvChannelUpdateStreamRequester requester = channelClient().updateStreamRequest();
        requester.parameter()
                .withStreamType(StreamType.CLIENT);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道的功能开关状态
     */
    @Test
    public void updateSwitch() {
        PolyvChannelUpdateSwitchRequester requester = channelClient().updateSwitchRequest();
        requester.parameter()
                .withType(PolyvChannelUpdateSwitchParameter.Type.RED_PACK)
                .withEnabled(EnableSetting.Y);
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道的功能开关状态
     */
    @Test
    public void searchSwitch() {
        PolyvChannelSearchSwitchRequester requester = channelClient().searchSwitchRequest();
        PolyvApiResult<List<PolyvChannelSwitchState>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询批量频道状态
     */
    @Test
    public void batchSearchLiveState() {
        PolyvChannelLiveStatusRequester requester = appClient().liveStatusRequest();
        requester.parameter().withChannelIds("2419208");
        PolyvApiResult<List<PolyvLiveStatus>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道直播流信息
     */
    @Test
    public void searchStreamInfo() {
        PolyvChannelStreamInfoRequester requester = channelClient().streamInfoRequest();
        PolyvApiResult<PolyvChannelStream> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道状态为直播中
     */
    @Test
    public void updateOnLine() {
        PolyvChannelLiveStreamRequester requester = channelClient().streamLiveRequest();
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道状态为无直播
     */
    @Test
    public void updateOnLineEnd() {
        PolyvChannelEndStreamRequester requester = channelClient().streamEndRequest();
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道状态为禁止直播
     */
    @Test
    public void updateOnLineCutOff(){
        PolyvChannelCutoffStreamRequester requester = channelClient().streamCutoffRequest();
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道状态为可直播
     */
    @Test
    public void updateOnLineResume(){
        PolyvChannelResumeStreamRequester requester = channelClient().streamResumeRequest();
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道场次信息
     */
    @Test
    public void searchSessionInfo(){
        PolyvChannelSessionListRequester requester = channelClient().sessionListRequest();
        requester.parameter().withPageSize(10).withPage(1);
        PolyvApiResult<PolyvPaginator<PolyvChannelSession>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     *查询所有频道的缩略信息
     */
    @Test
    public void getAllChannelBriefInfo(){
        PolyvChannelBriefInfoListRequester requester = appClient().channelBriefInfoListRequest();
        PolyvApiResult<PolyvPaginator<PolyvChannelBriefInfo>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     *查询所有频道的基础信息
     */
    @Test
    public void getAllChannelBaseInfo(){
        PolyvChannelBasicListRequester requester = appClient().channelBasicListRequest();
        PolyvApiResult<PolyvPaginator<PolyvChannelBasic>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     *查询所有频道的详细信息
     */
    @Test
    public void getAllChannelDetailInfo(){
        PolyvChannelDetailListRequester requester = appClient().channelDetailListRequest();
        PolyvApiResult<PolyvPaginator<PolyvChannelDetail>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道列表
     */
    @Test
    public void getChannelList(){
        PolyvChannelListRequester requester = appClient().channelListRequest();
        PolyvApiResult<PolyvChannelIdList> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 创建直播分类
     */
    @Test
    public void createCategory() {
        PolyvAppCreateCategoryRequester requester = appClient().createCategoryRequest();
        requester.parameter()
                .withCategoryName("testing");
        PolyvApiResult<PolyvCategory> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询直播分类
     */
    @Test
    public void getCategoryList() {
        PolyvAppCategoryListRequester requester = appClient().categoryListRequest();
        PolyvApiResult<List<PolyvCategory>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 删除直播分类
     */
    @Test
    public void DeleteCategory() {
        PolyvAppDeleteCategoryRequester requester = appClient().deleteCategoryRequest();
        requester.parameter().withCategoryId("379081");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改直播分类名称
     */
    @Test
    public void updateCategoryName() {
        PolyvAppUpdateCategoryNameRequester requester = appClient().updateCategoryNameRequest();
        requester.parameter().withCategoryId("378304").withCategoryName("test1");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改直播分类顺序
     */
    @Test
    public void updateCategoryRank() {
        PolyvAppUpdateCategoryRankRequester requester = appClient().updateCategoryRankRequest();
        requester.parameter().withCategoryId("378304").withAfterCategoryId("378952");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 创建单个子频道
     */
    @Test
    public void createSingleAccount(){
        PolyvChannelCreateAccountRequester requester = channelClient().accountCreateRequest();
        requester.parameter().withRole(ChannelAccountRole.ASSISTANT).withNickname("bigboss");
        PolyvApiResult<PolyvChannelAccount> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 批量创建子频道
     */
    @Test
    public void batchCreateAccount() {
        PolyvChannelBatchCreateAccountRequester requester = channelClient().batchCreateAccountRequest();
        PolyvChannelBatchCreateAccountItem item = new PolyvChannelBatchCreateAccountItem();
        item.setNickname("bigboss23");
        item.setPasswd("12344");
        item.setRole("guest");
        List<PolyvChannelBatchCreateAccountItem> list = new ArrayList<>();
        list.add(item);
        requester.parameter().withList(list);
        PolyvApiResult<List<PolyvChannelAccount>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询单个子频道信息
     */
    @Test
    public void searchSingleAccount() {
        PolyvChannelSearchSingleAccountRequester requester = channelClient().searchSingleAccountRequest();
        requester.parameter().withAccount("0042419208");
        PolyvApiResult<PolyvChannelAccount> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道号下所有子频道信息
     */
    @Test
    public void searchAllAccount() {
        PolyvChannelSearchAccountRequester requester = channelClient().searchAccountRequest();
        PolyvApiResult<List<PolyvChannelAccount>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改子频道信息
     */
    @Test
    public void updateAccountInfo(){
        PolyvChannelUpdateAccountRequester requester = channelClient().updateAccountRequest();
        requester.parameter().withAccount("0092419208");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 删除子频道
     */
    @Test
    public void deleteAccount(){
        PolyvChannelDeleteAccountRequester requester = channelClient().deleteAccountRequest();
        requester.parameter().withAccount("0092419208");
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 获取频道API的访问令牌
     */
    @Test
    public void getApiToken() {
        PolyvChannelCreateTokenRequester requester = channelClient().createChannelTokenRequest();
        requester.parameter().withDisposable(true).withExpireSeconds(3600);
        PolyvApiResult<PolyvChannelToken> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

}
