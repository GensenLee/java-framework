package org.devops.mjar.live.transform.process;


import org.devops.mjar.live.core.servlet.CommonRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GENSEN
 * @date 2021/8/26 16:01
 * @description：增强执行
 */
public class ProcessPerformer {

    private HttpServletRequest request;

    private HttpServletResponse response;

    public ProcessPerformer(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public Object getProcessData(){
        return ((CommonRequestWrapper)request).getConvertedData();
    }

    public void updateProcessData(Object data){
        ((CommonRequestWrapper)request).setConvertedData(data);
    }

    /**
     * 执行过程
     * @param processorChain
     * @throws Exception
     */
    public void process(TransformProcessorChain processorChain) throws Exception {
        if (processorChain == null) {
            return;
        }
        Object args = doProcessChain(processorChain);
        ((CommonRequestWrapper)request).setConvertedData(args);
    }

    /**
     * @param transformProcessorChain
     * @return
     * @throws Exception
     */
    private Object doProcessChain(TransformProcessorChain transformProcessorChain) throws Exception {
        Object result = null;
        if (transformProcessorChain != null) {
            transformProcessorChain.reuse();
            transformProcessorChain.doProcessing(request, response);
            result = ((CommonRequestWrapper)request).getConvertedData();
            ProcessorConfig processorConfig = transformProcessorChain.lastProcessor();
            if (processorConfig == null) {
                return result;
            }
            TransformProcessor processor = processorConfig.getProcessor();
            if (processor instanceof ReturnableTransformProcessor) {
                Object data = ((ReturnableTransformProcessor) processor).getResult();
                if (data != null) {
                    result = data;
                }
            }
        }
        return result;
    }

}
