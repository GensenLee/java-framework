package org.devops.mjar.live.core.handler;

import org.devops.core.utils.util.StringUtil;
import feign.RequestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author GENSEN
 * @date 2021/3/6 16:52
 * @description：feign请求持有
 */
public class MjarLiveFeignRequestTemplateHandler {

    private Map<String, RequestTemplate> handle = new ConcurrentHashMap<>(16);

    public void handle(RequestTemplate requestTemplate) {
        String requestId = MjarLiveContext.getContext().getMjarLiveRequestIdHandler().get();
        if (StringUtil.isEmpty(requestId)) {
            return;
        }
        handle.put(requestId, requestTemplate);
    }

    public RequestTemplate get() {
        String id = MjarLiveContext.getContext().getMjarLiveRequestIdHandler().get();
        if (StringUtil.isEmpty(id)) {
            return null;
        }
        return handle.get(id);
    }

    public void remove() {
        String id = MjarLiveContext.getContext().getMjarLiveRequestIdHandler().get();
        if (StringUtil.isEmpty(id)) {
            return;
        }
        handle.remove(id);
    }

}
