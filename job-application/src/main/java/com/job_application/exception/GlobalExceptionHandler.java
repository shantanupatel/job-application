package com.job_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = IdNotFoundException.class)
	public ResponseEntity<Object> handleIdNotFoundException(IdNotFoundException ex) {
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
