package com.example.ilsvitalsign_be.dto;

public class PatientListDTO {

	private Integer patientId;
	private String mrn;
	private String firstname;
	private String lastname;
	private Boolean enrollFlag;

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Boolean getEnrollFlag() {
		return enrollFlag;
	}

	public void setEnrollFlag(Boolean enrollFlag) {
		this.enrollFlag = enrollFlag;
	}

	@Override
	public String toString() {
		return "PatientListDTO [patientId=" + patientId + ", mrn=" + mrn + ", firstname=" + firstname + ", lastname="
				+ lastname + ", enrollFlag=" + enrollFlag + "]";
	}

}
