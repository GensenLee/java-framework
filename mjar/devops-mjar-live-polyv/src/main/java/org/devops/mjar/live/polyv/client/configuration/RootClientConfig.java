package org.devops.mjar.live.polyv.client.configuration;

import lombok.Data;
import org.devops.core.utils.util.StringUtil;
import org.devops.mjar.live.core.processor.ProcessorAttributes;
import org.devops.mjar.live.core.processor.ProcessorConfig;
import org.devops.mjar.live.core.processor.ProcessorType;
import org.devops.mjar.live.core.sign.LiveApiProfiles;

/**
 * @author GENSEN
 * @date 2020/10/28 18:39
 * @description：配置
 */
@Data
@ProcessorAttributes(clientType = ProcessorType.Root)
public class RootClientConfig extends ProcessorConfig {

    public RootClientConfig() {
    }

    public RootClientConfig(String polyvAppId, String appSecret) {
        this.polyvAppId = polyvAppId;
        this.appSecret = appSecret;
    }

    /**
     * appId
     */
    private String polyvAppId;
    /**
     * appSecret
     */
    private String appSecret;

    @Override
    public LiveApiProfiles createProfiles() {
        profiles = new LiveApiProfiles();
        profiles.setAppId(polyvAppId);
        profiles.setAppSecret(appSecret);
        return profiles;
    }

    @Override
    public boolean doCheck() {
        if (StringUtil.isEmpty(polyvAppId) || StringUtil.isEmpty(appSecret)) {
            return false;
        }
        return true;
    }


    public static class ClientConfigurationBuilder {
        private RootClientConfig rootClientConfiguration;

        protected ClientConfigurationBuilder() {
            rootClientConfiguration = new RootClientConfig();
        }

        public static ClientConfigurationBuilder builder() {
            return new ClientConfigurationBuilder();
        }

        public ClientConfigurationBuilder polyvAppId(String polyvAppId) {
            rootClientConfiguration.setPolyvAppId(polyvAppId);
            return this;
        }

        public ClientConfigurationBuilder appSecret(String appSecret) {
            rootClientConfiguration.setAppSecret(appSecret);
            return this;
        }

        public RootClientConfig build() {
            return rootClientConfiguration;
        }
    }
}
