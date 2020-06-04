package org.codejudge.sb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.format.annotation.NumberFormat;

@Embeddable
public class TbCrmLeadsPk implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@Column(nullable = false)
	private String email;

	@NumberFormat(pattern = "#")
	@Column(length = 10, columnDefinition = "number(10)", nullable = false)
	private Long mobile;

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

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

}
