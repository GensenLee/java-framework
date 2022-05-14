package org.devops.mjar.live.core.handler;


import org.devops.mjar.live.core.sign.LiveApiProfiles;

/**
 * @author GENSEN
 * @date 2021/3/12 17:43
 * @description：上下文对象
 */
public class MjarLiveProfileHandler {

    private ThreadLocal<LiveApiProfiles> handle = new ThreadLocal<>();

    public void set(LiveApiProfiles profile) {
        handle.set(profile);
    }

    public LiveApiProfiles get() {
        return handle.get();
    }

    public void remove() {
        handle.remove();
    }
}
