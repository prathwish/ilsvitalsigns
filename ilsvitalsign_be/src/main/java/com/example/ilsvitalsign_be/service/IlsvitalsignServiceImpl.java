package com.example.ilsvitalsign_be.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.ilsvitalsign_be.dto.Address;
import com.example.ilsvitalsign_be.dto.Coding;
import com.example.ilsvitalsign_be.dto.Communication;
import com.example.ilsvitalsign_be.dto.Identifier;
import com.example.ilsvitalsign_be.dto.Language;
import com.example.ilsvitalsign_be.dto.MaritalStatus;
import com.example.ilsvitalsign_be.dto.Name;
import com.example.ilsvitalsign_be.dto.ObservationResponseDTO;
import com.example.ilsvitalsign_be.dto.ObservationValueDTO;
import com.example.ilsvitalsign_be.dto.PatientDTO;
import com.example.ilsvitalsign_be.dto.PatientDetails;
import com.example.ilsvitalsign_be.dto.PatientEnrollmentDTO;
import com.example.ilsvitalsign_be.dto.PatientListDTO;
import com.example.ilsvitalsign_be.dto.Resource;
import com.example.ilsvitalsign_be.dto.SourceObservationDTO;
import com.example.ilsvitalsign_be.dto.Telecom;
import com.example.ilsvitalsign_be.entity.CodeValueEntity;
import com.example.ilsvitalsign_be.entity.ObservationEntity;
import com.example.ilsvitalsign_be.entity.ObservationValueEntity;
import com.example.ilsvitalsign_be.entity.PatientEnrollmentEntity;
import com.example.ilsvitalsign_be.entity.SourceEntity;
import com.example.ilsvitalsign_be.entity.SourcePatientEntity;
import com.example.ilsvitalsign_be.entity.SourcePatientId;
import com.example.ilsvitalsign_be.repository.CodeValueRepository;
import com.example.ilsvitalsign_be.repository.ObservationRepository;
import com.example.ilsvitalsign_be.repository.PatientEnrollmentRepository;
import com.example.ilsvitalsign_be.repository.SourcePatientRepository;
import com.example.ilsvitalsign_be.repository.SourceRepository;
import com.example.ilsvitalsign_be.util.IlsvitalsignUtil;

@Component
public class IlsvitalsignServiceImpl implements IlsvitalsignService {

	static Logger logger = LoggerFactory.getLogger(IlsvitalsignServiceImpl.class);

	@Autowired
	Environment env;

	@Autowired
	private PatientEnrollmentRepository patientEnrollmentRepository;

	@Autowired
	ObservationRepository observationRepository;

	@Autowired
	private CodeValueRepository codeValueRepository;

	@Autowired
	SourcePatientRepository sourcePatientRepository;

	@Autowired
	SourceRepository sourceRepository;

	@Autowired
	private RestTemplate restTemplate;

	ModelMapper modelMapper = new ModelMapper();

	protected static final List<String> viewDetails = Arrays.asList("First Name", "Last Name", "Gender", "Dob", "Mrn");

	@Override
	public Integer createPatient(PatientEnrollmentDTO patientEnrollment) {
		PatientEnrollmentEntity patientEnrollmentEntity = populatePatientEnrollmentEntity(patientEnrollment);
		patientEnrollmentEntity.setCdrId("7d43cfc4-8a22-4ac9-a1fe-47c03e36c906");
		patientEnrollmentRepository.save(patientEnrollmentEntity);
		return patientEnrollmentEntity.getPatientId();
	}

	private PatientEnrollmentEntity populatePatientEnrollmentEntity(PatientEnrollmentDTO patientEnrollment) {
		PatientEnrollmentEntity patientEnrollmentEntity = modelMapper.map(patientEnrollment,
				PatientEnrollmentEntity.class);
		patientEnrollmentEntity.setAuditTimestamp(IlsvitalsignUtil.getCurrentTimestamp());
		return patientEnrollmentEntity;
	}

	public PatientEnrollmentDTO populatePatientEnrollmentDTO(PatientEnrollmentEntity patientEnrollmentEntity) {
		return modelMapper.map(patientEnrollmentEntity, PatientEnrollmentDTO.class);
	}

	@Override
	public List<PatientEnrollmentEntity> getAllAvailablePatient() {
		return patientEnrollmentRepository.findAll();
	}

