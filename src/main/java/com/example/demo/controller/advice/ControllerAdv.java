package com.example.demo.controller.advice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.demo.exceptions.*;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ControllerAdv {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> ExceptionMet(){
		ExceptionResponse res = new ExceptionResponse();
		res.setCode(101);
		res.setError("User Not Found");
		res.setDate(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(res,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyRegistered.class)
	public ResponseEntity<ExceptionResponse> anotherError(){
		ExceptionResponse res = new ExceptionResponse();
		res.setCode(1055);
		res.setError("User is Already Registered");
		res.setDate(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(res,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> resp = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String FieldName = ((FieldError)error).getField();
			String defaultMessage = error.getDefaultMessage();
			resp.put(FieldName, defaultMessage);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ExceptionResponse> CategoryException(){
		ExceptionResponse res = new ExceptionResponse();
		res.setCode(301);
		res.setError("Category Not Found");
		res.setDate(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(res,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PostAlreadyExist.class)
	public ResponseEntity<ExceptionResponse> postAlreadyExist(){
		ExceptionResponse res = new ExceptionResponse();
		res.setCode(302);
		res.setError("Post Already Exist");
		res.setDate(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(res,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CategoryAlreadyExist.class)
	public ResponseEntity<ExceptionResponse> CategoryAlreadyExist(){
		ExceptionResponse res = new ExceptionResponse();
		res.setCode(302);
		res.setError("Post Already Exist");
		res.setDate(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(res,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ExceptionResponse> entityNotFoundException(){
		ExceptionResponse res = new ExceptionResponse();
		res.setCode(303);
		res.setError("Entity Not Found");
		res.setDate(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(res,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CommonException.class)
	public ResponseEntity<ExceptionResponse> commonException(CommonException ex){
		ExceptionResponse res = new ExceptionResponse();
		res.setCode(ex.getErrorCode());
		res.setError(ex.getErrorMessage());
		res.setDate(LocalDateTime.now());
		return new ResponseEntity<ExceptionResponse>(res,HttpStatus.BAD_REQUEST);
	}
}
