package org.devops.iweb.file.constant;

import lombok.Data;

@Data
public class IwebFileConstant {

	private static String path = "";
	private static String domain = "";

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		IwebFileConstant.path = path;
	}
	
	public static String getDomain() {
		return domain;
	}

	public static void setDomain(String domain) {
		IwebFileConstant.domain = domain;
	}
}
