package org.devops.web.test.test.polyvApi;

import com.google.common.collect.Lists;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvAppClient;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.operator.requester.PolyvAppChannelDonatePointSettingRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelSentRewardMsgRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelUpdateDonateCashRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelUpdateDonateGoodRequester;
import org.devops.mjar.live.polyv.pojo.model.PolyvDonateGoods;
import org.devops.mjar.live.polyv.pojo.model.PolyvDonatePointSettingModel;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSentRewardMsgParameter;
import org.junit.Test;

/**
 * 打赏
 */
@EnableMjarLivePolyv
public class DonatePointJunitTest extends AbstractJUnit4ServiceAction {

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
     * 查询频道打赏设置
     */
    @Test
    public void searchDonatePointSetting(){
        PolyvAppChannelDonatePointSettingRequester requester = appClient().donatePointSettingRequest();
        PolyvApiResult<PolyvDonatePointSettingModel> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 设置现金打赏
     */
    @Test
    public void updateDonateCash() {
        PolyvChannelUpdateDonateCashRequester requester = channelClient().updateDonateCashRequest();
        requester.parameter()
                .withCashes(Lists.newArrayList(10.88, 16.66, 18.88, 66.11, 666.66, 1888.09))
                .withCashMin(300.0);
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }
    /**
     * 设置道具打赏
     */
    @Test
    public void updateDonateGood() {
        PolyvChannelUpdateDonateGoodRequester requester = channelClient().updateDonateGoodRequest();
        PolyvDonateGoods goods = new PolyvDonateGoods();
        goods.setGoodName("鲜花");
        goods.setGoodImg("//livestatic.videocc.net/uploaded/images/webapp/channel/donate/01-flower.png");
        goods.setGoodPrice(0F);
        goods.setGoodEnabled(EnableSetting.Y.getValue());
        PolyvDonateGoods goods1 = new PolyvDonateGoods();
        goods1.setGoodImg("//livestatic.videocc.net/uploaded/images/webapp/channel/donate/08-car.png");
        goods1.setGoodName("你好");
        goods1.setGoodPrice(30.0F);
        goods1.setGoodEnabled(EnableSetting.Y.getValue());
        requester.parameter().withGoods(Lists.newArrayList(goods,goods1));
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }
    /**
     * 发送打赏消息
     */
    @Test
    public void sentRewardMsg() {
        PolyvChannelSentRewardMsgRequester requester = channelClient().sentRewardMsgRequest();
        requester.parameter()
                .withNickname("abc")
                .withAvatar("https://livestatic.videocc.net/uploaded/images/webapp/avatar/default-teacher.png")
                .withViewerId("123123")
                .withDonateType(PolyvChannelSentRewardMsgParameter.DonateType.GOOD)
                .withContent("鲜花")
                .withGoodImage("http://s1.videocc.net/default-img/donate/flower.png");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }
}
