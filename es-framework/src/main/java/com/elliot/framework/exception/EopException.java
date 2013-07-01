package com.elliot.framework.exception;

public class EopException extends RuntimeException {
	public EopException(){
		super();
	}
	
	public EopException(String message){
		super(message);
	}
}
