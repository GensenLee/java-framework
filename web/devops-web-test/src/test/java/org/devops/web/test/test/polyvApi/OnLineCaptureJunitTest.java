package org.devops.web.test.test.polyvApi;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelSearchCaptureRequester;
import org.junit.Test;

/**
 * 直播截图
 */
@EnableMjarLivePolyv
public class OnLineCaptureJunitTest extends AbstractJUnit4ServiceAction {

    private PolyvChannelClient channelClient(){
        return PolyvClientBuilder.standardChannelClient()
                .withAppId("fzu9jlakze")
                .withUserId("bf95ea5c05")
                .withAppSecret("91fc3d7946444bb4bb098bc497c52018")
                .withChannelId("2490359")
                .build();
    }

    /**
     * 查询频道直播截图
     */
    @Test
    public void searchCapture() {
        PolyvChannelSearchCaptureRequester requester = channelClient().searchCaptureRequest();
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

}
