package com.example.ilsvitalsign_be.dto;

public class Component {

	private Code code;
	private ValueQuantity valueQuantity;

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public ValueQuantity getValueQuantity() {
		return valueQuantity;
	}

	public void setValueQuantity(ValueQuantity valueQuantity) {
		this.valueQuantity = valueQuantity;
	}

	@Override
	public String toString() {
		return "Component [code=" + code + ", valueQuantity=" + valueQuantity + "]";
	}
	
	

}
