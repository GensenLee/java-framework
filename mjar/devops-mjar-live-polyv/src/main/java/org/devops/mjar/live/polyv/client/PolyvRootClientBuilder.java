package org.devops.mjar.live.polyv.client;

import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.mjar.live.polyv.client.configuration.RootClientConfig;
import org.devops.mjar.live.core.processor.AbstractProcessorBuilder;
import org.devops.mjar.live.core.processor.DefaultProcessorProfileFactory;
import org.devops.mjar.live.core.processor.ProcessorConfig;

/**
 * @author GENSEN
 * @date 2020/10/26 15:56
 * @description：ROOT客户端构造器
 */
public class PolyvRootClientBuilder extends AbstractProcessorBuilder<PolyvRootClient> {

    private PolyvRootClientBuilder() {
        super(SpringContextUtil.getBean(DefaultProcessorProfileFactory.class));
        configurationBuilder = RootClientConfig.ClientConfigurationBuilder.builder();
    }

    /**
     * 配置构造器
     */
    private RootClientConfig.ClientConfigurationBuilder configurationBuilder;

    /**
     * @return
     */
    static PolyvRootClientBuilder standard(){
        return new PolyvRootClientBuilder();
    }

    public PolyvRootClientBuilder withAppId(String appId) {
        this.configurationBuilder.polyvAppId(appId);
        return this;
    }

    public PolyvRootClientBuilder withAppSecret(String appSecret) {
        this.configurationBuilder.appSecret(appSecret);
        return this;
    }

    @Override
    public PolyvRootClient build() {
        PolyvRootClient client = SpringContextUtil.getBean(PolyvRootClient.class);
        check(client);
        RootClientConfig configuration = configurationBuilder.build();
        clientProfileFactory.resolve(configuration, client);
        client.setConfiguration(configuration);
        return client;
    }

    @Override
    public PolyvRootClient buildWithConfig(ProcessorConfig configuration) {
        configuration.doCheck();
        PolyvRootClient client = SpringContextUtil.getBean(PolyvRootClient.class);
        check(client);
        client.setConfiguration(configuration);
        return client;
    }
}
