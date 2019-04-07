package com.example.ilsvitalsign_be.dto;

public class Subject {

	private String reference;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "Subject [reference=" + reference + "]";
	}

}
