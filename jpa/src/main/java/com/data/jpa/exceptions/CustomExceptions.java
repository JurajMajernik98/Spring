package com.data.jpa.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class CustomExceptions{
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> response(MethodArgumentNotValidException exception) {
		Map<String, String> response = new HashMap<>();
		
		
		List<ObjectError> errors = exception.getBindingResult().getAllErrors();
		
		for (ObjectError error : errors) {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			LocalDateTime timestamp = LocalDateTime.now();
			String statusCode = HttpStatus.BAD_REQUEST.toString();
			
			response.put(fieldName, message);
			response.put("timestamp", timestamp.toString());
			response.put("status", statusCode);
		}
	
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