	@Override
	public void getPatientById(Integer patientId) {
		PatientEnrollmentEntity patientEnrollmentEntity = patientEnrollmentRepository.findByPatientId(patientId);
		if (Objects.nonNull(patientEnrollmentEntity)) {
			logger.info("patientEnrollmentEntity::" + patientEnrollmentEntity);
			// PatientDTO patientDTO = populatePatientDTO(patientEnrollmentEntity);
			// String cdrUrl = Iilsvitalsign_beConstants.ENVIRONMENT_ENDPOINTS_CDR +
			// Iilsvitalsign_beConstants.CDR_URI;
			// logger.info("partyUrl::" + cdrUrl);
			// restTemplate.postForObject(cdrUrl, patientDTO, Object.class);
			patientEnrollmentEntity.setEnrollFlag(true);
			patientEnrollmentRepository.save(patientEnrollmentEntity);
		}
	}

	private PatientDTO populatePatientDTO(PatientEnrollmentEntity patientEnrollmentEntity) {
		logger.info("inside populatePatientDTO method");
		PatientDTO patientDTO = new PatientDTO();

		List<Identifier> identifiers = new ArrayList<>();

		Identifier mrIdentifier = new Identifier();
		com.example.ilsvitalsign_be.dto.Type mrType = new com.example.ilsvitalsign_be.dto.Type();
		List<Coding> msCodings = new ArrayList<>();
		Coding msCoding = new Coding();
		CodeValueEntity msCodeValueEntity = getCodingByCode("MR");
		msCoding.setCode(msCodeValueEntity.getCode());
		msCoding.setDisplay(msCodeValueEntity.getDisplay());

		msCodings.add(msCoding);
		mrType.setCoding(msCodings);
		mrType.setText(msCodeValueEntity.getDisplay());
		mrIdentifier.setType(mrType);
		mrIdentifier.setValue(patientEnrollmentEntity.getMRN());
		identifiers.add(mrIdentifier);

		Identifier sbIdentifier = new Identifier();
		com.example.ilsvitalsign_be.dto.Type sbType = new com.example.ilsvitalsign_be.dto.Type();
		List<Coding> sbCodings = new ArrayList<>();
		Coding sbCoding = new Coding();
		CodeValueEntity sbCodeValueEntity = getCodingByCode("SB");
		sbCoding.setCode(sbCodeValueEntity.getCode());
		sbCoding.setDisplay(sbCodeValueEntity.getDisplay());

		sbCodings.add(sbCoding);
		sbType.setCoding(sbCodings);
		sbType.setText(sbCodeValueEntity.getDisplay());
		sbIdentifier.setType(sbType);
		sbIdentifier.setValue(patientEnrollmentEntity.getSSN_Number());
		identifiers.add(sbIdentifier);

		patientDTO.setIdentifier(identifiers);

		List<Name> names = new ArrayList<>();
		Name name = new Name();
		name.setFamily(patientEnrollmentEntity.getFirstName());
		String[] given = { patientEnrollmentEntity.getLastName() };
		name.setGiven(given);
		name.setPrefix(patientEnrollmentEntity.getPrefix());
		name.setUse(patientEnrollmentEntity.getSuffix());
		names.add(name);
		patientDTO.setName(names);

		List<Telecom> telecoms = new ArrayList<>();
		Telecom home = new Telecom();
		home.setSystem("phone");
		home.setUse("home");
		home.setValue(patientEnrollmentEntity.getHomePhoneNumber());

		Telecom work = new Telecom();
		work.setSystem("phone");
		work.setUse("work");
		work.setValue(patientEnrollmentEntity.getBusinessPhoneNumber());

		telecoms.add(home);
		telecoms.add(work);
		patientDTO.setTelecom(telecoms);

		patientDTO.setBirthDate(patientEnrollmentEntity.getDob());
		patientDTO.setGender(patientEnrollmentEntity.getGender());

		List<Address> addresses = new ArrayList<>();
		Address address = new Address();
		address.setCity(patientEnrollmentEntity.getCity());
		address.setCountry(patientEnrollmentEntity.getCountry());
		address.setLine(patientEnrollmentEntity.getStreet());
		address.setPostalCode(patientEnrollmentEntity.getZipCode());
		address.setState(patientEnrollmentEntity.getState());
		addresses.add(address);
		patientDTO.setAddress(addresses);

		MaritalStatus maritalStatus = new MaritalStatus();
		List<Coding> codings = new ArrayList<>();
		Coding coding = new Coding();
		CodeValueEntity codeValueEntity = getCodingByDisplayName(patientEnrollmentEntity.getMaritalStatus());
		coding.setCode(codeValueEntity.getCode());
		coding.setDisplay(codeValueEntity.getDisplay());
		codings.add(coding);
		maritalStatus.setCoding(codings);
		maritalStatus.setText(codeValueEntity.getDisplay());
		patientDTO.setMaritalStatus(maritalStatus);

		patientDTO.setMultipleBirthBoolean(Boolean.valueOf(patientEnrollmentEntity.getMultipleBirthIndicator()));

		List<Communication> communications = new ArrayList<>();
		Communication communication = new Communication();
		Language languages = new Language();
		List<Coding> codingLangs = new ArrayList<>();
		Coding codelang = new Coding();
		CodeValueEntity codeValueLangEntity = getCodingByDisplayName(patientEnrollmentEntity.getPrimaryLanguage());
		codelang.setCode(codeValueLangEntity.getCode());
		codelang.setDisplay(codeValueLangEntity.getDisplay());
		codingLangs.add(codelang);
		languages.setCoding(codingLangs);
		communication.setLanguage(languages);
		communications.add(communication);
		patientDTO.setCommunication(communications);

		patientDTO.setResourceType("Patient");

		logger.info("Exiting populatePatientDTO method");
		return patientDTO;
	}

