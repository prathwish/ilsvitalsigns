package com.example.ilsvitalsign_be.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OBSERVATION")
public class ObservationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OBSERVATION_ID")
	private Integer observationId;

	@Column(name = "CATEGORY", length = 128)
	private String category;

	@Column(name = "SUBCATEGORY_TYPE", length = 128)
	private String subCategoryType;

	@Column(name = "SUBCATEGORY_CODE", length = 128)
	private String subCategoryCode;

	@Column(name = "CDRID", length = 128)
	private String cdrId;

	@Column(name = "EFFECTIVEDATE_TIME", length = 128)
	private String effectiveDateTime;

	@JoinColumns({ @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATIENT_ID"),
			@JoinColumn(name = "SOURCE_ID", referencedColumnName = "SOURCE_ID") })
	@ManyToOne(cascade = CascadeType.ALL)
	private SourcePatientEntity sourcePatientEntity;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "observationEntity", fetch = FetchType.EAGER)
	private List<ObservationValueEntity> observationValueEntity = new ArrayList<>();

	public Integer getObservationId() {
		return observationId;
	}

	public void setObservationId(Integer observationId) {
		this.observationId = observationId;
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

	public String getEffectiveDateTime() {
		return effectiveDateTime;
	}

	public void setEffectiveDateTime(String effectiveDateTime) {
		this.effectiveDateTime = effectiveDateTime;
	}

	public SourcePatientEntity getSourcePatientEntity() {
		return sourcePatientEntity;
	}

	public void setSourcePatientEntity(SourcePatientEntity sourcePatientEntity) {
		this.sourcePatientEntity = sourcePatientEntity;
	}

	public List<ObservationValueEntity> getObservationValueEntity() {
		return observationValueEntity;
	}

	public void setObservationValueEntity(List<ObservationValueEntity> observationValueEntity) {
		this.observationValueEntity = observationValueEntity;
	}

	
}
