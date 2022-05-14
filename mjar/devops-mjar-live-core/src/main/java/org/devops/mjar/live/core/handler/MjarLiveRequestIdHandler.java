package org.devops.mjar.live.core.handler;

/**
 *
 * @author GENSEN
 */
public class MjarLiveRequestIdHandler {
    private InheritableThreadLocal<String> handle = new InheritableThreadLocal<>();

    public String get() {
        return handle.get();
    }

    public void remove() {
        handle.remove();
    }

    public void set(String requestId) {
        handle.set(requestId);
    }
}