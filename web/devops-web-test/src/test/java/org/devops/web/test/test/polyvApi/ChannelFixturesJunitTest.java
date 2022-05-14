package org.devops.web.test.test.polyvApi;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvAppClient;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.enums.MenuType;
import org.devops.mjar.live.polyv.operator.requester.*;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelMenuDetail;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelMenuList;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppUploadImageFileParameter;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelLogoParameter;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateGlobalEnabledParameter;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.util.List;

/**
 * 频道装饰
 */
@EnableMjarLivePolyv
public class ChannelFixturesJunitTest extends AbstractJUnit4ServiceAction {

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
     * 设置频道是否应用通用设置
     */
    @Test
    public void applicationCommonSetting(){
        PolyvChannelUpdateGlobalEnabledRequester requester = channelClient().updateGlobalEnabledRequest();
        requester.parameter()
                .withEnabled(EnableSetting.Y)
                .withGlobalEnabledType(PolyvChannelUpdateGlobalEnabledParameter.SwitchType.AUTH);
        PolyvApiResult<Boolean> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道播放器中显示的logo
     */
    @Test
    public void updateLogo(){
        PolyvChannelLogoRequester requester = channelClient().updateLogoRequest();
        requester.parameter().withLogoHref("https://www.baidu.com")
                .withLogoImage("http://img.videocc.net/uimage/b/bf95ea5c05/f/bf95ea5c055cc0c6fee289172f0e6b3f_0.jpg")
                .withLogoOpacity(1F)
                .withLogoPosition(PolyvChannelLogoParameter.Position.BL);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    };

    /**
     * 修改暖场设置开关
     */
    @Test
    public void updateWarmUpOpen() {
        PolyvChannelUpdateWarmUpRequester requester = channelClient().updateWarmUpRequest();
        requester.parameter().withWarmUpEnabled("Y");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改暖场图片
     */
    @Test
    public void updateWarmUpImages() {
        PolyvChannelUpdateWarmUpImageRequester requester = channelClient().updateWarmUpImagesRequest();
        requester.parameter().withCoverImage("https://liveimages.videocc.net/assets/wimages/pc_images/logo.png");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改暖场视频
     */
    @Test
    public void updateWarmUpVideo() {
        PolyvChannelUpdateWarmUpVideoRequester requester = channelClient().updateWarmUpVideoRequest();
        requester.parameter().withWarmUpFlv("//s1.videocc.net/default-img/avatar/guest.png");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 增加页面菜单
     */
    @Test
    public void createMenus() {
        PolyvChannelCreateMenuRequester requester = channelClient().createMenuRequest();
        requester.parameter()
                .withName("nihao")
                .withContent("1122ddddfssdsss")
                .withType(MenuType.TEXT);
        PolyvApiResult<PolyvChannelMenuDetail> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道页面菜单信息
     */
    @Test
    public void searchMenuList() {
        PolyvChannelSearchMenuListRequester requester = channelClient().searchMenuListRequest();
        PolyvApiResult<List<PolyvChannelMenuDetail>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道图文直播内容
     */
    @Test
    public void searchImageMenuList() {
        PolyvChannelSearchImageMenuListRequester requester = channelClient().searchImageMenuListRequest();
        PolyvApiResult<PolyvChannelMenuList> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改提问功能开关
     */
    @Test
    public void updateMenuConsult() {
        PolyvChannelUpdateMenuConsultRequester requester = channelClient().updateMenuConsultRequest();
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改修改页面菜单排序
     */
    @Test
    public void updateMenuRank() {
        PolyvChannelUpdateMenuRankRequester requester = channelClient().updateMenuRankRequest();
        requester.parameter()
                .withMenuIds("69882b8035,92fc64f245,04be32d7ed,3d85f1e788,6131d6b1a6,4b6d119c65,eb7319cd69");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改修改页面菜单内容
     */
    @Test
    public void updateMenuInfo() {
        PolyvChannelUpdateMenuInfoRequester requester = channelClient().updateMenuInfoRequest();
        requester.parameter().withMenuId("69882b8035").withContent("bigboss2021");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 删除页面菜单内容
     */
    @Test
    public void DeleteMenuInfo() {
        PolyvChannelDeleteMenuRequester requester = channelClient().deleteMenuRequest();
        requester.parameter().withMenuIds("58e1059609,72ce44d9fe");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 上传频道所有装修图片素材
     */
    @Test
    public void UploadAllImage() throws Exception{
        PolyvAppUploadImageFileRequester requester = appClient().uploadImageRequest();
        requester.parameter()
                .withType(PolyvAppUploadImageFileParameter.Type.WARM_IMAGE)
                .withFile(new MockMultipartFile( "test.jpg",new FileInputStream("C:/Users/Admin/Desktop/2.jpg")));
        PolyvApiResult<List<String>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改直播介绍菜单
     */
    @Test
    public void updateIntroduction() {
        PolyvChannelUpdateIntroductMenuRequester requester = channelClient().updateIntroductMenuRequest();
        requester.parameter().withContent("111").withMenuType("desc");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

}
