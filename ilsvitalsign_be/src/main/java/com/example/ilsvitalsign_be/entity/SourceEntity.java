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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SOURCE")
public class SourceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SOURCE_ID")
	private Integer sourceId;

	@Column(name = "SOURCE_NAME", length = 128)
	private String sourceName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sourcePatientId.source", fetch = FetchType.LAZY)
	private List<SourcePatientEntity> observationEntity = new ArrayList<>();

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public List<SourcePatientEntity> getObservationEntity() {
		return observationEntity;
	}

	public void setObservationEntity(List<SourcePatientEntity> observationEntity) {
		this.observationEntity = observationEntity;
	}

}
