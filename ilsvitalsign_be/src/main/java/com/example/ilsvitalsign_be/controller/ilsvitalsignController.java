package com.example.ilsvitalsign_be.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.ilsvitalsign_be.dto.AuditEvent;
import com.example.ilsvitalsign_be.dto.NewPatientResponse;
import com.example.ilsvitalsign_be.dto.ObservationResponseDTO;
import com.example.ilsvitalsign_be.dto.PatientDetails;
import com.example.ilsvitalsign_be.dto.PatientEnrollmentDTO;
import com.example.ilsvitalsign_be.dto.PatientListDTO;
import com.example.ilsvitalsign_be.dto.Resource;
import com.example.ilsvitalsign_be.dto.SourceObservationDTO;
import com.example.ilsvitalsign_be.entity.ObservationEntity;
import com.example.ilsvitalsign_be.entity.PatientEnrollmentEntity;
import com.example.ilsvitalsign_be.service.IlsvitalsignService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ilsvitalsignController {

	static Logger logger = LoggerFactory.getLogger(ilsvitalsignController.class);

	@Autowired
	private IlsvitalsignService ilsvitalsignService;

	@Autowired
	private SimpMessagingTemplate brokerMessagingTemplate;

	@GetMapping(value = "/dashboard/getallpatient/")
	public ResponseEntity<List<PatientListDTO>> getAllAvailablePatient() {
		logger.info("inside getAllAvailablePatient");
		List<PatientListDTO> patientListDTO = new ArrayList<>();
		List<PatientEnrollmentEntity> availablePatient = ilsvitalsignService.getAllAvailablePatient();
		availablePatient.forEach(patient -> patientListDTO.add(ilsvitalsignService.populateAllPatientDetails(patient)));
		logger.info("exiting getAllAvailablePatient");
		return new ResponseEntity<>(patientListDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/dashboard/getpatient/{patientId}")
	public ResponseEntity<List<PatientDetails>> getPatient(@PathVariable("patientId") Integer patientId)
			throws Exception {
		logger.info("inside getPatient1");
		List<PatientDetails> patientDetails = ilsvitalsignService.getPatient(patientId);
		logger.info("exiting getAllAvailablePatient");
		return new ResponseEntity<>(patientDetails, HttpStatus.OK);
	}

	@PostMapping(value = "/dashboard/patient/add")
	public ResponseEntity<NewPatientResponse> createNewPatient(@RequestBody PatientEnrollmentDTO patientEnrollment) {
		logger.info("info");
		ilsvitalsignService.createPatient(patientEnrollment);
		return new ResponseEntity<>(populateNewPatientResponse("Record Added Successfully"), HttpStatus.OK);
	}

	@PutMapping(value = "/dashboard/patient/enrollment/{pid}")
	public ResponseEntity<NewPatientResponse> patientEnrollment(@PathVariable("pid") Integer pid) {
		logger.info("patient enrollment by pid:: " + pid);
		ilsvitalsignService.getPatientById(pid);
		return new ResponseEntity<>(populateNewPatientResponse("CDR records created successfully"), HttpStatus.OK);
	}

	//@MessageMapping("/hello2")
	@SendTo("/topic/greetings")
	@PostMapping(value = "/dashboard/patient/observation")
	public ResponseEntity<ObservationResponseDTO> patientObservation(@RequestBody Resource resource) throws Exception {
		logger.info("inside patientObservation method");
		Gson gson = new Gson();
		logger.info("observation in jsin format::" + gson.toJson(resource));
		ObservationResponseDTO resp = null;
		if (Objects.nonNull(resource)) {
			ObservationEntity observationEntity = ilsvitalsignService.createObservation(resource);
			logger.info("entity saved successfully done");
			resp = ilsvitalsignService.populatePostObservationDTO(observationEntity);
			logger.info("ObservationResponseDTO json::" + gson.toJson(resp));

			brokerMessagingTemplate.convertAndSend("/topic/greetings",
					JSON.toJSONString(resp, SerializerFeature.BrowserCompatible));
			/*
			 * if (Objects.nonNull(observationEntity)) {
			 * brokerMessagingTemplate.convertAndSend("/topic/greetings",
			 * JSON.toJSONString(resp, SerializerFeature.BrowserCompatible)); }
			 */

		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@MessageMapping("/hello")
	//@SendTo("/topic/greetings")
	public ResponseEntity<String> patientObservationTest(String id) throws Exception {
		logger.info("id::"+id);
		brokerMessagingTemplate.convertAndSend("/topic/greetings",
				JSON.toJSONString(id, SerializerFeature.BrowserCompatible));

		return new ResponseEntity<>("success", HttpStatus.OK);

	}

	@GetMapping(value = "/dashboard/patient/observations/{patientId}")
	public ResponseEntity<List<SourceObservationDTO>> getObservationsByPatientId(@PathVariable Integer patientId) {
		List<SourceObservationDTO> sourceObservationDTO = ilsvitalsignService.getAllObservation(patientId);
		return new ResponseEntity<>(sourceObservationDTO, HttpStatus.OK);
	}

	private NewPatientResponse populateNewPatientResponse(String msg) {
		NewPatientResponse newPatientResponse = new NewPatientResponse();
		newPatientResponse.setAction(true);
		newPatientResponse.setMessage(msg);
		return newPatientResponse;

	}
}
