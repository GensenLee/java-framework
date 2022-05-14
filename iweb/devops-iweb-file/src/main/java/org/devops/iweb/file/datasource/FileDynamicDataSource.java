package org.devops.iweb.file.datasource;

public class FileDynamicDataSource {

	private static String dataSource;
	
	public static void setDataSource(String dataSource){
		FileDynamicDataSource.dataSource = dataSource;
	}

	public static String getDataSource(){
		return FileDynamicDataSource.dataSource;
	}
}
