package org.devops.iweb.auth.datasource;

public class AuthDynamicDataSource {

	private static String dataSource;
	
	public static void setDataSource(String dataSource){
		AuthDynamicDataSource.dataSource = dataSource;
	}

	public static String getDataSource(){
		return AuthDynamicDataSource.dataSource;
	}
}
