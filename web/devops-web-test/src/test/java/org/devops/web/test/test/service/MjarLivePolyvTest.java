package org.devops.web.test.test.service;

import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvAppClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelBasicListRequester;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelBasic;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.junit.Test;

/**
 * @author GENSEN
 * @date 2021/10/15 10:51
 * @description：
 */
@EnableMjarLivePolyv
public class MjarLivePolyvTest extends AbstractJUnit4ServiceAction {

    private PolyvAppClient appClient(){
        return PolyvClientBuilder.standardAppClient()
                .withAppId("fo8pzmmfyu")
                .withUserId("57da68abfa")
                .withAppSecret("1f9aa546b7214ddf9f73bfc88deb7aad")
                .build();
    }

    @Test
    public void test(){
        PolyvChannelBasicListRequester polyvChannelBasicListRequester = appClient().channelBasicListRequest();
        PolyvApiResult<PolyvPaginator<PolyvChannelBasic>> execute = polyvChannelBasicListRequester.execute();
        System.out.println(execute.getData());
    }

}
