package org.devops.web.test.test.polyvApi;

import com.google.common.collect.Lists;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvAppClient;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.operator.requester.*;
import org.devops.mjar.live.polyv.pojo.model.PolyvAdminInfo;
import org.devops.mjar.live.polyv.pojo.model.PolyvBadWords;
import org.devops.mjar.live.polyv.pojo.model.PolyvBanned;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelKicked;
import org.devops.mjar.live.polyv.pojo.param.*;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * 聊天室
 */
@EnableMjarLivePolyv
public class TalkJunitTest extends AbstractJUnit4ServiceAction {

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
     * 禁言/解禁用户
     */
    @Test
    public void updateBannedUser() {
        PolyvChannelUpdateBannedUserRequester requester = channelClient().updateBannedUserRequest();
        requester.parameter()
                .withToBanned(PolyvChannelUpdateBannedUserParameter.ToBanned.Y)
                .withUserIds("bf95ea5c05");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 禁言IP
     */
    @Test
    public void addBannedIp() {
        PolyvChannelAddBannedIpRequester requester = channelClient().addBannedIpRequest();
        requester.parameter()
                .withIp("192.168.0.111");
        PolyvApiResult<List<String>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道禁言用户Userid/IP
     */
    @Test
    public void getBannedList() {
        PolyvChannelBannedListRequester requester = channelClient().bannedListRequest();
        requester.parameter()
                .withType(PolyvChannelBannedListParameter.Type.USERID);
        PolyvApiResult<List<String>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 批量导入严禁词
     */
    @Test
    public void AddBadWords() {
        PolyvAppAddBadWordsRequester requester = appClient().addBadWordsRequest();
        requester.parameter()
                .withWords("[\"badWord1\",\"badWord2\",\"bigboss\"]");
        PolyvApiResult<List<PolyvBadWords>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道严禁词/禁言ip
     */
    @Test
    public void getBadWordsList() {
        PolyvChannelBadWordsListRequester requester = channelClient().badWordsListRequest();
        requester.parameter().withType(PolyvChannelBadWordsListParameter.Type.IP);
        PolyvApiResult<List<String>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询账号严禁词
     */
    @Test
    public void getUserBadWord() {
        PolyvAppBadWordListRequester requester = appClient().userBadWordRequest();
        PolyvApiResult<List<String>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 删除频道严禁词/禁言ip
     */
    @Test
    public void deleteBadWord() {
        PolyvChannelBadWordDeleteRequester requester = channelClient().badWordDeleteRequest();
        requester.parameter()
                .withType(PolyvChannelBadWordDeleteParameter.Type.BADWORD)
                .withContent("badWord1");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 删除账号禁言词
     */
    @Test
    public void userDeleteBadWord() {
        PolyvAppBadWordDeleteRequester requester = appClient().userBadWordDeleteRequest();
        requester.parameter()
                .withWords("badWord1");
        PolyvApiResult<List<String>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 批量删除频道多条聊天记录
     */
    @Test
    public void batchDeleteChat() {
        PolyvChannelChatBatchDeleteRequester requester = channelClient().chatBatchDeleteRequest();
        requester.parameter()
                .withIds("21edee30-dfc3-11eb-829e-b143e92a2478");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 删除频道单条聊天记录
     */
    @Test
    public void deleteChat() {
        PolyvChannelChatDeleteRequester requester = channelClient().chatDeleteRequest();
        requester.parameter()
                .withId("61931ce0-dfc3-11eb-a519-9100f10a5815");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 清空频道聊天记录
     */
    @Test
    public void chatClean() {
        PolyvChannelChatCleanRequester requester = channelClient().chatCleanRequest();
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道踢人列表
     */
    @Test
    public void getKickedList() {
        PolyvChannelKickedListRequester requester = channelClient().kickedListRequest();
        PolyvApiResult<List<PolyvChannelKicked>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询账号禁言列表
     */
    @Test
    public void getUserBannedList() {
        PolyvAppBannedListRequester requester = appClient().userBannedListRequest();
        PolyvApiResult<List<PolyvBanned>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 账号设置禁言/解禁用户
     */
    @Test
    public void updateUserBanned() {
        PolyvAppUpdateBannedRequester request = appClient().userUpdateBannedRequest();
        request.parameter()
                .withViewerIds(FastJsonUtils.toJsonString(Lists.newArrayList("123123","456456")))
                .withBanned(PolyvAppUpdateBannedParameter.Banned.N);
        PolyvApiResult<String> apiResult = request.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改讲师身份信息
     */
    @Test
    public void updateTeacherInfo(){
        PolyvChannelUpdateTeacherRequester requester = channelClient().updateTeacherRequest();
        requester.parameter().withActor("superTeacher")
                .withAvatar("//livestatic.videocc.net/uploaded/images/webapp/channel/donate/01-flower.png")
                .withNickname("bigboss")
                .withPasswd("6666666");
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改管理员身份信息
     */
    @Test
    public void updateAdminInfo() throws Exception {
        PolyvChannelUpdateAdminInfoRequester requester = channelClient().updateAdminInfoRequest();
        requester.parameter()
                .withAvatar(new MockMultipartFile("test", new FileInputStream(new File("C:\\Users\\Admin\\Desktop\\2.jpg"))))
                .withNickname("baby")
                .withActor("abc1");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询管理员身份信息
     */
    @Test
    public void getAdminInfo() {
        PolyvChannelAdminInfoRequester requester = channelClient().adminInfoRequest();
        PolyvApiResult<PolyvAdminInfo> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 发送图文信息
     */
    @Test
    public void sentMessage() {
        PolyvChannelSentMessageRequester requester = channelClient().sentMessageRequest();
        requester.parameter()
                .withMsg("test")
                .withPic("123")
                .withNickName("abc");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 获取聊天室在线列表
     */
    @Test
    public void getOnLineList(){
        PolyvChannelChatOnLineListRequester requester = channelClient().chatOnLineListRequest();
        requester.parameter().withRoomId("2419208");
        requester.execute();

    }

    /**
     * 发送点赞
     */
    @Test
    public void sentFavor() {
        PolyvChannelSentFavorRequester requester = channelClient().sentFavorRequest();
        requester.parameter()
                .withViewerId("123123");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

}
