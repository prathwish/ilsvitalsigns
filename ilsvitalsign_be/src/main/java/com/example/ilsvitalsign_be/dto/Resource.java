package com.example.ilsvitalsign_be.dto;

import java.util.List;

public class Resource {

	private String resourceType;
	private String id;
	private Meta meta;
	private String status;
	private String comment;
	private List<Category> category;
	private Code code;
	private Subject subject;
	private String effectiveDateTime;
	private ValueQuantity valueQuantity;
	private Device device;
	private List<Component> component;

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getEffectiveDateTime() {
		return effectiveDateTime;
	}

	public void setEffectiveDateTime(String effectiveDateTime) {
		this.effectiveDateTime = effectiveDateTime;
	}

	public ValueQuantity getValueQuantity() {
		return valueQuantity;
	}

	public void setValueQuantity(ValueQuantity valueQuantity) {
		this.valueQuantity = valueQuantity;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public List<Component> getComponent() {
		return component;
	}

	public void setComponent(List<Component> component) {
		this.component = component;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Resource [resourceType=" + resourceType + ", id=" + id + ", meta=" + meta + ", status=" + status
				+ ", comment=" + comment + ", category=" + category + ", code=" + code + ", subject=" + subject
				+ ", effectiveDateTime=" + effectiveDateTime + ", valueQuantity=" + valueQuantity + ", device=" + device
				+ ", component=" + component + "]";
	}

}
