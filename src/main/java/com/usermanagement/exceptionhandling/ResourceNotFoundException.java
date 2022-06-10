package com.usermanagement.exceptionhandling;

public class ResourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3792730321198696519L;
	
	public ResourceNotFoundException(){
		
	}
	
	
	public ResourceNotFoundException(final String message){
		super(message);
	}

}
