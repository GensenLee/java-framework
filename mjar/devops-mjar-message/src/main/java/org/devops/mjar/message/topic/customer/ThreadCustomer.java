package org.devops.mjar.message.topic.customer;

import lombok.Data;

@Data
public class ThreadCustomer {

	private String name;
	
	private String topic;
	
	private String[] tag;
	
	private ICustomer iCustomer;
	
	public boolean checked(String tag){
		if(this.tag == null || tag.length() == 0) {
			return false;
		}
		if(this.tag[0].equals("*")){
			return true;
		}
		for(String t : this.tag){
			if(t.equalsIgnoreCase(tag)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean onMessage(String tag,String body){
		if(!checked(tag)) {
			return false;
		}
		return iCustomer.onMsg(tag, body);
	}
	
}
