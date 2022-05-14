package org.devops.mjar.live.polyv.client.configuration;

import lombok.Data;
import org.devops.core.utils.util.StringUtil;
import org.devops.mjar.live.core.processor.ProcessorAttributes;
import org.devops.mjar.live.core.processor.ProcessorType;
import org.devops.mjar.live.core.sign.LiveApiProfiles;

/**
 * @author GENSEN
 * @date 2020/10/28 18:39
 * @description：配置
 */
@Data
@ProcessorAttributes(clientType = ProcessorType.App)
public class AppClientConfig extends RootClientConfig {

    public AppClientConfig() {
    }

    public AppClientConfig(String polyvAppId, String appSecret, String appUserId) {
        super(polyvAppId, appSecret);
        this.appUserId = appUserId;
    }

    /**
     * userId
     */
    private String appUserId;

    @Override
    public LiveApiProfiles createProfiles(){
        LiveApiProfiles liveApiProfiles = super.createProfiles();
        liveApiProfiles.setUserId(this.appUserId);
        return liveApiProfiles;
    }

    @Override
    public boolean doCheck(){
        return super.doCheck() && StringUtil.isNotEmpty(appUserId);
    }


    public static class ClientConfigurationBuilder {
        private AppClientConfig appClientConfiguration;

        protected ClientConfigurationBuilder() {
            appClientConfiguration = new AppClientConfig();
        }

        public static ClientConfigurationBuilder builder() {
            return new ClientConfigurationBuilder();
        }

        public ClientConfigurationBuilder polyvAppId(String polyvAppId) {
            appClientConfiguration.setPolyvAppId(polyvAppId);
            return this;
        }

        public ClientConfigurationBuilder appSecret(String appSecret) {
            appClientConfiguration.setAppSecret(appSecret);
            return this;
        }

        public ClientConfigurationBuilder appUserId(String appUserId) {
            appClientConfiguration.setAppUserId(appUserId);
            return this;
        }

        public AppClientConfig build() {

            return appClientConfiguration;
        }
    }
}
