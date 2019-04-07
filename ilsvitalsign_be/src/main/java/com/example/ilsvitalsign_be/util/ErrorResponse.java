package com.example.ilsvitalsign_be.util;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorResponse {

	public ErrorResponse(Boolean action, String details) {
		super();
		this.action = action;
		this.details = details;
	}

	// General error message about nature of error
	private Boolean action;

	// Specific errors in API request processing
	private String details;

	public Boolean getAction() {
		return action;
	}

	public void setAction(Boolean action) {
		this.action = action;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
