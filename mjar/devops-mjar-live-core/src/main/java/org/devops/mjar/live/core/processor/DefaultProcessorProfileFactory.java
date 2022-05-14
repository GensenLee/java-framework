package org.devops.mjar.live.core.processor;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.StringUtil;
import org.devops.mjar.live.core.exception.LiveApiRuntimeException;
import org.devops.mjar.live.core.sign.LiveApiProfiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/3/19 15:06
 * @description：默认配置工厂
 */
@Component
public class DefaultProcessorProfileFactory {

    private static final Logger logger = LoggerFactory.getLogger(DefaultProcessorProfileFactory.class);


    @Autowired
    private Environment environment;

    private LiveApiProfiles resolve(ProcessorAttributes annotation) {
        ProcessorType processorType = annotation.clientType();
        Map<String, Object> map = new HashMap<>();
        for (String key : processorType.getKeys()) {
            String value = environment.getProperty(annotation.clientType().getPrefix() + key);
            map.put(key, value);
        }
        LiveApiProfiles property = FastJsonUtils.getBean(FastJsonUtils.toJsonString(map), LiveApiProfiles.class);

        if (property == null) {
            throw new LiveApiRuntimeException("default profile not found");
        }
        if ((StringUtil.isEmpty(property.getAppId()) || StringUtil.isEmpty(property.getAppSecret()))
                || (ProcessorType.Root != annotation.clientType() && StringUtil.isEmpty(property.getUserId()))) {
            throw new LiveApiRuntimeException("incomplete default profile");
        }
        return property;
    }

    /**
     * {@link ProcessorConfig#doCheck} 检查不通过时，使用默认配置
     *
     * @param config
     * @param processor
     */
    public void resolve(ProcessorConfig config, Processor processor) {
        ProcessorAttributes annotation = AnnotationUtils.findAnnotation(processor.getClass(), ProcessorAttributes.class);
        LiveApiProfiles customProfiles = config.createProfiles();
        if (!config.doCheck()) {
            // 使用默认配置
            logger.info("default {} client profiles active", annotation.clientType().name());
            LiveApiProfiles profiles = resolve(annotation);
            config.setProfiles(profiles);
            if (StringUtil.isEmpty(profiles.getChannelId())) {
                profiles.setChannelId(customProfiles.getChannelId());
            }
        }
    }


}
