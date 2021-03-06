package com.example.first_project.exceptions;

import com.example.first_project.entities.ExceptionInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestExceptionHandler {

	private static final Logger logger = LogManager.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handlerConstraintViolationException(ConstraintViolationException e) {
		ExceptionInfo error = new ExceptionInfo(e.getMessage(), HttpStatus.BAD_REQUEST.value());

		logger.info("ConstraintViolationException");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<?> handlerBadRequestException(MissingServletRequestParameterException e) {
		ExceptionInfo error = new ExceptionInfo(e.getMessage(), HttpStatus.BAD_REQUEST.value());

		logger.info("MissingServletRequestParameterException");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TriangleDoesNotExistException.class)
	public ResponseEntity<?> handlerTriangleDoesNotExistException(TriangleDoesNotExistException e) {
		ExceptionInfo error = new ExceptionInfo(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());

		logger.info("TriangleDoesNotExistException");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
