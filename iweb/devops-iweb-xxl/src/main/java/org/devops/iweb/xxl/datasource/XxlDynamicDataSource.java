package org.devops.iweb.xxl.datasource;

public class XxlDynamicDataSource {

	private static String dataSource;
	
	public static void setDataSource(String dataSource){
		XxlDynamicDataSource.dataSource = dataSource;
	}

	public static String getDataSource(){
		return XxlDynamicDataSource.dataSource;
	}
}
