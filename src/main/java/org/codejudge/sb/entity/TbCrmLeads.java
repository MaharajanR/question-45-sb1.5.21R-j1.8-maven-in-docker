package org.codejudge.sb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TB_CRM_LEADS", uniqueConstraints = { @UniqueConstraint(columnNames = { "email", "mobile" }) })
@IdClass(TbCrmLeadsPk.class)
public class TbCrmLeads {

	@Id
	private Long id;

	@Id
	private String email;

	@Id
	private long mobile;

	@Column(nullable = false)
	private String first_name;

	@Column(nullable = false)
	private String last_name;

	@Column(nullable = false)
	private String location_string;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Location_type location_type;

	@Column(nullable = true)
	private String communication;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

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

	public String getLocation_string() {
		return location_string;
	}

	public void setLocation_string(String location_string) {
		this.location_string = location_string;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Location_type getLocation_type() {
		return location_type;
	}

	public void setLocation_type(Location_type location_type) {
		this.location_type = location_type;
	}

	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

}
