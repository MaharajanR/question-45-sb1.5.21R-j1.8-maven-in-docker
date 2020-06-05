package org.codejudge.sb.messageutil;

public enum Messages {
	NO_RCRD_FND("No Record found for the lead id"), SUCC("success"), FAIL("failure");

	private String message;

	private Messages(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
