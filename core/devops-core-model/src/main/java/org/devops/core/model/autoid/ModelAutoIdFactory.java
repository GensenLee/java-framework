package org.devops.core.model.autoid;

import java.util.HashMap;
import java.util.Map;

import org.devops.core.model.emun.ModelAutoIdType;

/**
 * @author GENSEN
 * @date 2021/9/15 17:00
 * @description：id工厂
 */
public class ModelAutoIdFactory {

    private static final Map<String, AutoIdGenerator<?>> table_generator_cache = new HashMap<>();

    /**
     * @param modelAutoIdType
     * @param key
     * @return
     */
    public static synchronized AutoIdGenerator<?> createAutoIdGenerator(ModelAutoIdType modelAutoIdType, String key){
    	AutoIdGenerator<?> instance = table_generator_cache.get(key);
    	if(instance != null) {
    		return instance;
    	}
        instance = modelAutoIdType.createInstance(key);
        table_generator_cache.put(key, instance);
        return instance;
    }

}
