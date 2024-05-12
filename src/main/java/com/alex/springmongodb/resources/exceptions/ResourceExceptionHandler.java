package com.alex.springmongodb.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alex.springmongodb.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<StandardError> notFound(ObjectNotFoundException e, HttpServletRequest request){
		String error = "resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError errorStandard = new StandardError(Instant.now().toEpochMilli(),status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(errorStandard);
	}
}
