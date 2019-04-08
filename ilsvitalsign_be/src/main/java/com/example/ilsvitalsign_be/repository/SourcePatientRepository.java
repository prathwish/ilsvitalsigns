package com.example.ilsvitalsign_be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ilsvitalsign_be.entity.SourcePatientEntity;
import com.example.ilsvitalsign_be.entity.SourcePatientId;

public interface SourcePatientRepository extends JpaRepository<SourcePatientEntity, SourcePatientId> {

	SourcePatientEntity findBySourcePatientId_PatientEnrollmentEntity_PatientIdAndSourcePatientId_Source_SourceId(
			Integer patientId, Integer sourceId);

	List<SourcePatientEntity> findBySourcePatientId_PatientEnrollmentEntity_PatientId(Integer patientId);

}
