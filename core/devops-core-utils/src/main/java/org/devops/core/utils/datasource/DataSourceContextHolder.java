package org.devops.core.utils.datasource;

import java.util.HashMap;
import java.util.Map;

public class DataSourceContextHolder {

	private static final Map<String,ThreadLocal<String>> mapThreadLocal = new HashMap<>();
	
	public synchronized static void initThreadLocal(String dataSourceName) {
		mapThreadLocal.put(dataSourceName, new ThreadLocal<String>());
	}
	
	public static void setDataSourceType(String dataSourceName, String type){
		
		ThreadLocal<String> threadLocal = mapThreadLocal.get(dataSourceName);
		if(threadLocal == null) {
			return;
		}
		if(type == null || "".equals(type.trim())){
			threadLocal.remove();
			return;
		}
		threadLocal.set(type);
	}
	
	public static String getDataSourceType(String dataSourceName){
		ThreadLocal<String> threadLocal = mapThreadLocal.get(dataSourceName);
		if(threadLocal == null) {
			return null;
		}
		return threadLocal.get();
	}
}
