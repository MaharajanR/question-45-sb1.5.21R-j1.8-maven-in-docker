package org.codejudge.sb.payload.Response;

public class ErrorResponse {

	private String status;
	private String reason;

	public ErrorResponse() {
	}

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
