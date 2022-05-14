package org.devops.web.test.test.polyvApi;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.operator.requester.*;
import org.devops.mjar.live.polyv.pojo.model.PolyvAdverties;
import org.devops.mjar.live.polyv.pojo.model.PolyvWeChatShare;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateHeadAdvertParameter;
import org.junit.Test;

import java.util.List;

/**
 * 营销设置
 */
@EnableMjarLivePolyv
public class MarketJunitTest extends AbstractJUnit4ServiceAction {

    private PolyvChannelClient channelClient(){
        return PolyvClientBuilder.standardChannelClient()
                .withAppId("fzu9jlakze")
                .withUserId("bf95ea5c05")
                .withAppSecret("91fc3d7946444bb4bb098bc497c52018")
                .withChannelId("2490359")
                .build();
    }

    /**
     * 查询频道广告列表
     */
    @Test
    public void searchAdvertList() {
        PolyvChannelSearchAdvertListRequester requester = channelClient().searchAdvertListRequest();
        PolyvApiResult<List<PolyvAdverties>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道播放器片头广告
     */
    @Test
    public void updateHeadAdvertList() {
        PolyvChannelUpdateHeadAdvertRequester requester = channelClient().updateHeadAdvertRequest();
        requester.parameter().withHeadAdvertDuration(30).withHeadAdvertHeight(150).withHeadAdvertHref("https://www.baidu.com")
                .withEnabled(EnableSetting.Y)
                .withHeadAdvertType(PolyvChannelUpdateHeadAdvertParameter.HeadAdvertType.IMAGE)
                .withHeadAdvertMediaUrl("http://img.videocc.net/uimage/b/bf95ea5c05/f/bf95ea5c055cc0c6fee289172f0e6b3f_0.jpg");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道播放器暂停广告
     */
    @Test
    public void updateStopAdvertList() {
        PolyvChannelUpdateStopAdvertRequester requester = channelClient().updateStopAdvertRequest();
        requester.parameter().withStopAdvertHref("http://www.baidu.com").withStopAdvertImage("https://img1.baidu.com/it/u=1357518075,2207145940&fm=26&fmt=auto&gp=0.jpg")
                .withEnabled(EnableSetting.Y);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     *查询频道微信分享信息
     */
    @Test
    public void searchWechatShhare() {
        PolyvChannelSearchWeChatShareRequester requester = channelClient().searchWeChatShareRequest();
        PolyvApiResult<PolyvWeChatShare> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道微信分享信息
     */
    @Test
    public void uploadWechatShare() {
        PolyvChannelUpdateWeChatShareRequester requester = channelClient().updateWeChatShareRequest();
        requester.parameter().withWeixinShareDesc("bigboss12").withWeixinShareTitle("hellobigboss");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }
}
