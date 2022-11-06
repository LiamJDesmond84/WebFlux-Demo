package com.liam.webfluxdemo.exceptionhandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.liam.webfluxdemo.dtos.InputFailedValidationResponse;
import com.liam.webfluxdemo.exceptions.InputValidationException;

@ControllerAdvice
public class InputValidationHandler {
	
	
	@ExceptionHandler(InputValidationException.class)
	public ResponseEntity<InputFailedValidationResponse> handleException(InputValidationException exc) {
		
		InputFailedValidationResponse response = new InputFailedValidationResponse();
		
		response.setErrorCode(exc.getErrorcode());
		response.setInput(exc.getInput());
		response.setMessage(exc.getMessage());
		
		return ResponseEntity.badRequest().body(response);
		
	}

}
