package org.devops.mjar.live.polyv.client.configuration;

import org.devops.core.utils.util.StringUtil;
import lombok.Data;
import org.devops.mjar.live.core.processor.ProcessorAttributes;
import org.devops.mjar.live.core.processor.ProcessorType;
import org.devops.mjar.live.core.sign.LiveApiProfiles;

/**
 * @author GENSEN
 * @date 2020/10/28 18:39
 * @description：配置
 */
@Data
@ProcessorAttributes(clientType = ProcessorType.Channel)
public class ChannelClientConfig extends AppClientConfig {

    public ChannelClientConfig() {
    }

    public ChannelClientConfig(String channelId, String polyvAppId, String appSecret, String appUserId) {
        super(polyvAppId ,appSecret ,appUserId);
        this.channelId = channelId;
    }

    /**
     * channelId
     */
    private String channelId;


    @Override
    public boolean doCheck(){
        return super.doCheck() && StringUtil.isNotEmpty(channelId);
    }


    public static final class ClientConfigurationBuilder {
        private ChannelClientConfig channelClientConfiguration;

        private ClientConfigurationBuilder() {
            channelClientConfiguration = new ChannelClientConfig();
        }

        public static ClientConfigurationBuilder builder() {
            return new ClientConfigurationBuilder();
        }

        public ClientConfigurationBuilder channelId(String channelId){
            channelClientConfiguration.setChannelId(channelId);
            return this;
        }

        public ClientConfigurationBuilder polyvAppId(String polyvAppId) {
            channelClientConfiguration.setPolyvAppId(polyvAppId);
            return this;
        }

        public ClientConfigurationBuilder appSecret(String appSecret) {
            channelClientConfiguration.setAppSecret(appSecret);
            return this;
        }

        public ClientConfigurationBuilder appUserId(String appUserId) {
            channelClientConfiguration.setAppUserId(appUserId);
            return this;
        }

        public ChannelClientConfig build() {
            return channelClientConfiguration;
        }
    }

    @Override
    public LiveApiProfiles createProfiles() {
        LiveApiProfiles polyvProfiles = super.createProfiles();
        polyvProfiles.setChannelId(channelId);
        return polyvProfiles;
    }
}
