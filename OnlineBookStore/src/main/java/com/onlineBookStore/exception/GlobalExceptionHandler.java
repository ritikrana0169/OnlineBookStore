package com.onlineBookStore.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(DuplicateEmailException.class)
	    public ResponseEntity<ErrorDetails> handleDuplicateEmailException(DuplicateEmailException ex, WebRequest request) {
	        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
	        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	    }
	 @ExceptionHandler(ResourceNotFoundException.class)
	 public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFoundException ex,WebRequest request){
		 ErrorDetails error=new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
		 return new ResponseEntity<>(error,HttpStatus.CONFLICT);
	 }

}
