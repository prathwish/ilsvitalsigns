package com.example.ilsvitalsign_be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ilsvitalsign_be.entity.ObservationEntity;

public interface ObservationRepository extends JpaRepository<ObservationEntity, Integer> {

	List<ObservationEntity> findTop700BySourcePatientEntity_SourcePatientId_PatientEnrollmentEntity_PatientIdAndSourcePatientEntity_SourcePatientId_Source_SourceIdOrderByEffectiveDateTimeDesc(
			Integer patientId, Integer sourceId);

	// List<ObservationEntity>
	// findTop700ByPatientEnrollmentEntity_PatientIdOrderByEffectiveDateTimeDesc(Integer
	// patientId);

}
