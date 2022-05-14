package org.devops.mjar.live.transform.transformer;

import org.devops.mjar.live.transform.process.PostTransformProcessorChain;
import org.devops.mjar.live.transform.process.PreTransformProcessorChain;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author GENSEN
 * @date 2021/7/2 16:58
 * @description：Transformer转化配置
 */
@Setter(AccessLevel.PACKAGE)
@Getter
public class TransformerConfig {
    /**
     * 实现的或xml配置的转化
     */
    private Transformer transformer;

    /**
     * 前置增强链
     */
    private PreTransformProcessorChain preTransformProcessorChain;

    /**
     * 后置增强链
     */
    private PostTransformProcessorChain postTransformProcessorChain;

    public TransformerConfig(Transformer transformer, PreTransformProcessorChain preTransformProcessorChain, PostTransformProcessorChain postTransformProcessorChain) {
        this.transformer = transformer;
        this.preTransformProcessorChain = preTransformProcessorChain;
        this.postTransformProcessorChain = postTransformProcessorChain;
    }
}
