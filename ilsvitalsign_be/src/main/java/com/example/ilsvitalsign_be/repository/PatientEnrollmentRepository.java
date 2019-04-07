package com.example.ilsvitalsign_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ilsvitalsign_be.entity.PatientEnrollmentEntity;



@Repository
public interface PatientEnrollmentRepository extends JpaRepository<PatientEnrollmentEntity, Integer> {

	PatientEnrollmentEntity findByPatientId(Integer patientId);

	PatientEnrollmentEntity findByMrn(String mrn);

	PatientEnrollmentEntity findByCdrId(String id);

}
