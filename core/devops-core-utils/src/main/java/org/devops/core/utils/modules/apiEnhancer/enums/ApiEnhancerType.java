package org.devops.core.utils.modules.apiEnhancer.enums;

public enum ApiEnhancerType {

	/**
	 * 幂等
	 */
	IDEMPOTENT,
	/**
	 * 串行
	 */
	SERIAL,
	/**
	 * 乐观创建
	 */
	CREATE,
	/**
	 * 接口缓存
	 */
	CACHEABLE,
	/**
	 * API操作间隔
	 */
	API_INTERVAL,
	/**
	 * 内存缓存,重启失效
	 */
	MEMORY;

}
