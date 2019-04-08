package com.example.ilsvitalsign_be.service;

import java.util.List;

import com.example.ilsvitalsign_be.dto.ObservationResponseDTO;
import com.example.ilsvitalsign_be.dto.PatientDetails;
import com.example.ilsvitalsign_be.dto.PatientEnrollmentDTO;
import com.example.ilsvitalsign_be.dto.PatientListDTO;
import com.example.ilsvitalsign_be.dto.Resource;
import com.example.ilsvitalsign_be.dto.SourceObservationDTO;
import com.example.ilsvitalsign_be.entity.ObservationEntity;
import com.example.ilsvitalsign_be.entity.PatientEnrollmentEntity;




public interface IlsvitalsignService {

	Integer createPatient(PatientEnrollmentDTO patientEnrollment);

	List<PatientEnrollmentEntity> getAllAvailablePatient();
	
	void getPatientById(Integer pid);

	PatientEnrollmentDTO populatePatientEnrollmentDTO(PatientEnrollmentEntity patient);

	PatientListDTO populateAllPatientDetails(PatientEnrollmentEntity patient);

	List<PatientDetails> getPatient(Integer patientId) throws Exception;

	List<SourceObservationDTO> getAllObservation(Integer id);

	ObservationResponseDTO populatePostObservationDTO(ObservationEntity observationEntity);

	ObservationEntity createObservation(Resource resource);

		
	}


