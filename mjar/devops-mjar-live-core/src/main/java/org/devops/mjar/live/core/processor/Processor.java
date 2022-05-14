package org.devops.mjar.live.core.processor;

import org.devops.mjar.live.core.sign.LiveApiProfiles;

/**
 * @author GENSEN
 * @date 2020/10/23 15:53
 * @description：客户端
 */
public abstract class Processor {

    protected Processor() {
    }

    /**
     * configuration
     */
    private ProcessorConfig configuration;

    /**
     * @param configuration
     */
    public void setConfiguration(ProcessorConfig configuration){
        if (configuration.getProfiles() == null) {
            configuration.createProfiles();
        }
        this.configuration = configuration;
    }

    public LiveApiProfiles profile(){
        return configuration.getProfiles();
    }
}
