package org.devops.web.test.test.polyvApi;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelUpdateAuthUrlRequester;
import org.junit.Test;

/**
 * 播放器
 */
@EnableMjarLivePolyv
public class VideoPlayerJunitTest  extends AbstractJUnit4ServiceAction {

    private PolyvChannelClient channelClient(){
        return PolyvClientBuilder.standardChannelClient()
                .withAppId("fzu9jlakze")
                .withUserId("bf95ea5c05")
                .withAppSecret("91fc3d7946444bb4bb098bc497c52018")
                .withChannelId("2490359")
                .build();
    }

    /**
     * 修改设置授权认证URL
     */
    @Test
    public void updateAuth() {
        PolyvChannelUpdateAuthUrlRequester requester = channelClient().updateAuthUrlRequest();
        requester.parameter().withUrl("www.baidu.com");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }


}
