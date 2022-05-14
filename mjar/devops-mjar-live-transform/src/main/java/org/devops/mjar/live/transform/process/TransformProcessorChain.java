package org.devops.mjar.live.transform.process;


/**
 * @author GENSEN
 * @date 2021/7/2 15:55
 * @description：抽象调度链
 */
public abstract class TransformProcessorChain implements ProcessorChain{



    // -------------------------------------------------------------- Constants
    public static final int INCREMENT = 10;

    /**
     * The int which is used to maintain the current position
     * in the processor chain.
     */
    ThreadLocal<Integer> posHandler = new ThreadLocal<>();

    /**
     * The int which gives the current number of processor in the chain.
     */
    int n = 0;

    ProcessorConfig[] processors = new ProcessorConfig[0];

    public void reuse(){
        posHandler.set(0);
    }

    /**
     * @return
     */
    int currentPos(){
        return posHandler.get();
    }

    /**
     * @return
     */
    int nextPos(){
        Integer pos = posHandler.get();
        int tmp = pos;
        tmp++;
        posHandler.set(tmp);
        return pos;
    }


    /**
     * Add a processor to the set of processors that will be executed in this chain.
     *
     * @param processorConfig The ProcessorConfig for the transform to be executed
     */
    void addProcessor(ProcessorConfig processorConfig){
        for (ProcessorConfig processor : processors) {
            if (processor == processorConfig) {
                return;
            }
        }

        if (n == processors.length) {
            ProcessorConfig[] newProcessors =
                    new ProcessorConfig[n + INCREMENT];
            System.arraycopy(processors, 0, newProcessors, 0, n);
            processors = newProcessors;
        }
        processors[n++] = processorConfig;
    }

    /**
     * 最后一个调用的 processor
     * @return
     */
    public ProcessorConfig lastProcessor(){
        if (n == 0){
            return null;
        }
        return processors[currentPos()];
    }

}
