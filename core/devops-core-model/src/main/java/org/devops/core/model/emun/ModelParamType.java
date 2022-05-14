package org.devops.core.model.emun;

public enum ModelParamType {

	VALUES("values","值"),
	WHERE("where","条件");
	
	private  ModelParamType(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	private String code;
	private String desc;
	
	public String getCode(){
		return this.code;
	}
	
	public String getDesc() {
		return desc;
	}
}
