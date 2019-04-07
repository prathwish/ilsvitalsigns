package com.example.ilsvitalsign_be.dto;

import java.io.Serializable;

public class Coding implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String system;
	private String code;
	private String display;

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@Override
	public String toString() {
		return "Coding [system=" + system + ", code=" + code + ", display=" + display + "]";
	}

}
