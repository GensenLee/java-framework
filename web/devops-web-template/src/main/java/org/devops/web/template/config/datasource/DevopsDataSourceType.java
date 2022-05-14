package org.devops.web.template.config.datasource;

/**
 * 数据源标识
 * @author xhz
 *
 */
public class DevopsDataSourceType {

	private DevopsDataSourceType() {
		
	}

	public final static String DEVOPS_01 = "devops01";
	public final static String DEVOPS_02 = "devops02";

	/**
	 * 主库1
	 */
	public final static String MASTER = "MASTER";
	
	/**
	 * 从库1
	 */
	public final static String SLAVE1 = "SLAVE1";
	
}
