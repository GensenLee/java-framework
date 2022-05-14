package org.devops.web.test.test.polyvApi;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.enums.CardImageType;
import org.devops.mjar.live.polyv.enums.CardShowCondition;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.operator.requester.*;
import org.devops.mjar.live.polyv.pojo.model.PolyvCardPushInfo;
import org.junit.Test;

import java.util.List;

/**
 * @author fangzy
 * @description：卡片推送单元测试
 */
@EnableMjarLivePolyv
public class CardPushJunitTest extends AbstractJUnit4ServiceAction {

    private PolyvChannelClient channelClient(){
        return PolyvClientBuilder.standardChannelClient()
                .withAppId("g45udahsa1")
                .withUserId("b96feb067c")
                .withAppSecret("e35b61b07dfa43b393211a255d2337bf")
                .withChannelId("2715709")
                .build();
    }

    /**
     * 查询频道卡片推送
     */
    @Test
    public void list() {
        PolyvChannelCardPushListRequester requester = channelClient().cardPushListRequest();
        PolyvApiResult<List<PolyvCardPushInfo>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 创建频道卡片推送
     */
    @Test
    public void create() {
        PolyvChannelCardPushCreateRequester requester = channelClient().createCardPushRequest();
        requester.parameter().withImageType(CardImageType.REDPACK)
                .withTitle("222")
                .withLink("http://www.baidu.com")
                .withDuration(0)
                .withShowCondition(CardShowCondition.PUSH)
                .withEnterEnabled(EnableSetting.Y);
        PolyvApiResult<PolyvCardPushInfo> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道卡片推送
     */
    @Test
    public void update() {
        PolyvChannelCardPushUpdateRequester requester = channelClient().updateCardPushRequest();
        requester.parameter().withCardPushId(1642L)
                .withImageType(CardImageType.REDPACK)
                .withTitle("222333");
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 删除卡片推送
     */
    @Test
    public void delete() {
        PolyvChannelCardPushDeleteRequester requester = channelClient().deleteCardPushRequest();
        requester.parameter().withCardPushId(1642L);
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 推送频道卡片
     */
    @Test
    public void push() {
        PolyvChannelCardPushPushRequester requester = channelClient().pushCardPushRequest();
        requester.parameter().withCardPushId(1642L);
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 取消推送卡片
     */
    @Test
    public void cancel() {
        PolyvChannelCardPushCancelRequester requester = channelClient().cancelCardPushRequest();
        requester.parameter().withCardPushId(1642L);
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

}