	private CodeValueEntity getCodingByCode(String codeName) {
		if (Objects.nonNull(codeName)) {
			return codeValueRepository.findByCode(codeName);
		}
		return new CodeValueEntity();
	}

	private CodeValueEntity getCodingByDisplayName(String displayName) {
		if (Objects.nonNull(displayName)) {
			return codeValueRepository.findByDisplay(displayName);
		}
		return new CodeValueEntity();
	}

	@Override
	public PatientListDTO populateAllPatientDetails(PatientEnrollmentEntity patient) {

		PatientListDTO patientListDTO = new PatientListDTO();
		patientListDTO.setFirstname(patient.getFirstName());
		patientListDTO.setLastname(patient.getLastName());
		patientListDTO.setMrn(patient.getMRN());
		patientListDTO.setPatientId(patient.getPatientId());
		patientListDTO.setEnrollFlag(patient.isEnrollFlag());
		return patientListDTO;
	}

	@Override
	public List<PatientDetails> getPatient(Integer patientId) throws Exception {
		List<PatientDetails> patientDetails = new ArrayList<>();
		PatientEnrollmentEntity patientEnrollmentEntity = patientEnrollmentRepository.findByPatientId(patientId);
		if (Objects.nonNull(patientEnrollmentEntity)) {
			logger.info("patientEnrollmentEntity:::" + patientEnrollmentEntity);
			List<Field> fields = Arrays.asList(patientEnrollmentEntity.getClass().getDeclaredFields());
			for (Field field : fields) {

				String fieldName = Objects.nonNull(field.getName())
						? field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1)
								.replaceAll("_", " ").replaceAll("([^_])([A-Z])", "$1 $2").trim()
						: null;
				if (viewDetails.contains(fieldName)) {
					PatientDetails patientDetail = new PatientDetails();
					field.setAccessible(true);

					try {
						patientDetail.setValue(Objects.nonNull(field.get(patientEnrollmentEntity))
								? field.get(patientEnrollmentEntity).toString()
								: null);
						patientDetail.setField(fieldName);
						patientDetails.add(patientDetail);
					} catch (IllegalArgumentException e) {
						logger.error(e.getMessage());
					} catch (IllegalAccessException e) {
						logger.error(e.getMessage());
					}
				}

			}
		} else {
			throw new Exception("patientId is not available");
		}

