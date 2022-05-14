package org.devops.mjar.live.transform.process;

/**
 * @author GENSEN
 * @date 2021/7/2 15:46
 * @description：
 */
public class ProcessorConfig {

    TransformProcessor processor;

    // TODO: 2021/7/2 可增强配置

    public ProcessorConfig(TransformProcessor processor) {
        this.processor = processor;
    }

    public TransformProcessor getProcessor() {
        return processor;
    }
}
