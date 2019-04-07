package com.example.ilsvitalsign_be.dto;

public class ValueQuantity {

	private Long value;
	private String unit;
	private String system;
	private String code;

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

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

	@Override
	public String toString() {
		return "ValueQuantity [value=" + value + ", unit=" + unit + ", system=" + system + ", code=" + code + "]";
	}

}
