package org.devops.mjar.live.core.handler;

import lombok.Getter;

/**
 * @author GENSEN
 * @date 2021/6/25 15:29
 * @description：执行过程上下文
 */
@Getter
public class MjarLiveContext {
    private static final MjarLiveContext context;
    static {
        context = new MjarLiveContext();
    }

    private final MjarLiveFeignRequestTemplateHandler mjarLiveFeignRequestTemplateHandler;

    private final MjarLiveFeignResponseHandler mjarLiveFeignResponseHandler;

    private final MjarLiveProfileHandler mjarLiveProfileHandler;

    private final MjarLiveRequestIdHandler mjarLiveRequestIdHandler;

    public MjarLiveContext() {
        this.mjarLiveFeignRequestTemplateHandler = new MjarLiveFeignRequestTemplateHandler();
        this.mjarLiveFeignResponseHandler = new MjarLiveFeignResponseHandler();
        this.mjarLiveProfileHandler = new MjarLiveProfileHandler();
        this.mjarLiveRequestIdHandler = new MjarLiveRequestIdHandler();
    }

    /**
     * 清除线程handler
     */
    public void clean(){
        context.mjarLiveRequestIdHandler.remove();
        context.mjarLiveProfileHandler.remove();
        context.mjarLiveFeignResponseHandler.remove();
        context.mjarLiveFeignRequestTemplateHandler.remove();;
    }

    public static MjarLiveContext getContext(){
        return context;
    }
}
