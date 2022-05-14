package org.devops.core.utils.modules.apiEnhancer.data;

public interface IApiEnhancerData {

	public String get(String key);

	public void set(String key, long expire, String value);
}
