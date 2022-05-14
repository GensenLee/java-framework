package org.devops.mjar.live.core.handler;

import org.devops.core.utils.util.StringUtil;
import feign.Response;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author GENSEN
 * @date 2021/3/6 16:52
 * @description：feign响应持有
 */
public class MjarLiveFeignResponseHandler {

    private Map<String, Response> handle = new ConcurrentHashMap<>(16);

    public void handle(Response response) {
        String requestId = MjarLiveContext.getContext().getMjarLiveRequestIdHandler().get();
        if (StringUtil.isEmpty(requestId)) {
            return;
        }
        handle.put(requestId, response);
    }

    public Response get() {
        return handle.get(MjarLiveContext.getContext().getMjarLiveRequestIdHandler().get());
    }

    public void remove() {
        String id = MjarLiveContext.getContext().getMjarLiveRequestIdHandler().get();
        if (StringUtil.isEmpty(id)) {
            return;
        }
        handle.remove(id);
    }

}
