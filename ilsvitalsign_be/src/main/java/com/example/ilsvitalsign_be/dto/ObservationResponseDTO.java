package com.example.ilsvitalsign_be.dto;

import java.io.Serializable;
import java.util.List;

public class ObservationResponseDTO implements Serializable {

	private Integer observationId;
	private String sourceName;
	private String category;
	private String subCategoryType;
	private String subCategoryCode;
	private String cdrId;
	private String patientId;
	private List<ObservationValueDTO> observationValueDTO;

	public Integer getObservationId() {
		return observationId;
	}

	public void setObservationId(Integer observationId) {
		this.observationId = observationId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategoryType() {
		return subCategoryType;
	}

	public void setSubCategoryType(String subCategoryType) {
		this.subCategoryType = subCategoryType;
	}

	public String getSubCategoryCode() {
		return subCategoryCode;
	}

	public void setSubCategoryCode(String subCategoryCode) {
		this.subCategoryCode = subCategoryCode;
	}

	public String getCdrId() {
		return cdrId;
	}

	public void setCdrId(String cdrId) {
		this.cdrId = cdrId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public List<ObservationValueDTO> getObservationValueDTO() {
		return observationValueDTO;
	}

	public void setObservationValueDTO(List<ObservationValueDTO> observationValueDTO) {
		this.observationValueDTO = observationValueDTO;
	}

}
