package org.devops.mjar.live.polyv.client;

import org.devops.mjar.live.polyv.feign.client.PolyvAppFeignClient;
import org.devops.mjar.live.polyv.feign.client.PolyvRootFeignClient;
import org.devops.mjar.live.polyv.operator.requester.PolyvAppCreateRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvAppSearchChannelRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvRootMonitorListRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvRootViewlogListRequester;
import org.devops.mjar.live.core.processor.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author GENSEN
 * @date 2021/3/1 15:58
 * @description：总账户
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Scope("prototype")
public class PolyvRootClient extends Processor {

    @Autowired
    private PolyvRootFeignClient rootFeignClient;

    @Autowired
    private PolyvAppFeignClient appFeignClient;


    /**
     * 创建应用
     *
     * @return
     */
    public PolyvAppCreateRequester createAppRequest() {
        return new PolyvAppCreateRequester(appFeignClient::create);
    }

    public PolyvAppSearchChannelRequester searchChannelRequest() {
        return new PolyvAppSearchChannelRequester(rootFeignClient::searchChannel);
    }

    /**
     * 查询所有日志
     *
     * @return
     */
    public PolyvRootViewlogListRequester viewlogListRequest() {
        return new PolyvRootViewlogListRequester(rootFeignClient::getViewlogList);
    }

    /**
     * 直播监控列表
     *
     * @return
     */
    public PolyvRootMonitorListRequester monitorListRequest() {
        return new PolyvRootMonitorListRequester(rootFeignClient::getMonitorList);
    }

}
