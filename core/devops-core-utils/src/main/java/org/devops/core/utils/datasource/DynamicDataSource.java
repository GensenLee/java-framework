package org.devops.core.utils.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{

	private String dataSourceName;
	
	public DynamicDataSource(String dataSourceName) {
		this.dataSourceName = dataSourceName;
		DataSourceContextHolder.initThreadLocal(dataSourceName);
		DataSourceAspect.initThreadLocal(dataSourceName);
	}
	
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceType(dataSourceName);
	}
}
