package org.devops.tool.autocode.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ����ת����mysql -> Java
 * 
 * @author liuzifeng
 *
 */
public class JavaType {
	
	private static Map<String, String> map = new HashMap<String, String>();
	
	private static Map<String, String> mapImport = new HashMap<String, String>();
	private Date date;
	static {
		map.put("int", "Integer");
		map.put("bit", "Integer");
		map.put("bigint", "Long");
		map.put("timestamp", "Date");
		map.put("varchar", "String");
		map.put("text", "String");
		map.put("longtext", "String");
		map.put("mediumtext", "String");
		map.put("json", "String");
		map.put("enum", "String");
		map.put("datetime", "Date");
		map.put("double", "Double");
		map.put("decimal", "BigDecimal");
		map.put("tinyint", "Byte");
		map.put("date", "Date");
		map.put("char", "String");
		map.put("smallint", "Integer");
		map.put("float", "Float");
		
		mapImport.put("decimal", "import java.math.BigDecimal;");
		mapImport.put("datetime", "import java.util.Date;");
		mapImport.put("date", "import java.util.Date;");
		mapImport.put("timestamp", "import java.util.Date;");
	}
	
	public static String get(String sqlType) {
		return map.get(sqlType.toLowerCase());
	}
	
	public static String getImport(String sqlType) {
		return mapImport.get(sqlType.toLowerCase());
	}

}
