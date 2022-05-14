package org.devops.mjar.ext.datasource;

public class ExtDynamicDataSource {

	private static String dataSource;
	
	public static void setDataSource(String dataSource){
		ExtDynamicDataSource.dataSource = dataSource;
	}

	public static String getDataSource(){
		return ExtDynamicDataSource.dataSource;
	}
}
