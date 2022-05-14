package org.devops.core.utils.modules.apiEnhancer.data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApiEnhancerDataMemory implements IApiEnhancerData {

	private static ApiEnhancerDataMemory instance;
	
	private Map<String,ApiEnhancerDataMemoryVO> mapData = new ConcurrentHashMap<>();
	
	private ApiEnhancerDataMemory() {
		
	}
	
	static {
		instance = new ApiEnhancerDataMemory();
	}
	
	public static ApiEnhancerDataMemory getInstance() {
		return instance;
	}

	@Override
	public String get(String key) {
		ApiEnhancerDataMemoryVO apiEnhancerDataMemoryVO = mapData.get(key);
		if(apiEnhancerDataMemoryVO == null) {
			return "";
		}
		if(apiEnhancerDataMemoryVO.isExpired()) {
			mapData.remove(key);
			return "";
		}
		return apiEnhancerDataMemoryVO.getValue();
	}

	@Override
	public void set(String key, long expire, String value) {
		ApiEnhancerDataMemoryVO apiEnhancerDataMemoryVO = new ApiEnhancerDataMemoryVO();
		apiEnhancerDataMemoryVO.setValue(value);
		if(expire > 0) {
			apiEnhancerDataMemoryVO.setExpire(System.currentTimeMillis() + expire);
		}
		
		mapData.put(key, apiEnhancerDataMemoryVO);
		
	}
}
