package org.codejudge.sb.entity;

public enum Location_type {

	USA("US", "CA Address", "2000"), IND("IND", "Mumbai Address", "2001");

	private String country;
	private String city;
	private String zip;

	private Location_type(String country, String city, String zip) {
		this.country = country;
		this.city = city;
		this.zip = zip;
	}



	public String getValue() {
		return String.format("%s/%s/%s", country, city, zip);
	}
}
