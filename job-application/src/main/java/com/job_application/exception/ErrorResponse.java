package com.job_application.exception;

public class ErrorResponse {

	private int HttpStatus;
	private String message;

	public ErrorResponse(int httpStatus, String message) {
		super();
		HttpStatus = httpStatus;
		this.message = message;
	}

	public int getHttpStatus() {
		return HttpStatus;
	}

	public String getMessage() {
		return message;
	}

}
