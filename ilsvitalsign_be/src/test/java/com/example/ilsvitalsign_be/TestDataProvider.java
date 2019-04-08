package com.example.ilsvitalsign_be;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.example.ilsvitalsign_be.dto.Category;
import com.example.ilsvitalsign_be.dto.Code;
import com.example.ilsvitalsign_be.dto.Coding;
import com.example.ilsvitalsign_be.dto.Component;
import com.example.ilsvitalsign_be.dto.PatientEnrollmentDTO;
import com.example.ilsvitalsign_be.dto.Resource;
import com.example.ilsvitalsign_be.dto.Subject;
import com.example.ilsvitalsign_be.dto.ValueQuantity;
import com.example.ilsvitalsign_be.entity.ObservationEntity;
import com.example.ilsvitalsign_be.entity.ObservationValueEntity;
import com.example.ilsvitalsign_be.entity.PatientEnrollmentEntity;
import com.example.ilsvitalsign_be.entity.SourceEntity;
import com.example.ilsvitalsign_be.entity.SourcePatientEntity;
import com.example.ilsvitalsign_be.entity.SourcePatientId;

public class TestDataProvider {

	@DataProvider(name = "PatientEnrollmentEntity")
	public static Object[][] patientEnrollmentEntity() {
		PatientEnrollmentEntity patientEnrollmentEntity = new PatientEnrollmentEntity();
		patientEnrollmentEntity.setPatientId(1);
		patientEnrollmentEntity.setFirstName("Jhon");
		patientEnrollmentEntity.setLastName("Abhrahm");
		patientEnrollmentEntity.setMrn("12345");
		patientEnrollmentEntity.setDob("25-09-1993");

		return new Object[][] { { patientEnrollmentEntity } };
	}

	@DataProvider(name = "PatientEnrollmentDTO")
	public static Object[][] patientEnrollmentDTO() {
		PatientEnrollmentDTO patientEnrollmentDTO = new PatientEnrollmentDTO();
		patientEnrollmentDTO.setMrn("12345");
		patientEnrollmentDTO.setFirstName("Jhon");
		patientEnrollmentDTO.setLastName("Abhrahm");
		patientEnrollmentDTO.setDob("25-09-1993");
		patientEnrollmentDTO.setGender("male");
		patientEnrollmentDTO.setHomePhoneNumber("(03) 3410 5613");
		patientEnrollmentDTO.setBusinessPhoneNumber("(03) 5555 6473");
		patientEnrollmentDTO.setPrimaryLanguage("Italian");
		patientEnrollmentDTO.setDriverLicenseNumber("999-73-6001");

		return new Object[][] { { patientEnrollmentDTO } };
	}

	@DataProvider(name = "ObservationEntity")
	public static Object[][] observationEntity() {
		ObservationEntity observationEntity = new ObservationEntity();
		observationEntity.setObservationId(1);
		observationEntity.setCategory("Vital Signs");
		observationEntity.setEffectiveDateTime("2019-03-20T16:21:54+01:00");
		observationEntity.setObservationValueEntity(
				Arrays.asList((ObservationValueEntity) TestDataProvider.observationValueEntity()[0][0]));
		observationEntity.setSubCategoryCode("MDC_PRESS_BLD_ART_ABP");
		observationEntity.setSubCategoryType("Blood pressure panel with all children optional");
		observationEntity.setCdrId("0b5b22f7-83a3-42fb-b475-b78582d4d7b8");
		return new Object[][] { { observationEntity } };
	}

	@DataProvider(name = "ObservationValueEntity")
	public static Object[][] observationValueEntity() {
		ObservationValueEntity observationValueEntity = new ObservationValueEntity();
		observationValueEntity.setCode("MDC_PRESS_BLD_ART_ABP_SYS");
		observationValueEntity.setObservationValueId(1);
		observationValueEntity.setType("Systolic blood pressure");
		observationValueEntity.setUnit("mmHg");
		observationValueEntity.setValue("120");
		return new Object[][] { { observationValueEntity } };
	}

	@DataProvider(name = "Resource")
	public static Object[][] resource() {
		Resource resource = new Resource();
		resource.setComment("source");
		Subject subject = new Subject();
		subject.setReference("http://130.147.82.83:4080/Patient/7d43cfc4-8a22-4ac9-a1fe-47c03e36c906");
		resource.setSubject(subject);
		resource.setEffectiveDateTime("2019-03-20T16:21:54+01:00");
		resource.setCategory(Arrays.asList((Category) TestDataProvider.getCategory()));
		resource.setCode(TestDataProvider.getCode("http://loinc.org/", "85354-9",
				"Blood pressure panel with all children optional"));
		resource.setComponent(Arrays.asList((Component) TestDataProvider.getComponent()));
		return new Object[][] { { resource } };
	}

	private static Component getComponent() {
		Component component = new Component();
		component.setCode(TestDataProvider.getCode("http://loinc.org/", "8480-6", "Systolic blood pressure"));
		component.setValueQuantity(TestDataProvider.getValueQuantity());
		return component;
	}

	private static ValueQuantity getValueQuantity() {
		ValueQuantity valueQuantity = new ValueQuantity();
		valueQuantity.setCode("mm[Hg]");
		valueQuantity.setSystem("http://unitsofmeasure.org/");
		valueQuantity.setUnit("mmHg");
		valueQuantity.setValue(Long.valueOf(120));
		return valueQuantity;
	}

	private static Code getCode(String system, String code, String display) {
		Code code1 = new Code();
		code1.setText("ABP");
		code1.setCoding(Arrays.asList(TestDataProvider.getCoding(system, code, display)));

		return code1;
	}

	private static Category getCategory() {
		Category category = new Category();
		category.setCoding(Arrays.asList(TestDataProvider.getCoding("http://hl7.org/fhir/observation-category",
				"vital-signs", "\"Vital Signs")));
		return category;
	}

	private static Coding getCoding(String system, String code, String display) {
		Coding coding = new Coding();
		coding.setCode(code);
		coding.setDisplay(display);
		coding.setSystem(system);
		return coding;
	}

	public static Object[][] sourceEntity() {
		SourceEntity sourceEntity = new SourceEntity();
		sourceEntity.setSourceId(1);
		sourceEntity.setSourceName("source");
		return new Object[][] { { sourceEntity } };
	}

	public static Object[][] sourcePatientEntity() {
		SourcePatientEntity sourcePatientEntity = new SourcePatientEntity();
		SourcePatientId sourcePatientId;
		sourcePatientEntity.setSourcePatientId(TestDataProvider.getSourcePatientId());
		sourcePatientEntity
				.setObservationEntity(Arrays.asList((ObservationEntity) TestDataProvider.observationEntity()[0][0]));
		return new Object[][] { { sourcePatientEntity } };
	}

	private static SourcePatientId getSourcePatientId() {
		SourcePatientId sourcePatientId = new SourcePatientId();
		sourcePatientId
				.setPatientEnrollmentEntity((PatientEnrollmentEntity) TestDataProvider.patientEnrollmentEntity()[0][0]);
		sourcePatientId.setSource((SourceEntity) TestDataProvider.sourceEntity()[0][0]);
		return sourcePatientId;
	}

}
