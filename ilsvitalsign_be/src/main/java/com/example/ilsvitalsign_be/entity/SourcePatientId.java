package com.example.ilsvitalsign_be.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class SourcePatientId implements Serializable {
	
	private static final long	serialVersionUID	= 1L;
	
	@ManyToOne
	@JoinColumn(name = "PATIENT_ID", nullable = false)
	private PatientEnrollmentEntity	patientEnrollmentEntity; 
	
	@ManyToOne
	@JoinColumn(name = "SOURCE_ID", nullable = false)
	private SourceEntity source;

	public PatientEnrollmentEntity getPatientEnrollmentEntity() {
		return patientEnrollmentEntity;
	}

	public void setPatientEnrollmentEntity(PatientEnrollmentEntity patientEnrollmentEntity) {
		this.patientEnrollmentEntity = patientEnrollmentEntity;
	}

	public SourceEntity getSource() {
		return source;
	}

	public void setSource(SourceEntity source) {
		this.source = source;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
