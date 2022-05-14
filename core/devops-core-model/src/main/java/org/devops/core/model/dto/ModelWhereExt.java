package org.devops.core.model.dto;

import java.util.List;

import org.devops.core.utils.util.StringUtil;

public class ModelWhereExt {

	private Integer start;
	
	private Integer size;
	
	private String orderByStr;
	
	private String groupByStr;
	
	public ModelWhereExt limit(int size){
		this.size = size;
		return this;
	}
	
	public ModelWhereExt limit(int start,int size){
		this.start = start;
		this.size = size;
		return this;
	}
	
	public ModelWhereExt orderBy(String orderBy){
		this.orderByStr = orderBy;
		return this;
	}
	
	public ModelWhereExt groupBy(String groupBy){
		this.groupByStr = groupBy;
		return this;
	}
	
	@Override
	public String toString(){
		String ext = "";
		if(StringUtil.isNotEmpty(groupByStr)){
			ext+="GROUP BY "+groupByStr;
		}
		if(StringUtil.isNotEmpty(orderByStr)){
			ext+=" ORDER BY "+orderByStr;
		}
		if(start == null && size != null){
			ext += " LIMIT "+size;
		}else if(start != null && size != null){
			ext += " LIMIT "+start+","+size;
		}
		return ext;
	}
	
	public String toStringParametric(List<Object> listParameter){
		String ext = "";
		if(StringUtil.isNotEmpty(groupByStr)){
			ext+="GROUP BY "+groupByStr;
		}
		if(StringUtil.isNotEmpty(orderByStr)){
			ext+=" ORDER BY "+orderByStr;
		}
		if(start == null && size != null){
			ext += " LIMIT ?";
			listParameter.add(size);
		}else if(start != null && size != null){
			ext += " LIMIT ?,?";
			listParameter.add(start);
			listParameter.add(size);
		}
		return ext;
	}

}
