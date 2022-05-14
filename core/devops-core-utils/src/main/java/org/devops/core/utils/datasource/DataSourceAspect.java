package org.devops.core.utils.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Order(-10)
@Slf4j
public class DataSourceAspect {

	private static Map<String,ThreadLocal<Map<String,String>>> mapContext = new HashMap<>();
	
	public synchronized static void initThreadLocal(String dataSource) {
		mapContext.put(dataSource, new ThreadLocal<Map<String,String>>());
	}
	
	@Before("@annotation(dataSource)")
    public void changeDataSource(JoinPoint point, DataSource dataSource) throws Throwable {
		
		String oldDataSourceType = DataSourceContextHolder.getDataSourceType(dataSource.name());
		Map<String,String> mapDataSourceType = mapContext.get(dataSource.name()).get();
		if(mapDataSourceType == null){
			mapDataSourceType = new HashMap<String,String>();
			mapContext.get(dataSource.name()).set(mapDataSourceType);
		}
		mapDataSourceType.put(point.getSignature().toString(), oldDataSourceType);
		
		String dataSourceType = dataSource.value();
		log.info("[DataSourceAspect.changeDataSource] 方法:{},选择数据源:{}",point.getSignature(),dataSourceType);
		DataSourceContextHolder.setDataSourceType(dataSource.name(),dataSourceType);
    }

    @After("@annotation(dataSource)")
    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
    	Map<String,String> mapDataSourceType = mapContext.get(dataSource.name()).get();
    	String oldDataSourceType = null;
    	if(mapDataSourceType != null){
    		oldDataSourceType = mapDataSourceType.get(point.getSignature().toString());
    		mapDataSourceType.remove(point.getSignature().toString());
    	}
    	log.info("[DataSourceAspect.restoreDataSource] 方法:{},恢复数据源:{}",point.getSignature(),oldDataSourceType);
        DataSourceContextHolder.setDataSourceType(dataSource.name(),oldDataSourceType);
    }
}
