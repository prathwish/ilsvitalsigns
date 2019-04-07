package com.example.ilsvitalsign_be.dto;

import java.io.Serializable;

public class Identifier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Type type;
	private String value;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
