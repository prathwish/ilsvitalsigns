package com.example.ilsvitalsign_be.dto;

import java.util.List;

public class MaritalStatus {
	
	private List<Coding> coding ;
	private String text;
	public List<Coding> getCoding() {
		return coding;
	}
	public void setCoding(List<Coding> coding) {
		this.coding = coding;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	
}
