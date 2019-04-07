package com.example.ilsvitalsign_be.dto;

import java.util.List;

public class PatientDTO {

	private String resourceType;
	private List<Identifier> identifier;
	private List<Name> name;
	private List<Telecom> telecom;
	private String gender;
	private String birthDate;
	private List<Address> address;
	private MaritalStatus maritalStatus;
	private Boolean multipleBirthBoolean;
	private List<Communication> communication;

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public List<Identifier> getIdentifier() {
		return identifier;
	}

	public void setIdentifier(List<Identifier> identifier) {
		this.identifier = identifier;
	}

	public List<Name> getName() {
		return name;
	}

	public void setName(List<Name> name) {
		this.name = name;
	}

	public List<Telecom> getTelecom() {
		return telecom;
	}

	public void setTelecom(List<Telecom> telecom) {
		this.telecom = telecom;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Boolean getMultipleBirthBoolean() {
		return multipleBirthBoolean;
	}

	public void setMultipleBirthBoolean(Boolean multipleBirthBoolean) {
		this.multipleBirthBoolean = multipleBirthBoolean;
	}

	public List<Communication> getCommunication() {
		return communication;
	}

	public void setCommunication(List<Communication> communication) {
		this.communication = communication;
	}

}
