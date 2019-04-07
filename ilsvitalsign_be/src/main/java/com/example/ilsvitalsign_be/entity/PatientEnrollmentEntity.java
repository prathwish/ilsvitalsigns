package com.example.ilsvitalsign_be.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PATIENT_ENROLLMENT")
public class PatientEnrollmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PATIENT_ID")
	private Integer patientId;

	@Column(name = "PATIENT_SET_ID", length = 128)
	private String patientSetID;

	@Column(name = "PATIENT_EXT_ID", length = 128)
	private String patientExtId;

	@Column(name = "MRN", length = 128)
	private String mrn;

	@Column(name = "ALT_PID", length = 128)
	private String altPID;

	@Column(name = "FIRST_NAME", length = 128)
	private String firstName;

	@Column(name = "MID_NAME", length = 128)
	private String midName;

	@Column(name = "LAST_NAME", length = 128)
	private String lastName;

	@Column(name = "SUFFIX", length = 128)
	private String suffix;

	@Column(name = "PREFIX", length = 128)
	private String prefix;

	@Column(name = "MOTHER_MAIDEN_NAME", length = 128)
	private String motherMaidenName;

	@Column(name = "DOB", length = 128)
	private String dob;

	@Column(name = "GENDER", length = 128)
	private String gender;

	@Column(name = "PATIENT_ALIAS_FIRST_NAME", length = 128)
	private String patientAliasFirstName;

	@Column(name = "PATIENT_ALIAS_MID_NAME", length = 128)
	private String patientAliasMidName;

	@Column(name = "PATIENT_ALIAS_LASTNAME", length = 128)
	private String patientAliasLastName;

	@Column(name = "PATIENT_ALIAS_SUFFIX", length = 128)
	private String patientAliasSuffix;

	@Column(name = "PATIENT_ALIAS_PREFIX", length = 128)
	private String patientAliasPrefix;

	@Column(name = "RACE", length = 128)
	private String race;

	@Column(name = "STREET", length = 128)
	private String street;

	@Column(name = "OTHER", length = 128)
	private String other;

	@Column(name = "CITY", length = 128)
	private String city;

	@Column(name = "STATE", length = 128)
	private String state;

	@Column(name = "ZIPCODE", length = 128)
	private String zipCode;

	@Column(name = "COUNTRY", length = 128)
	private String country;

	@Column(name = "TYPE", length = 128)
	private String type;

	@Column(name = "COUNTRY_CODE", length = 128)
	private String countryCode;

	@Column(name = "HOME_PHONE_NUMBER", length = 128)
	private String homePhoneNumber;

	@Column(name = "BUSINESS_PHONE_NUMBER", length = 128)
	private String businessPhoneNumber;

	@Column(name = "PRIMARY_LANGUAGE", length = 128)
	private String primaryLanguage;

	@Column(name = "MARITAL_STATUS", length = 128)
	private String maritalStatus;

	@Column(name = "RELIGION", length = 128)
	private String religion;

	@Column(name = "PATIENT_ACCOUNT_NUMBER", length = 128)
	private String patientAccountNumber;

	@Column(name = "SSN_NUMBER", length = 128)
	private String SSN_Number;

	@Column(name = "DRIVER_LICENSE_NUMBER", length = 128)
	private String driverLicenseNumber;

	@Column(name = "MOTHER_IDENTIFIER", length = 128)
	private String motherIdentifier;

	@Column(name = "ETHNIC_GROUP", length = 128)
	private String ethnicGroup;

	@Column(name = "BIRTH_PLACE", length = 128)
	private String birthPlace;

	@Column(name = "MULTIPLE_BIRTH_INDICATOR", length = 128)
	private String multipleBirthIndicator;

	@Column(name = "BIRTH_ORDER", length = 128)
	private String birthOrder;

	@Column(name = "CITIZENSHIP", length = 128)
	private String citizenship;

	@Column(name = "VETERANS_MILITAR_YSTATUS", length = 128)
	private String veteransMilitaryStatus;

	@Column(name = "NATIONALITY", length = 128)
	private String nationality;

	@Column(name = "PATIENT_DEATH_DATE_AND_TIME", length = 128)
	private String patientDeathDateandTime;

	@Column(name = "PATIENT_DEATH_INDICATOR", length = 128)
	private String patientDeathIndicator;

	@Column(name = "ENROLL_FLAG")
	private boolean enrollFlag;

	@Column(name = "CDR_ID", length = 128)
	private String cdrId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sourcePatientId.patientEnrollmentEntity", fetch = FetchType.LAZY)
	private List<SourcePatientEntity> sourcePatientEntity = new ArrayList<>();

	@NotNull
	@Column(name = "AUDIT_TIMESTAMP", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp auditTimestamp;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientSetID() {
		return patientSetID;
	}

	public void setPatientSetID(String patientSetID) {
		this.patientSetID = patientSetID;
	}

	public String getPatientExtId() {
		return patientExtId;
	}

	public void setPatientExtId(String patientExtId) {
		this.patientExtId = patientExtId;
	}

	public String getMRN() {
		return mrn;
	}

	public void setMRN(String mRN) {
		mrn = mRN;
	}

	public String getAltPID() {
		return altPID;
	}

	public void setAltPID(String altPID) {
		this.altPID = altPID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getMotherMaidenName() {
		return motherMaidenName;
	}

	public void setMotherMaidenName(String motherMaidenName) {
		this.motherMaidenName = motherMaidenName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPatientAliasFirstName() {
		return patientAliasFirstName;
	}

	public void setPatientAliasFirstName(String patientAliasFirstName) {
		this.patientAliasFirstName = patientAliasFirstName;
	}

	public String getPatientAliasMidName() {
		return patientAliasMidName;
	}

	public void setPatientAliasMidName(String patientAliasMidName) {
		this.patientAliasMidName = patientAliasMidName;
	}

	public String getPatientAliasLastName() {
		return patientAliasLastName;
	}

	public void setPatientAliasLastName(String patientAliasLastName) {
		this.patientAliasLastName = patientAliasLastName;
	}

	public String getPatientAliasSuffix() {
		return patientAliasSuffix;
	}

	public void setPatientAliasSuffix(String patientAliasSuffix) {
		this.patientAliasSuffix = patientAliasSuffix;
	}

	public String getPatientAliasPrefix() {
		return patientAliasPrefix;
	}

	public void setPatientAliasPrefix(String patientAliasPrefix) {
		this.patientAliasPrefix = patientAliasPrefix;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}

	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}

	public String getBusinessPhoneNumber() {
		return businessPhoneNumber;
	}

	public void setBusinessPhoneNumber(String businessPhoneNumber) {
		this.businessPhoneNumber = businessPhoneNumber;
	}

	public String getPrimaryLanguage() {
		return primaryLanguage;
	}

	public void setPrimaryLanguage(String primaryLanguage) {
		this.primaryLanguage = primaryLanguage;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getPatientAccountNumber() {
		return patientAccountNumber;
	}

	public void setPatientAccountNumber(String patientAccountNumber) {
		this.patientAccountNumber = patientAccountNumber;
	}

	public String getSSN_Number() {
		return SSN_Number;
	}

	public void setSSN_Number(String sSN_Number) {
		SSN_Number = sSN_Number;
	}

	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}

	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}

	public String getMotherIdentifier() {
		return motherIdentifier;
	}

	public void setMotherIdentifier(String motherIdentifier) {
		this.motherIdentifier = motherIdentifier;
	}

	public String getEthnicGroup() {
		return ethnicGroup;
	}

	public void setEthnicGroup(String ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getMultipleBirthIndicator() {
		return multipleBirthIndicator;
	}

	public void setMultipleBirthIndicator(String multipleBirthIndicator) {
		this.multipleBirthIndicator = multipleBirthIndicator;
	}

	public String getBirthOrder() {
		return birthOrder;
	}

	public void setBirthOrder(String birthOrder) {
		this.birthOrder = birthOrder;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getVeteransMilitaryStatus() {
		return veteransMilitaryStatus;
	}

	public void setVeteransMilitaryStatus(String veteransMilitaryStatus) {
		this.veteransMilitaryStatus = veteransMilitaryStatus;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPatientDeathDateandTime() {
		return patientDeathDateandTime;
	}

	public void setPatientDeathDateandTime(String patientDeathDateandTime) {
		this.patientDeathDateandTime = patientDeathDateandTime;
	}

	public String getPatientDeathIndicator() {
		return patientDeathIndicator;
	}

	public void setPatientDeathIndicator(String patientDeathIndicator) {
		this.patientDeathIndicator = patientDeathIndicator;
	}

	public boolean isEnrollFlag() {
		return enrollFlag;
	}

	public void setEnrollFlag(boolean enrollFlag) {
		this.enrollFlag = enrollFlag;
	}

	public Timestamp getAuditTimestamp() {
		return auditTimestamp;
	}

	public void setAuditTimestamp(Timestamp auditTimestamp) {
		this.auditTimestamp = auditTimestamp;
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public List<SourcePatientEntity> getSourcePatientEntity() {
		return sourcePatientEntity;
	}

	public void setSourcePatientEntity(List<SourcePatientEntity> sourcePatientEntity) {
		this.sourcePatientEntity = sourcePatientEntity;
	}

	public String getCdrId() {
		return cdrId;
	}

	public void setCdrId(String cdrId) {
		this.cdrId = cdrId;
	}
}
