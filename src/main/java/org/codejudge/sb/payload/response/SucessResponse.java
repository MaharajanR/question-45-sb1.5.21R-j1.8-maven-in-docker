package org.codejudge.sb.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class SucessResponse {

	private String status;

	@JsonInclude(Include.NON_NULL)
	private String communication;

	public SucessResponse(String status) {
		this.status = status;
	}

	public SucessResponse(String status, String communication) {
		this.status = status;
		this.communication = communication;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

}
