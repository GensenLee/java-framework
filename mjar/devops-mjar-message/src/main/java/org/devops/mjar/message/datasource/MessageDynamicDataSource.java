package org.devops.mjar.message.datasource;

public class MessageDynamicDataSource {

	private static String dataSource;
	
	public static void setDataSource(String dataSource){
		MessageDynamicDataSource.dataSource = dataSource;
	}

	public static String getDataSource(){
		return MessageDynamicDataSource.dataSource;
	}
}
