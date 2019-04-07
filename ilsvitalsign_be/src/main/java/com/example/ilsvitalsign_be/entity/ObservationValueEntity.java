package com.example.ilsvitalsign_be.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OBSERVATIONVALUE_TABLE")
public class ObservationValueEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OBSERVATION_VALUE_ID")
	private Integer observationValueId;

	@Column(name = "TYPE", length = 128)
	private String type;

	@Column(name = "CODE", length = 128)
	private String code;

	@Column(name = "UNIT", length = 128)
	private String unit;

	@Column(name = "VALUE", length = 128)
	private String value;

	@ManyToOne
	@JoinColumn(name = "OBSERVATION_ID")
	private ObservationEntity observationEntity;

	public Integer getObservationValueId() {
		return observationValueId;
	}

	public void setObservationValueId(Integer observationValueId) {
		this.observationValueId = observationValueId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ObservationEntity getObservationEntity() {
		return observationEntity;
	}

	public void setObservationEntity(ObservationEntity observationEntity) {
		this.observationEntity = observationEntity;
	}

	@Override
	public String toString() {
		return "ObservationValueEntity [observationValueId=" + observationValueId + ", type=" + type + ", code=" + code
				+ ", unit=" + unit + ", value=" + value + ", observationEntity=" + observationEntity + "]";
	}

}
