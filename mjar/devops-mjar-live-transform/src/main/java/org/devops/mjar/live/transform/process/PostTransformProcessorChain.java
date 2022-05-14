package org.devops.mjar.live.transform.process;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GENSEN
 * @date 2021/7/2 16:18
 * @description：后置调度
 */
public class PostTransformProcessorChain extends TransformProcessorChain {

    /**
     * @param request
     * @param response
     */
    @Override
    public void doProcessing(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Call the next processor if there is one
        if (currentPos() < n) {
            ProcessorConfig processorConfig = processors[nextPos()];

            TransformProcessor processor = processorConfig.processor;

            // 链式调用，直到 pos < n = false
            processor.processing(request, response, this);
        }
    }
}
