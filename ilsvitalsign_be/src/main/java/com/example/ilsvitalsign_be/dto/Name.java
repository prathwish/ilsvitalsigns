package com.example.ilsvitalsign_be.dto;

import java.io.Serializable;

public class Name implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String use;
	private String family;
	private String[] given;
	private String prefix;
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}

	public String[] getGiven() {
		return given;
	}
	public void setGiven(String[] given) {
		this.given = given;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	

}
