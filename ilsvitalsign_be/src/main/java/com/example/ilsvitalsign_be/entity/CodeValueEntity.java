package com.example.ilsvitalsign_be.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CODE_VALUE")
public class CodeValueEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODE_VALUE_ID", length = 8)
	private Integer codeValueId;

	@NotNull
	@Column(name = "CODE", length = 20)
	private String code;

	@NotNull
	@Column(name = "DISPLAY", length = 128)
	private String display;

	public Integer getCodeValueId() {
		return codeValueId;
	}

	public void setCodeValueId(Integer codeValueId) {
		this.codeValueId = codeValueId;
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

}
