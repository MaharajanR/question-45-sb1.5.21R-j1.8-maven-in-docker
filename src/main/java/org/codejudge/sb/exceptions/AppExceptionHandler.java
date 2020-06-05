package org.codejudge.sb.exceptions;

import org.codejudge.sb.payload.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ErrorResponse> handleCrmServiceException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse("failure", ex.getMessage());
		return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
