package org.devops.mjar.message.vo;

import org.devops.core.utils.vo.BaseBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MessageVO<T> extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String subject;
	
	public T data;

	public MessageVO(){
		
	}
	
	public MessageVO(T data) {
		super();
		this.subject = null;
		this.data = data;
	}
	
	public MessageVO(String subject, T data) {
		super();
		this.subject = subject;
		this.data = data;
	}
	
	
}
