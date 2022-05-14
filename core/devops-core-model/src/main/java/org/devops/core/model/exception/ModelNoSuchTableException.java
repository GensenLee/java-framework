package org.devops.core.model.exception;

public class ModelNoSuchTableException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ModelNoSuchTableException(String message){
		super(message);
	}
}
