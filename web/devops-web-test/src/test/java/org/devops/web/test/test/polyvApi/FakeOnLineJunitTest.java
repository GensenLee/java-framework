package org.devops.web.test.test.polyvApi;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelCreateDiskVideoRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelDeleteDiskVideoRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelDiskVideoListRequester;
import org.devops.mjar.live.polyv.pojo.model.PolyvDiskVideo;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.junit.Test;

/**
 * 伪直播
 */
@EnableMjarLivePolyv
public class FakeOnLineJunitTest extends AbstractJUnit4ServiceAction {

    private PolyvChannelClient channelClient(){
        return PolyvClientBuilder.standardChannelClient()
                .withAppId("fzu9jlakze")
                .withUserId("bf95ea5c05")
                .withAppSecret("91fc3d7946444bb4bb098bc497c52018")
                .withChannelId("2490359")
                .build();
    }

    /**
     * 设置伪直播
     */
    @Test
    public void createDisk() {
        PolyvChannelCreateDiskVideoRequester requester = channelClient().createDiskVideoRequest();
        requester.parameter().withVids("bf95ea5c057455ab29972552ea0ca416_b").withStartTimes("1627564182000");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 删除伪直播
     */
    @Test
    public void deleteDisk() {
        PolyvChannelDeleteDiskVideoRequester requester = channelClient().deleteDiskVideoRequest();
        requester.parameter().withVids("bf95ea5c057455ab29972552ea0ca416");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 分页获取伪直播
     */
    @Test
    public void diskVideoList() {
        PolyvChannelDiskVideoListRequester requester = channelClient().diskVideoListRequest();
        PolyvApiResult<PolyvPaginator<PolyvDiskVideo>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

}
