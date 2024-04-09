package com.example.demo.exceptions;

public class CommonException extends RuntimeException{
	String errorMessage;
	int errorCode;
	
	public CommonException(String errorMessage, int errorCode ) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
