package org.devops.mjar.live.polyv.feign.client;

import org.devops.mjar.live.polyv.feign.annotation.PolyvFeignClient;
import org.devops.mjar.live.polyv.feign.annotation.PolyvGetMapping;
import org.devops.mjar.live.polyv.pojo.model.PolyvMonitor;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.model.PolyvViewLog;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppSearchChannelParameter;
import org.devops.mjar.live.polyv.pojo.param.PolyvRootMonitorListParameter;
import org.devops.mjar.live.polyv.pojo.param.PolyvRootViewlogListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelSimpleInfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author GENSEN
 * @date 2021/3/1 15:57
 * @description：总账号
 */
@PolyvFeignClient(name = "PolyvRootFeignClient")
public interface PolyvRootFeignClient {

    /**
     * 查询所有应用下的频道列表
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/app/query-list")
    PolyvApiResult<PolyvPaginator<PolyvChannelSimpleInfo>> searchChannel(@RequestParam PolyvAppSearchChannelParameter param);

    /**
     * 按天查询平台下的观看日志
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/app/statistics/viewlog")
    PolyvApiResult<List<PolyvViewLog>> getViewlogList(@RequestParam PolyvRootViewlogListParameter param);

    /**
     * 获取应用的直播监控列表
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/app/monitor/list")
    PolyvApiResult<PolyvPaginator<PolyvMonitor>> getMonitorList(@RequestParam PolyvRootMonitorListParameter param);

}
