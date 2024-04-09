package com.example.demo.exceptions;

import java.time.LocalDateTime;

public class ExceptionResponse {
	private String errorMessage;
	private int errorCode;
	private LocalDateTime date;
	public String getError() {
		return errorMessage;
	}
	public void setError(String error) {
		this.errorMessage = error;
	}
	public int getCode() {
		return errorCode;
	}
	public void setCode(int code) {
		this.errorCode = code;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	

}
