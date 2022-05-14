package org.devops.mjar.live.polyv.client;

import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.mjar.live.polyv.client.configuration.ChannelClientConfig;
import org.devops.mjar.live.core.processor.AbstractProcessorBuilder;
import org.devops.mjar.live.core.processor.DefaultProcessorProfileFactory;
import org.devops.mjar.live.core.processor.ProcessorConfig;

/**
 * @author GENSEN
 * @date 2020/10/26 15:56
 * @description：CLIENT客户端构造器
 */
public class PolyvChannelClientBuilder extends AbstractProcessorBuilder<PolyvChannelClient> {

    private PolyvChannelClientBuilder() {
        super(SpringContextUtil.getBean(DefaultProcessorProfileFactory.class));
        configurationBuilder = ChannelClientConfig.ClientConfigurationBuilder.builder();
    }

    /**
     * 配置构造器
     */
    private ChannelClientConfig.ClientConfigurationBuilder configurationBuilder;

    /**
     * @return
     */
    static PolyvChannelClientBuilder standard(){
        return new PolyvChannelClientBuilder();
    }

    public PolyvChannelClientBuilder withAppId(String appId) {
        this.configurationBuilder.polyvAppId(appId);
        return this;
    }

    public PolyvChannelClientBuilder withAppSecret(String appSecret) {
        this.configurationBuilder.appSecret(appSecret);
        return this;
    }

    public PolyvChannelClientBuilder withUserId(String userId) {
        this.configurationBuilder.appUserId(userId);
        return this;
    }

    public PolyvChannelClientBuilder withChannelId(String channelId) {
        this.configurationBuilder.channelId(channelId);
        return this;
    }

    @Override
    public PolyvChannelClient build() {
        PolyvChannelClient client = SpringContextUtil.getBean(PolyvChannelClient.class);
        ChannelClientConfig configuration = configurationBuilder.build();
        clientProfileFactory.resolve(configuration, client);
        client.setConfiguration(configuration);
        return client;
    }

    @Override
    public PolyvChannelClient buildWithConfig(ProcessorConfig configuration) {
        configuration.doCheck();
        PolyvChannelClient client = SpringContextUtil.getBean(PolyvChannelClient.class);
        client.setConfiguration(configuration);
        return client;
    }

}
