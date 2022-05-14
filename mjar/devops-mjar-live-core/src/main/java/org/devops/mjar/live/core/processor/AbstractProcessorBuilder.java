package org.devops.mjar.live.core.processor;

import org.devops.mjar.live.core.exception.LiveApiRuntimeException;

/**
 * @author GENSEN
 * @date 2020/10/31 10:49
 * @description：处理器构造器
 */
public abstract class AbstractProcessorBuilder<T> {

    protected DefaultProcessorProfileFactory clientProfileFactory;

    public AbstractProcessorBuilder(DefaultProcessorProfileFactory clientProfileFactory) {
        this.clientProfileFactory = clientProfileFactory;
    }

    /**
     * @return
     */
    public abstract T build();

    /**
     * @param config
     * @return
     */
    public abstract T buildWithConfig(ProcessorConfig config);

    protected void check(T object){
        if (object == null) {
            throw new LiveApiRuntimeException("spring context is not ready");
        }
    }
}
