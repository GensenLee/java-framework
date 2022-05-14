package org.devops.mjar.live.core.processor;

import org.devops.mjar.live.core.sign.LiveApiProfiles;
import lombok.Getter;

/**
 * @author GENSEN
 * @date 2020/10/31 11:01
 * @description：client配置
 */
public abstract class ProcessorConfig {

    /**
     * 配置
     */
    @Getter
    protected LiveApiProfiles profiles;

    /**
     * 配置覆盖
     * @param profiles
     */
    void setProfiles(LiveApiProfiles profiles) {
        this.profiles = profiles;
    }

    /**
     * 检查配置
     * @return
     */
    public abstract boolean doCheck();

    /**
     * 构建配置
     * @return
     */
    public abstract LiveApiProfiles createProfiles();
}
