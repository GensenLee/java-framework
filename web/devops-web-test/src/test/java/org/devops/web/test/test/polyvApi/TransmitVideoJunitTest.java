package org.devops.web.test.test.polyvApi;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelSearchTransmitRequester;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelTransmit;
import org.junit.Test;

import java.util.List;

/**
 * 转播
 */
@EnableMjarLivePolyv
public class TransmitVideoJunitTest extends AbstractJUnit4ServiceAction {

    private PolyvChannelClient channelClient(){
        return PolyvClientBuilder.standardChannelClient()
                .withAppId("fzu9jlakze")
                .withUserId("bf95ea5c05")
                .withAppSecret("91fc3d7946444bb4bb098bc497c52018")
                .withChannelId("2490359")
                .build();
    }

    /**
     * 查询转播频道信息
     */
    @Test
    public void searchTransmit() {
        PolyvChannelSearchTransmitRequester requester = channelClient().searchTransmitRequest();
        PolyvApiResult<List<PolyvChannelTransmit>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

}
