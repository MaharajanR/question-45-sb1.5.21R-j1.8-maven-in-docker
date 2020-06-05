package org.codejudge.sb.payload.response;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FetchLeadsResponse {

	@JsonFormat(pattern = "")
	private String first_name;
	private String last_name;
	private BigInteger mobile;
	private String email;
	private String location_type;
	private String location_string;
	private String status;
	private String communication;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public BigInteger getMobile() {
		return mobile;
	}

	public void setMobile(BigInteger mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation_type() {
		return location_type;
	}

	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}

	public String getLocation_string() {
		return location_string;
	}

	public void setLocation_string(String location_string) {
		this.location_string = location_string;
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