		return patientDetails;

	}

	@Override
	public List<SourceObservationDTO> getAllObservation(Integer patientId) {

		PatientEnrollmentEntity patientEnrollmentEntity = patientEnrollmentRepository.findByPatientId(patientId);

		if (Objects.nonNull(patientEnrollmentEntity)) {

			List<SourcePatientEntity> sourcePatientEntities = patientEnrollmentEntity.getSourcePatientEntity();

			// sourcePatientRepository.findBySourcePatientId_PatientEnrollmentEntity_PatientId(patientId);

			List<SourceObservationDTO> sourceObservationDTOs = new ArrayList<>();

			if (Objects.nonNull(sourcePatientEntities) && !sourcePatientEntities.isEmpty()) {
				sourcePatientEntities.forEach(sp -> {
					List<ObservationEntity> observations = observationRepository
							.findTop700BySourcePatientEntity_SourcePatientId_PatientEnrollmentEntity_PatientIdAndSourcePatientEntity_SourcePatientId_Source_SourceIdOrderByEffectiveDateTimeDesc(
									patientId, sp.getSourcePatientId().getSource().getSourceId());
					SourceObservationDTO sourceObservationDTO = new SourceObservationDTO();
					sourceObservationDTO.setSourceName(sp.getSourcePatientId().getSource().getSourceName());
					List<ObservationResponseDTO> observationResponseDTOs = new ArrayList<>();
					observations.forEach(observ -> {
						ObservationResponseDTO observationResponseDTO = populatePostObservationDTOTest(observ);
						observationResponseDTOs.add(observationResponseDTO);

					});
					sourceObservationDTO.setObservationResponseDTO(observationResponseDTOs);
					sourceObservationDTOs.add(sourceObservationDTO);
				});
				return sourceObservationDTOs;
			}
		}

		return null;
	}

	@Override
	public ObservationEntity createObservationTest(Resource resource) {
		return populatePatientObservationEntityTest(resource);
	}

	private ObservationEntity populatePatientObservationEntityTest(Resource resource) {
		ObservationEntity observationEntity = null;
		PatientEnrollmentEntity patientEnrollmentEntity = getPatientId(resource.getSubject().getReference());
		SourceEntity source = getOrCreateSource(resource.getComment());
		logger.info("patientEnrollmentEntity is null::" + Objects.isNull(patientEnrollmentEntity));
		if (Objects.nonNull(patientEnrollmentEntity)) {
			SourcePatientEntity sourcePatientEntity = sourcePatientRepository
					.findBySourcePatientId_PatientEnrollmentEntity_PatientIdAndSourcePatientId_Source_SourceId(
							patientEnrollmentEntity.getPatientId(), source.getSourceId());
			;
			if (Objects.isNull(sourcePatientEntity)) {
				sourcePatientEntity = new SourcePatientEntity();
				SourcePatientId sourcePatientId = new SourcePatientId();
				sourcePatientId.setPatientEnrollmentEntity(patientEnrollmentEntity);
				sourcePatientId.setSource(source);
				sourcePatientEntity.setSourcePatientId(sourcePatientId);
				sourcePatientEntity = sourcePatientRepository.save(sourcePatientEntity);
			}
			observationEntity = new ObservationEntity();
			logger.info("patientEnrollmentEntity is null::" + Objects.isNull(resource.getCategory()));
			observationEntity.setCategory(Objects.nonNull(resource.getCategory())
					? resource.getCategory().get(0).getCoding().get(0).getDisplay()
					: null);
			observationEntity.setCdrId(resource.getId());
			observationEntity.setEffectiveDateTime(resource.getEffectiveDateTime());
			logger.info("size:::" + resource.getCode().getCoding().size());
			observationEntity.setSubCategoryType(resource.getCode().getCoding().get(0).getDisplay());
			observationEntity.setSubCategoryCode(
					resource.getCode().getCoding().size() >= 2 ? resource.getCode().getCoding().get(1).getDisplay()
							: null);
			observationEntity.setSourcePatientEntity(sourcePatientEntity);
			logger.info("i here also");
			// observationEntity.setPatientEnrollmentEntity(patientEnrollmentEntity);
			List<ObservationValueEntity> observationValueEntity = populateObservationValueEntity(resource,
					observationEntity);

			observationEntity.setObservationValueEntity(observationValueEntity);
			observationRepository.save(observationEntity);
		}
		return observationEntity;
	}

	private SourceEntity getOrCreateSource(String comment) {

		SourceEntity source = sourceRepository.findBySourceName(comment);
		if (Objects.isNull(source)) {
			source = new SourceEntity();
			source.setSourceName(comment);
			return sourceRepository.save(source);
		}
		return source;

	}

	private PatientEnrollmentEntity getPatientId(String subReference) {

		/*
		 * PatientSubRefResponse patientSubRefResponse =
		 * restTemplate.getForObject(subReference, PatientSubRefResponse.class);
		 */
		// logger.info("patientSubRefResponse::" + patientSubRefResponse);
		// if (Objects.nonNull(patientSubRefResponse)) {
		// String patientMrn = patientSubRefResponse.getIdentifier().get(0).getValue();
		String cdrId = subReference.substring(subReference.lastIndexOf('/') + 1);
		PatientEnrollmentEntity patientEnrollmentEntity = patientEnrollmentRepository.findByCdrId(cdrId); // patientMrn
		return Objects.nonNull(patientEnrollmentEntity) ? patientEnrollmentEntity : null;

	}

	private List<ObservationValueEntity> populateObservationValueEntity(Resource resource,
			ObservationEntity observationEntity) {
		List<ObservationValueEntity> observationValueEntities = new ArrayList<>();

		if (Objects.nonNull(resource.getComponent()) && !resource.getComponent().isEmpty()) {
			logger.info("inside if of populateObservationValueEntity");
			for (com.example.ilsvitalsign_be.dto.Component component : resource.getComponent()) {
				ObservationValueEntity observationValueEntity = new ObservationValueEntity();
				observationValueEntity.setType(component.getCode().getCoding().get(0).getDisplay());
				observationValueEntity.setCode(component.getCode().getCoding().size() >= 2
						? component.getCode().getCoding().get(1).getDisplay()
						: null);
				observationValueEntity.setUnit(component.getValueQuantity().getUnit());
				observationValueEntity.setValue(component.getValueQuantity().getValue().toString());
				observationValueEntity.setObservationEntity(observationEntity);
				observationValueEntities.add(observationValueEntity);
				logger.info("inside if done");
			}
		} else {
			logger.info("inside else of populateObservationValueEntity");
			ObservationValueEntity observationValueEntity = new ObservationValueEntity();
			observationValueEntity.setType(null);
			observationValueEntity.setCode(null);
			observationValueEntity.setUnit(resource.getValueQuantity().getUnit());
			observationValueEntity.setValue(resource.getValueQuantity().getValue().toString());
			observationValueEntity.setObservationEntity(observationEntity);
			observationValueEntities.add(observationValueEntity);
			logger.info("inside else done");
		}

		return observationValueEntities;
	}

	@Override
	public ObservationResponseDTO populatePostObservationDTOTest(ObservationEntity observationEntity) {
		ObservationResponseDTO observationResponseDTO = new ObservationResponseDTO();
		observationResponseDTO.setCategory(observationEntity.getCategory());
		observationResponseDTO.setCdrId(observationEntity.getCdrId());
		observationResponseDTO.setObservationId(observationEntity.getObservationId());
		observationResponseDTO.setSourceName(
				observationEntity.getSourcePatientEntity().getSourcePatientId().getSource().getSourceName());
		List<ObservationValueDTO> observationValueDTO = populateObservationValueDTO(observationEntity);
		observationResponseDTO.setObservationValueDTO(observationValueDTO);
		observationResponseDTO.setPatientId(observationEntity.getSourcePatientEntity().getSourcePatientId()
				.getPatientEnrollmentEntity().getPatientId().toString());
		observationResponseDTO.setSubCategoryCode(observationEntity.getSubCategoryCode());
		observationResponseDTO.setSubCategoryType(observationEntity.getSubCategoryType());
		return observationResponseDTO;

	}

	private List<ObservationValueDTO> populateObservationValueDTO(ObservationEntity observationEntity) {
		List<ObservationValueDTO> observationValueDTOs = new ArrayList<>();

		observationEntity.getObservationValueEntity().stream().forEach(obe -> {
			ObservationValueDTO observationValueDTO = new ObservationValueDTO();
			observationValueDTO.setCode(obe.getCode());
			observationValueDTO.setType(obe.getType());
			observationValueDTO.setEffectiveDateTime(obe.getObservationEntity().getEffectiveDateTime());
			observationValueDTO.setUnit(obe.getUnit());
			observationValueDTO.setValue(obe.getValue());
			observationValueDTOs.add(observationValueDTO);

		});
		return observationValueDTOs;
	}

}
