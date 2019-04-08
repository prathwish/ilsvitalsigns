package com.example.ilsvitalsign_be.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "SOURCE_PATIENT")
public class SourcePatientEntity {

	@EmbeddedId
	private SourcePatientId sourcePatientId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sourcePatientEntity", fetch = FetchType.LAZY)
	@OrderBy("effectiveDateTime Desc")
	List<ObservationEntity> observationEntity = new ArrayList<>();

	public SourcePatientId getSourcePatientId() {
		return sourcePatientId;
	}

	public void setSourcePatientId(SourcePatientId sourcePatientId) {
		this.sourcePatientId = sourcePatientId;
	}

	public List<ObservationEntity> getObservationEntity() {
		return observationEntity;
	}

	public void setObservationEntity(List<ObservationEntity> observationEntity) {
		this.observationEntity = observationEntity;
	}

}
