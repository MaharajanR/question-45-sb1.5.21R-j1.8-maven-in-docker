package org.codejudge.sb.payload;

public class ErrorResponse extends Exception {

	private static final long serialVersionUID = 1128678242007093434L;
	private final String status;
	private final String reason;

	public ErrorResponse(String status, String reason) {
		this.status = status;
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}

}
