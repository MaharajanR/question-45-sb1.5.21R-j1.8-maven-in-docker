package org.codejudge.sb.exceptions;

public class ExceptionHandling extends RuntimeException {

	private static final long serialVersionUID = 6704728443775286469L;
	private String message;

	public ExceptionHandling(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
