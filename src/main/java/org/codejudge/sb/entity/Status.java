package org.codejudge.sb.entity;

public enum Status {

	CR("Created"), CO("Contacted");

	private String status;

	private Status(String status) {
		this.status = status;
	}

	public String getValue() {
		return String.format("%s", status);
	}
}
