package org.devops.mjar.live.transform.process;

/**
 * @author GENSEN
 * @date 2021/7/8 17:49
 * @description：可返回结果的
 */
public abstract class ReturnableTransformProcessor implements TransformProcessor {

    private final ThreadLocal<Object> processorResultHandler = new ThreadLocal<>();

    protected void setResult(Object result){
        processorResultHandler.set(result);
    }

    public Object getResult(){
        return processorResultHandler.get();
    }

    void removeResult(){
        processorResultHandler.remove();
    }

}
