package com.A1.cwa.exception;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.A1.cwa.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AuthorizationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<ErrorResponse> authorizationException(AuthorizationException ex) {
		ErrorResponse er = new ErrorResponse();

		er.setError(HttpStatus.FORBIDDEN.toString());
		er.setStatus(HttpStatus.FORBIDDEN.value());
		er.setMessage(ex.getMessage());

		return new ResponseEntity<>(er, HttpStatus.FORBIDDEN);

	}
	
	@ExceptionHandler(TestTransactionException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> TestTransactionException(TestTransactionException ex) {
		ErrorResponse er = new ErrorResponse();

		er.setError(HttpStatus.BAD_REQUEST.toString());
		er.setStatus(HttpStatus.BAD_REQUEST.value());
		er.setMessage(ex.getMessage());

		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

	}

}
