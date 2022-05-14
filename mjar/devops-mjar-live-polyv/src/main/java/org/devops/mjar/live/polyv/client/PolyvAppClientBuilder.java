package org.devops.mjar.live.polyv.client;

import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.mjar.live.polyv.client.configuration.AppClientConfig;
import org.devops.mjar.live.core.processor.AbstractProcessorBuilder;
import org.devops.mjar.live.core.processor.DefaultProcessorProfileFactory;
import org.devops.mjar.live.core.processor.ProcessorConfig;

/**
 * @author GENSEN
 * @date 2020/10/26 15:56
 * @description：APP客户端构造器
 */
public class PolyvAppClientBuilder extends AbstractProcessorBuilder<PolyvAppClient> {

    private PolyvAppClientBuilder() {
        super(SpringContextUtil.getBean(DefaultProcessorProfileFactory.class));
        configurationBuilder = AppClientConfig.ClientConfigurationBuilder.builder();
    }

    /**
     * 配置构造器
     */
    private AppClientConfig.ClientConfigurationBuilder configurationBuilder;

    /**
     * @return
     */
    static PolyvAppClientBuilder standard(){
        return new PolyvAppClientBuilder();
    }

    public PolyvAppClientBuilder withAppId(String appId) {
        this.configurationBuilder.polyvAppId(appId);
        return this;
    }

    public PolyvAppClientBuilder withAppSecret(String appSecret) {
        this.configurationBuilder.appSecret(appSecret);
        return this;
    }

    public PolyvAppClientBuilder withUserId(String userId) {
        this.configurationBuilder.appUserId(userId);
        return this;
    }

    @Override
    public PolyvAppClient build() {
        PolyvAppClient client = SpringContextUtil.getBean(PolyvAppClient.class);
        check(client);
        AppClientConfig configuration = configurationBuilder.build();
        clientProfileFactory.resolve(configuration, client);
        client.setConfiguration(configuration);
        return client;
    }

    @Override
    public PolyvAppClient buildWithConfig(ProcessorConfig configuration) {
        configuration.doCheck();
        PolyvAppClient client = SpringContextUtil.getBean(PolyvAppClient.class);
        check(client);
        client.setConfiguration(configuration);
        return client;
    }
}
