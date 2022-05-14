package org.devops.core.utils.datasource;

import java.util.HashMap;
import java.util.Map;

import org.devops.core.utils.util.StringUtil;

public class DataSourceMapper {

	private static Map<String,String> mapPackage = new HashMap<>();
	
	public static void setPackage(String packages,String name) {
		mapPackage.put(packages, name);
	}
	
	public static String getName(String packages) {
		if(StringUtil.isEmpty(packages)) {
			return "";
		}
		String name = mapPackage.get(packages);
		if(StringUtil.isNotEmpty(name)) {
			return name;
		}
		if(!packages.contains(".")) {
			return "";
		}
		return getName(packages.substring(0, packages.lastIndexOf(".")));
	}
	
	public static String getName(Class<?> clazz) {
		return getName(clazz.getPackage().getName());
	}
}
