package org.codejudge.sb.payload;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = false)
public class FetchLeadsRequest {

	@ApiModelProperty(required = true)
	private String first_name;

	@ApiModelProperty(required = true)
	private String last_name;

	@ApiModelProperty(required = true)
	@Size(min = 10, max = 11)
	private long mobile;

	@ApiModelProperty(required = true)
	private String email;

	@ApiModelProperty(required = true)
	private String location_type;

	@ApiModelProperty(required = true)
	private String location_string;

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

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
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

}
