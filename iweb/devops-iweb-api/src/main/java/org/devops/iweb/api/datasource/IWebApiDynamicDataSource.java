package org.devops.iweb.api.datasource;

public class IWebApiDynamicDataSource {

	private static String dataSource;
	
	public static void setDataSource(String dataSource){
		IWebApiDynamicDataSource.dataSource = dataSource;
	}

	public static String getDataSource(){
		return IWebApiDynamicDataSource.dataSource;
	}
}
