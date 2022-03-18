package com.example.first_project.exceptions;

public class TriangleDoesNotExistException extends RuntimeException {

	public TriangleDoesNotExistException() {
	}

	public TriangleDoesNotExistException(String message) {
		super(message);
	}

	public TriangleDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public TriangleDoesNotExistException(Throwable cause) {
		super(cause);
	}

	public TriangleDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}