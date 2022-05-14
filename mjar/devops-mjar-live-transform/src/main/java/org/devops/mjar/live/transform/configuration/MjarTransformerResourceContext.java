package org.devops.mjar.live.transform.configuration;

import org.springframework.core.io.Resource;

/**
 * @author GENSEN
 * @date 2021/6/28 21:19
 * @description：资源持有
 */
public class MjarTransformerResourceContext {

    private final static ThreadLocal<Resource> handler = new ThreadLocal<>();


    public static Resource get(){
        return handler.get();
    }

    public static void remove(){
        handler.remove();
    }

    public static void set(Resource resource){
        handler.set(resource);
    }

}
