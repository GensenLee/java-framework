package org.devops.web.test.test.polyvApi;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvAppClient;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.operator.requester.PolyvAppUpdateSsoTokenRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelUpdateAccountTokenRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelUpdateTokenRequester;
import org.junit.Test;

/**
 * 直播服务授权
 */
@EnableMjarLivePolyv
public class OnLineTokenLoginJunitTest extends AbstractJUnit4ServiceAction {

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
     * 设置账号单点登录token
     */
    @Test
    public void userLogin() {
        PolyvAppUpdateSsoTokenRequester requester = appClient().updateSsoTokenRequest();
        requester.parameter()
                .withToken("123test");
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }
    /**
     * 设置频道单点登录token
     */
    @Test
    public void channelLogin() {
        PolyvChannelUpdateTokenRequester requester = channelClient().updateTokenRequest();
        requester.parameter()
                .withToken("123test");
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 设置子频道单点登录token
     */
    @Test
    public void accountLogin() {
        PolyvChannelUpdateAccountTokenRequester requester = channelClient().updateAccountTokenRequest();
        requester.parameter()
                .withAccountId("0042419208")
                .withToken("123test");
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

}
