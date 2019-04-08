package com.example.ilsvitalsign_be;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.ilsvitalsign_be.controller.ilsvitalsignController;
import com.example.ilsvitalsign_be.controller.ilsvitalsignExceptionHandler;
import com.example.ilsvitalsign_be.dto.PatientEnrollmentDTO;
import com.example.ilsvitalsign_be.dto.Resource;
import com.example.ilsvitalsign_be.entity.ObservationEntity;
import com.example.ilsvitalsign_be.entity.PatientEnrollmentEntity;
import com.example.ilsvitalsign_be.entity.SourceEntity;
import com.example.ilsvitalsign_be.entity.SourcePatientEntity;
import com.example.ilsvitalsign_be.repository.ObservationRepository;
import com.example.ilsvitalsign_be.repository.PatientEnrollmentRepository;
import com.example.ilsvitalsign_be.repository.SourcePatientRepository;
import com.example.ilsvitalsign_be.repository.SourceRepository;
import com.example.ilsvitalsign_be.service.IlsvitalsignService;
import com.example.ilsvitalsign_be.service.IlsvitalsignServiceImpl;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IlsvitalsignBeApplicationTests {

	protected transient MockMvc mvc;

	@BeforeMethod
	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(ilsvitalsignService, "patientEnrollmentRepository", patientEnrollmentRepository);
		ReflectionTestUtils.setField(ilsvitalsignService, "observationRepository", observationRepository);
		ReflectionTestUtils.setField(ilsvitalsignService, "sourceRepository", sourceRepository);
		ReflectionTestUtils.setField(ilsvitalsignService, "sourcePatientRepository", sourcePatientRepository);
		ReflectionTestUtils.setField(ilsvitalsignController, "brokerMessagingTemplate", brokerMessagingTemplate);
		mvc = MockMvcBuilders.standaloneSetup(ilsvitalsignController).setControllerAdvice(ilsvitalsignExceptionHandler)
				.build();
	}

	@Mock
	PatientEnrollmentRepository patientEnrollmentRepository;

	@Mock
	ObservationRepository observationRepository;

	@Mock
	SourceRepository sourceRepository;

	@Mock
	SimpMessagingTemplate brokerMessagingTemplate;

	@Mock
	SourcePatientRepository sourcePatientRepository;

	@Spy
	private IlsvitalsignService ilsvitalsignService = new IlsvitalsignServiceImpl();

	@Spy
	private ilsvitalsignExceptionHandler ilsvitalsignExceptionHandler = new ilsvitalsignExceptionHandler();

	@InjectMocks
	private ilsvitalsignController ilsvitalsignController;

	@Test
	private void testValidGePatientById() throws Exception {

		when(patientEnrollmentRepository.findByPatientId(any(Integer.class)))
				.thenReturn((PatientEnrollmentEntity) TestDataProvider.patientEnrollmentEntity()[0][0]);

		this.mvc.perform(get("http://localhost:4000/api/dashboard/getpatient/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk());

	}

	@Test
	private void testValidGetAllPatient() throws Exception {

		when(patientEnrollmentRepository.findAll())
				.thenReturn(Arrays.asList((PatientEnrollmentEntity) TestDataProvider.patientEnrollmentEntity()[0][0]));

		this.mvc.perform(get("http://localhost:4000/api/dashboard/getallpatient/")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk());

	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "PatientEnrollmentDTO")
	private void testValidAddPatient(PatientEnrollmentDTO patientEnrollmentDTO) throws Exception {

		when(patientEnrollmentRepository.save(any(PatientEnrollmentEntity.class)))
				.thenReturn((PatientEnrollmentEntity) TestDataProvider.patientEnrollmentEntity()[0][0]);

		String inputJson = jsonConverter(patientEnrollmentDTO);
		this.mvc.perform(post("http://localhost:4000/api/dashboard/patient/add")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(inputJson)).andExpect(status().isOk());

	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "Resource")
	private void testValidPatientObservation(Resource resource) throws Exception {

		when(patientEnrollmentRepository.findByCdrId(any(String.class)))
				.thenReturn((PatientEnrollmentEntity) TestDataProvider.patientEnrollmentEntity()[0][0]);
		when(sourceRepository.findBySourceName(any(String.class)))
				.thenReturn((SourceEntity) TestDataProvider.sourceEntity()[0][0]);

		when(sourcePatientRepository
				.findBySourcePatientId_PatientEnrollmentEntity_PatientIdAndSourcePatientId_Source_SourceId(
						any(Integer.class), any(Integer.class))).thenReturn(null);

		when(sourcePatientRepository.save(any(SourcePatientEntity.class)))
				.thenReturn((SourcePatientEntity) TestDataProvider.sourcePatientEntity()[0][0]);

		when(observationRepository.save(any(ObservationEntity.class)))
				.thenReturn((ObservationEntity) TestDataProvider.observationEntity()[0][0]);

		String inputJson = jsonConverter(resource);
		this.mvc.perform(post("http://localhost:4000/api/dashboard/patient/observation")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(inputJson)).andExpect(status().isOk());

	}
	
	@Test
	private void testValidPatientEnrollment() throws Exception {

		when(patientEnrollmentRepository.findByPatientId(any(Integer.class)))
				.thenReturn((PatientEnrollmentEntity) TestDataProvider.patientEnrollmentEntity()[0][0]);
		
		this.mvc.perform(put("http://localhost:4000/api/dashboard/patient/enrollment/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk());

	}

	@Test
	private void testValidGetAllObservationsByPatientId() throws Exception {
		when(patientEnrollmentRepository.findByPatientId(any(Integer.class)))
				.thenReturn((PatientEnrollmentEntity) TestDataProvider.patientEnrollmentEntity()[0][0]);
		when(observationRepository
				.findTop700BySourcePatientEntity_SourcePatientId_PatientEnrollmentEntity_PatientIdAndSourcePatientEntity_SourcePatientId_Source_SourceIdOrderByEffectiveDateTimeDesc(
						any(Integer.class), any(Integer.class))).thenReturn(
								Arrays.asList((ObservationEntity) TestDataProvider.observationEntity()[0][0]));

		this.mvc.perform(get("http://localhost:4000/api/dashboard/getallpatient/")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk());

	}

	private String jsonConverter(Object o) {
		String json = null;
		Gson gson = new Gson();
		json = gson.toJson(o);
		return json;
	}

}
